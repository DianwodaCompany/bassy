import React from "react";

import {
    Row,
    Col,
    Card,
    Form,
    Input,
    Upload,
    Button,
    Icon,
    Select,
    List,
    Avatar,
    Popover,
    Spin,
    Tooltip
} from 'antd';
import marked from "marked";
import SimpleMDE from "simplemde";
import hljs from 'highlight.js';
import 'highlight.js/styles/github.css';
import 'simplemde/dist/simplemde.min.css';
import './editor.css';
import {connect} from "react-redux";
import {editBBS, getBBSDetail, getBBSPosts} from "../../apis/index"
import {message} from "antd/lib/index";
import moment from "moment";
import {CopyToClipboard} from 'react-copy-to-clipboard';
import {getLabelList} from "../../apis";

const FormItem = Form.Item;
const Dragger = Upload.Dragger;

const oss = require('ali-oss');
const co = require('co');
const bucket = window.__data.bucketName;
const store = oss({
    accessKeyId: 'accessKeyId',
    accessKeySecret: 'accessKeySecret',
    bucket: bucket,
    region: 'region'
});

@Form.create()
@connect(
    state => ({
        staffInfo: state.common.staffInfo,
    })
)
export default class ArticleEditorNote extends React.Component {
    constructor(props) {
        super(props);
        this.state = {
            smde: null,
            changeType: false,
            title: '',
            author: 'biaochenxuying',
            content: '',
            origin: 0, // 0 原创，1 转载，2 混合
            type: 40, // 文章类型 => 10: 普通文章，20: 想法，30: 学习打卡, 40: 记一笔
            noteType: 0,
            tag: [],
            fileList:[],
            uploadedFiles:[],
            copied: false,
            copyId: "",
            loading: false,
            week: moment().format('w'),
            relateBbs: [],
            bbsTitle:[],
            fetchBbs:[],
        };
        this.onChange = this.onChange.bind(this);
    }

    componentWillMount = async() =>  {
        if(this.props.edit) {
            const data = { staffCode: window.__data.staffCode};
            let resp = await getBBSDetail(this.props.location.state.id, data);
            if(resp.data) {
                var article = resp.data;
                this.setState({
                    title: article.title,
                    content: article.content,
                    tag: article.tag,
                    type: article.type,
                    fileList: article.files,
                    week: article.week,
                    relateBbs: article.relateBbs,
                    noteType: article.noteType,
                });
                this.state.smde.value(this.state.content);
                let bbsTitle =[];
                this.state.relateBbs.map(b => {
                    bbsTitle.push(b.title)
                });
                this.setState({bbsTitle});
            }
        }
    };

    componentDidMount() {
        this.state.smde = new SimpleMDE({
            element: document.getElementById('editor').childElementCount,
            autofocus: true,
            autosave: true,
            toolbar:["bold", "italic", "heading", "|",
                "quote", "unordered-list", "ordered-list", "|",
                "code", "horizontal-rule", "link", "image", "table", "|",
                "preview", "side-by-side", "fullscreen", "guide"],
            previewRender(plainText) {
            return marked(plainText, {
                renderer: new marked.Renderer(),
                gfm: true,
                pedantic: false,
                sanitize: false,
                tables: true,
                breaks: true,
                smartLists: true,
                smartypants: true,
                highlight: function(code) {
                    return hljs.highlightAuto(code).value;
                },
            });
        },
    });
    }

    uploadFiles = detail => {
        const suffix = "." + detail.file.name.replace(/^.+\./, '');
        const filename = detail.file.name.replace(suffix, "");
        console.log("uploadFiles---" + JSON.stringify(detail));
        co(function* () {
            return yield store.multipartUpload(`bassy/${moment().format("YYYY-MM-DD")}/${detail.file.name}`,
                detail.file, {
                progress: function* (p, cpt, res) {
                    detail.onProgress({percent: p*100}, detail.file);
                }
            });
        }).then(val => {
            console.log("val-----" + JSON.stringify(val.res.requestUrls));
            detail.onSuccess(val, detail.file);
        });
    };

    fetchArticle = async value => {
        this.setState({data: [], fetching: true});
        const res = await getBBSPosts({keyword: value});
        this.setState({fetchBbs: res.data.list, fetching: false});
    };

