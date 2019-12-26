import React from 'react';
import { Input, Modal, Select, notification,Form } from 'antd';
import SimpleMDE from 'simplemde';
import marked from 'marked';
import highlight from 'highlight.js';
import 'simplemde/dist/simplemde.min.css';

const FormItem = Form.Item;

@Form.create()
export default class ArticleComponent extends React.Component {

    componentDidMount() {
        this.props.smde = new SimpleMDE({
            element: document.getElementById('editor').childElementCount,
            autofocus: true,
            autosave: true,
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
                    highlight(code) {
                        return highlight.highlightAuto(code).value;
                    },
                });
            },
        });
    }

    handleSubmit = e => {
        e.preventDefault();
        this.props.form.validateFieldsAndScroll(
            async (err, values) => {
                if (!err) {

                }
            })
    }

    handleSubmit1() {
        const { dispatch } = this.props;
        const { articleDetail } = this.props.article;
        if (!this.state.title) {
            notification.error({
                message: '文章标题不能为空',
            });
            return;
        }
        if (!this.state.keyword) {
            notification.error({
                message: '文章关键字不能为空',
            });
            return;
        }
        if (!this.state.content) {
            notification.error({
                message: '文章内容不能为空',
            });
            return;
        }
        if (keyword instanceof Array) {
            keyword = keyword.join(',');
        }
        this.setState({
            loading: true,
        });

        let keyword = this.state.keyword;
        if (keyword instanceof Array) {
            keyword = keyword.join(',');
        }
        if (this.state.changeType) {
            const params = {
                id: articleDetail._id,
                title: this.state.title,
                author: this.state.author,
                desc: this.state.desc,
                keyword,
                content: this.state.content,
                img_url: this.state.img_url,
                origin: this.state.origin,
                state: this.state.state,
                type: this.state.type,
                tags: this.state.tags,
                category: this.state.category,
            };
            new Promise(resolve => {
                dispatch({
                    type: 'article/updateArticle',
                    payload: {
                        resolve,
                        params,
                    },
                });
            }).then(res => {
                if (res.code === 0) {
                    notification.success({
                        message: res.message,
                    });
                    this.setState({
                        visible: false,
                        changeType: false,
                        title: '',
                        author: 'biaochenxuying',
                        keyword: '',
                        content: '',
                        desc: '',
                        img_url: '',
                        origin: 0, // 0 原创，1 转载，2 混合
                        state: 1, // 文章发布状态 => 0 草稿，1 已发布
                        type: 1, // 文章类型 => 1: 普通文章，2: 简历，3: 管理员介绍
                        tags: '',
                        category: '',
                        tagsDefault: [],
                        categoryDefault: [],
                    });
                    this.handleSearch(this.state.pageNum, this.state.pageSize);
                } else {
                    notification.error({
                        message: res.message,
                    });
                }
            });
        } else {
            const params = {
                title: this.state.title,
                author: this.state.author,
                desc: this.state.desc,
                keyword: this.state.keyword,
                content: this.state.content,
                img_url: this.state.img_url,
                origin: this.state.origin,
                state: this.state.state,
                type: this.state.type,
                tags: this.state.tags,
                category: this.state.category,
            };
            new Promise(resolve => {
                dispatch({
                    type: 'article/addArticle',
                    payload: {
                        resolve,
                        params,
                    },
                });
            }).then(res => {
                if (res.code === 0) {
                    notification.success({
                        message: res.message,
                    });
                    this.setState({
                        visible: false,
                        chnageType: false,
                    });
                    this.handleSearch(this.state.pageNum, this.state.pageSize);
                } else {
                    notification.error({
                        message: res.message,
                    });
                }
            });
        }
    }

    handleCancel() {

    }

    render() {
        const { articleDetail } = this.props.article;
        const { changeType } = this.props;
        let originDefault = '原创';
        let typeDefault = '文章'; // 文章类型 => 10: 普通文章，20: 想法
        if (changeType) {
            originDefault = articleDetail.origin === 0 ? '原创' : '转载';
            typeDefault = articleDetail.type === 10 ? '普通文章' : '想法';
        } else {
            originDefault = '原创';
        }

        const { TextArea } = Input;
        const normalCenter = {
            textAlign: 'center',
            marginBottom: 20,
        };

        const { getFieldDecorator } = this.props.form;

        return (
            <div>
                <Form onSubmit={this.props.handleSubmit}>
                    <FormItem>
                        {
                            getFieldDecorator("title",
                                {
                                    rules:[{
                                        required: true, message: "任务类型必选"
                                    }],
                                    initialValue: this.props.title,
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
                    <FormItem>
                        {
                            getFieldDecorator("tag",
                                {
                                    rules:[{
                                        required: true, message: "任务类型必选"
                                    }],
                                    initialValue: this.props.tag,
                                })
                            ( <Input
                                    style={normalCenter}
                                    addonBefore="关键字"
                                    size="large"
                                    placeholder="关键字"
                                />
                            )
                        }
                    </FormItem>
                    <FormItem>
                        {
                            getFieldDecorator("tag",
                                {
                                    rules:[{
                                        required: true, message: "任务类型必选"
                                    }],
                                    initialValue: this.props.tag,
                                })
                            ( <Input
                                    style={normalCenter}
                                    addonBefore="关键字"
                                    size="large"
                                    placeholder="关键字"
                                />
                            )
                        }
                    </FormItem>
                    <FormItem>
                        {
                            getFieldDecorator("type",
                                {
                                    initialValue: this.props.type,
                                })
                            ( <Select
                                    style={{ width: 200, marginTop: 20, marginBottom: 20 }}
                                    placeholder="选择文章类型"
                                    defaultValue={typeDefault}
                                >
                                    {/* 文章类型 => 10: 普通文章，20: 想法 */}
                                    <Select.Option value="10">普通文章</Select.Option>
                                    <Select.Option value="20">想法</Select.Option>
                                </Select>
                            )
                        }
                    </FormItem>
                    <FormItem>
                        {
                            getFieldDecorator("origin",
                                {
                                    initialValue: this.props.type,
                                })
                            ( <Select
                                    style={{ width: 200, marginTop: 20, marginLeft: 10, marginBottom: 20 }}
                                    placeholder="选择文章转载状态"
                                    defaultValue={originDefault}
                                >
                                    {/* 0 原创，1 转载 */}
                                    <Select.Option value="0">原创</Select.Option>
                                    <Select.Option value="1">转载</Select.Option>
                                </Select>
                            )
                        }
                    </FormItem>
                    <FormItem>
                        {
                            getFieldDecorator("content",
                                {
                                    initialValue: this.props.content,
                                })
                            // ( <TextArea
                            //         id="editor"
                            //         style={{ marginBottom: 20 }}
                            //         size="large"
                            //         rows={6}
                            //         autosize={{ minRows: 15 }}
                            //         placeholder="文章内容，支持 markdown 格式"
                            //     />
                            (
                            <div title="添加与修改文章" width="1200px">
                                <textarea id="editor" style={{ marginBottom: 20, width: 800 }} size="large" rows={6} />
                            </div>
                            )
                        }
                    </FormItem>
                </Form>
            </div>
        );
    }
}