    handleChange = value => {
        let { relateBbs } = this.state;
        this.state.fetchBbs.map(art => {
            if (art.title === value) {
                relateBbs = [...relateBbs, {title: art.title, id: art.id}];
            }
        });
        let bbsTitle = [];
        relateBbs.map(art => {
            bbsTitle.push(art.title)
        });
        this.setState({
            bbsTitle,
            relateBbs,
            fetchBbs:[],
            fetching: false
        });
    };

    handleClose = removedTitle => {
        const newRelateBbs = this.state.relateBbs.filter(art => art.title !== removedTitle);
        let bbsTitle = [];
        newRelateBbs.map(art => {
            bbsTitle.push(art.title)
        });
        this.setState({bbsTitle, relateBbs: newRelateBbs});
    };

    onChange = info => {
        let fileList = [...info.fileList];
        const status = info.file.status;
        if (status !== 'uploading') {
            console.log(info.file, info.fileList);
        }
        if (status === 'done') {
            message.success(`${info.file.name} file uploaded successfully.`);

            fileList = fileList.map(file => {
                if (file.response && file.url === undefined) {
                    // Component will show file.url as link
                    file.url = bucket + ".oss-cn-hangzhou.aliyuncs.com/" + info.file.response.name ;
                }
                return file;
            });
        } else if (status === 'error') {
            message.error(`${info.file.name} file upload failed.`);
        }
        this.setState({ fileList });
        console.log("files-----" + JSON.stringify(this.state.fileList));
    };


    handleSubmit = e => {
        e.preventDefault();
        this.props.form.validateFieldsAndScroll(
            async (err, values) => {
                if (!err) {
                    if(this.state.smde.value() === '') {
                        message.warn("还没写任何内容哦~");
                        return
                    }
                    this.setState({ loading: true });
                    let params = Object.assign({}, values);
                    params.authorCode = this.props.staffInfo.code;
                    params.authorName = this.props.staffInfo.name;
                    params.id = this.props.location.state.id;
                    params.content = this.state.smde.value();
                    params.type = this.state.type;
                    params.week =this.state.week;
                    params.relateBbs =this.state.relateBbs;
                    // params.noteType =this.state.noteType;
                    params.staffCode = window.__data.staffCode;
                    let files = [];
                    this.state.fileList.map( file => {
                        files.push({uid: file.uid, name: file.name,
                            url: file.url, type: file.type,
                            lastModified: file.lastModified,
                            size: file.size});
                    });
                    params.files = JSON.stringify(files);
                    let resp = await editBBS(params);
                    if(resp.data) {
                        message.success("新增/编辑成功，感谢分享~~");
                        this.props.history.push({
                            pathname: '/bbs/myArticle',
                        });
                    } else {
                        message.error("新增/编辑失败，请联系开发人员!");
                        return
                    }
                    this.setState({ loading: false });
                }
            })
    };

    render() {

        const normalCenter = {
            textAlign: 'center',
			left: '100px',
        };

        const formItemLayout = {
            labelCol: {
                xs: { span: 24 },
                sm: { span: 8 },
            },
            wrapperCol: {
                xs: { span: 24 },
                sm: { span: 16 },
            },
        };

        const props = {
            name: 'file',
            multiple: true,
            defaultFileList: this.state.fileList,
            fileList: this.state.fileList,
            customRequest: this.uploadFiles,
            onChange: this.onChange,
        };

        const { fetchBbs, bbsTitle} = this.state;

        const { getFieldDecorator } = this.props.form;

        return (
            <div>
                <Spin spinning={this.state.loading}>
                    <Card>
                        <Form {...formItemLayout} onSubmit={this.handleSubmit} >
                            <Row>
                                <Col span={14} >
                                    <FormItem>
                                        {
                                            getFieldDecorator("title",
                                                {
                                                    rules:[{
                                                        required: true, message: "标题必填"
                                                    }],
                                                    initialValue: this.state.title,
                                                })
                                            ( <Input
                                                    style={normalCenter}
                                                    addonBefore="标题"
                                                    size="large"
                                                    placeholder="标题"
                                                />
                                            )
                                        }
                                    </FormItem>
                                </Col>
                            </Row>

                            <Row>
                                <Col span={6} style={{marginLeft: '6%'}}>
                                    <FormItem>
                                        {
                                            getFieldDecorator("tag",
                                                {
                                                    initialValue: this.state.tag,
                                                })
                                            (
                                                <Select mode="tags"
                                                        style={{ width: 800 }}
                                                        placeholder="关键字">
                                                </Select>
                                            )
                                        }
                                    </FormItem>
                                </Col>

                            </Row>
                            <Row>
                                <Col span={4}  style={{marginLeft: '3%'}}>
                                    <FormItem label={"分类"}>
                                        {
                                            getFieldDecorator("noteType",
                                                {
                                                    initialValue: this.state.noteType,
                                                })
                                            ( <Select
                                                    style={{ width: 120 }}
                                                    placeholder="选择分类"
                                                >
                                                    <Select.Option value={0}>业务经验</Select.Option>
                                                    <Select.Option value={1}>测试经验</Select.Option>
                                                    <Select.Option value={2}>工具技巧</Select.Option>
                                                    <Select.Option value={3}>其他</Select.Option>
                                                </Select>
                                            )
                                        }
                                    </FormItem>
                                </Col>
                                <Col span={4}  >
                                <FormItem label={"关联文章"}>
                                    {
                                        getFieldDecorator("relateBbs",
                                            {
                                                initialValue: this.state.bbsTitle,
                                            })
                                        (<Select
                                                mode="multiple"
                                                style={{width: 485}}
                                                placeholder="请添加关联文章"
                                                autoClearSearchValue
                                                filterOption={false}
                                                onSearch={this.fetchArticle}
                                                onSelect={this.handleChange}
                                                onDeselect={this.handleClose}
                                                value={bbsTitle}
                                            >
                                                {
                                                    fetchBbs.map(b => (
                                                            <Select.Option key={b.id} value={b.title}>
                                                                <Tooltip placement="topLeft" title={b.title}>{b.title}</Tooltip>
                                                            </Select.Option>
                                                        )
                                                    )
                                                }
                                            </Select>
                                        )
                                    }
                                </FormItem>
                                </Col>
                            </Row>

                            <FormItem>
                                {
                                    getFieldDecorator("fileUpload")
                                    (
                                        <div style={{marginLeft: 100, width: '100%'}} >
                                            <Dragger {...props}>
                                                <p className="ant-upload-drag-icon">
                                                    <Icon type="inbox" />
                                                </p>
                                                <p className="ant-upload-text">Click or drag file to this area to upload</p>
                                            </Dragger>
                                        </div>
                                    )
                                }
                            </FormItem>
                            <FormItem>
                                {
                                    getFieldDecorator("fileList",
                                        {
                                            initialValue: this.state.fileList,
                                        })
                                    (
                                        <List
                                            style={{marginLeft: 100, width: '100%'}}
                                            itemLayout="horizontal"
                                            header={<div>附件列表</div>}
                                            dataSource={this.state.fileList}
                                            renderItem={file => (
                                                <List.Item actions={[
                                                    <div id={file.uid}>
                                                        <CopyToClipboard text={file.url} onCopy={() => this.setState({copied: true, copyId: file.uid})}>
                                                            <Popover content={this.state.copied && this.state.copyId === file.uid ? "Copied." : null} trigger="click">
                                                                <a>Copy URL</a>
                                                            </Popover>
                                                        </CopyToClipboard>
                                                    </div>
                                                ]}>
                                                    <List.Item.Meta
                                                        avatar={ <Avatar icon="paper-clip" /> }
                                                        title={file.name}
                                                        description={file.url}
                                                    />
                                                </List.Item>
                                            )}
                                        />
                                    )
                                }
                            </FormItem>
                            <FormItem>
                                {
                                    getFieldDecorator("content",
                                        {
                                            initialValue: this.state.content,
                                        })
                                    (
                                        <div title="添加与修改文章" style={{ width: '120%', paddingLeft: 20 }}>
                                            <textarea id="editor"  size="large" rows={6} />
                                        </div>
                                    )
                                }
                            </FormItem>
                            <FormItem>
                                <Button type="primary"
                                        htmlType="submit"
                                        style={{ marginBottom: 20, width: 200, marginLeft: 550}}
                                        loading={this.state.loading}
                                >
                                    发布
                                </Button>
                            </FormItem>
                        </Form>
                    </Card>
                </Spin>
            </div>
        );
    }
}
