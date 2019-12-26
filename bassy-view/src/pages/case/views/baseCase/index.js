import React from 'react'
import {connect} from "react-redux";
import * as XLSX from 'xlsx';
import * as refer from '../../rmind/statics/refer';
import {
    Avatar,
    Button,
    Card,
    Col,
    Form,
    Icon,
    Input,
    message,
    Row,
    Select,
    Switch,
    Table,
    Tag,
    Tooltip,
    Tree,
    Upload
} from 'antd';
import {
    deleteCase,
    getLabelList,
    getPagedBaseCase,
    getProductList,
    getProductModuleTree,
    getXmindBaseCase,
    importTestCase
} from "../../../../apis";
import moment from "moment/moment";
import {addAllProduct} from "../../actions";
import StaffCode from "../../../Common/views/staffCode";
import {parseStaffName} from "../../../../utils";

const FormItem = Form.Item;
const {TreeNode} = Tree;
const ButtonGroup = Button.Group;

const formItemLayout = {
    labelCol: {
        xs: {span: 24},
        sm: {span: 8},
    },
    wrapperCol: {
        xs: {span: 24},
        sm: {span: 16},
    },
};

@connect(state => ({
    staffInfo: state.common.staffInfo,
    testCase: state.testCase,
    authResourceCodeList: state.common.authResourceCodeList,
}))
@Form.create()
export default class BaseCase extends React.Component {
    state = {
        list: [],
        pageNum: 1,
        pageSize: 15,
        total: 0,
        baseLabel: [],
        label: [],
        spanInfo: {
            treeSpan: 0,
            listSpan: 24
        },
        viewTree: false,
        productModuleTreeDate: [],
        product: null,
        module: null,
        caseListView: true,
        selectNodes: [],
        expandNodes: []
    };

    columns = [
        {
            title: "用例id",
            dataIndex: "id",
            key: "id",
            // width: "80px",
            width: 100,
            fixed: 'left',
            align: "center"
        }, {
            title: "用例标题",
            dataIndex: "title",
            fixed: 'left',
            key: "title",
            width: 200,
        }, {
            title: "所属产品",
            dataIndex: "productName",
            key: "productName"
        }, {
            title: "所属模块",
            dataIndex: "moduleName",
            key: "moduleName"
        }, {
            title: "优先级(P)",
            dataIndex: "pri",
            key: "pri",
            maxWidth: "80px",
            render: pri => {
                let backgroundColor = '';
                if (pri === 1) {
                    backgroundColor = '#f5222d';

                } else if (pri === 2) {
                    backgroundColor = '#faad14';

                } else {
                    backgroundColor = '#52c41a';
                }
                return <Avatar style={{backgroundColor}} size="small">{pri}</Avatar>
            }

            // }, {
            // 	title: "系统标签",
            // 	dataIndex: "systemLabel",
            // 	key: "systemLabel",
            // 	width: "150px",
            // 	render: labels => {
            // 		return (
            // 			labels.map((label, num) => {
            // 					const colors = ["magenta", "volcano", "gold", "green", "blue", "purple", "red", "orange", "lime", "cyan", "geekblue",];
            // 					let index = Math.floor((Math.random() * colors.length));
            // 					const isLongLab = label.length > 5;
            // 					return <Tag key={num}
            // 								color={colors[index]}>{isLongLab ? label.slice(0, 5) + '...' : label}</Tag>
            // 				}
            // 			)
            // 		)
            // 	}
        }, {
            title: "基础标签",
            dataIndex: "baseLabel",
            key: "baseLabel",
            width: "150px",
            render: labels => {
                return (
                    labels.map((label, num) => {
                            const colors = ["magenta", "volcano", "gold", "green", "blue", "purple", "red", "orange", "lime", "cyan", "geekblue",];
                            let index = Math.floor((Math.random() * colors.length));
                            const isLongLab = label.length > 5;
                            return <Tag key={num + 1}
                                        color={colors[index]}>{isLongLab ? label.slice(0, 5) + '...' : label}</Tag>
                        }
                    )
                )
            }
            // }, {
            // 	title: "用例类型",
            // 	dataIndex: "type",
            // 	key: "type",
            // 	render: type => {
            // 		const {caseType} = this.props.testCase;
            // 		return caseType.map((ct) => {
            // 			if (type === ct.code) {
            // 				return ct.name;
            // 			}
            // 		});
            // 	}
        }, {
            title: "最近更新人",
            dataIndex: "lastEditedBy",
            key: "lastEditedBy"
        }, {
            title: "最近更新时间",
            dataIndex: "lastEditedDate",
            key: "lastEditedDate",
            render: record => moment(record).format("YYYY-MM-DD HH:mm:ss")
        }, {
            title: "版本号",
            dataIndex: "version",
            key: "version"
        }, {
            title: "操作",
            key: "operation",
            fixed: 'right',
            width: 100,
            render: item => {
                return (
                    this.props.authResourceCodeList &&
                    <div>
                        <Button
                            type="primary"
                            size="small"
                            onClick={() => this.edit(item.id)}
                        >
                            编辑
                        </Button>&nbsp;&nbsp;
                        <Button
                            type="primary"
                            size="small"
                            onClick={() => this.copy(item.id)}
                        >
                            复制
                        </Button>&nbsp;&nbsp;
                        <Button
                            type="primary"
                            size="small"
                            onClick={() => this.delete(item.id)}
                        >
                            删除
                        </Button>&nbsp;&nbsp;
                    </div>
                )
            }
        }
    ];

    componentWillUnmount() {

    }

    componentDidMount() {
        const params = JSON.parse(localStorage.getItem('case_param'));
        let {title, type, label, lastEditedBy, product, module, selectNodes, expandNodes} = params === null ? {} : params;
        let labelValues = [];
        if (label !== undefined) {
            label.map(lab => {
                labelValues.push(lab.labelName)
            });
        }
        label = label !== undefined ? label : [];

        let viewTree = product !== null || module !== null;

        let spanInfo = {};
        if (viewTree) {
            spanInfo = {treeSpan: 4, listSpan: 20}
        } else {
            spanInfo = {treeSpan: 0, listSpan: 24}
        }
        if (selectNodes === undefined || expandNodes === undefined) {
            selectNodes = this.state.selectNodes;
            expandNodes = this.state.expandNodes;
        }

        this.setState({
            label,
            labelValues,
            title,
            type,
            viewTree,
            spanInfo,
            selectNodes,
            expandNodes
        });
        this.searchBaseCaseList({product, module});
        this.initCaseCommon();
        this.initProductTree();
    }

    initCaseCommon = async () => {
        const res = await getProductList({keyWords: ""});
        this.props.dispatch(addAllProduct(res.data));
    };

    initProductTree = async () => {
        const res = await getProductModuleTree({});
        this.setState({productModuleTreeDate: res.data});
    };

    edit = async (caseId) => {
        this.props.history.push({
            pathname: '/case/baseCase/list/caseDetail',
            state: {caseId, edit: true}
        })
    };

    copy = async (caseId) => {
        this.props.history.push({
            pathname: '/case/baseCase/list/caseDetail',
            state: {caseId, copy: true}
        })
    };

    delete = async (caseId) => {
        const res = await deleteCase({id: caseId});
        const {product, module, pageNum} = this.state;
        if (res.data) {
            this.searchBaseCaseList({product, module, pageNum});
        }
    };


    changeSearchCaseTitle = e => {
        this.setState({title: e.target.value});
    };

    fetchLabel = async value => {
        this.setState({data: [], fetching: true});
        const res = await getLabelList({type: "base", keyWords: value});
        this.setState({baseLabel: res.data.list, fetching: false});
    };

    handleChange = value => {
        let {label} = this.state;
        this.state.baseLabel.map(lab => {
            if (lab.name === value) {
                label = [...label, {type: "base", labelName: lab.name, id: lab.id}];
            }
        });
        let labelValues = [];
        label.map(lab => {
            labelValues.push(lab.labelName)
        });
        this.setState({
            labelValues,
            baseLabel: [],
            label,
            fetching: false
        });
    };

    handleClose = removedTag => {
        const newLabel = this.state.label.filter(lab => lab.labelName !== removedTag);
        let labelValues = [];
        newLabel.map(lab => {
            labelValues.push(lab.labelName)
        });
        this.setState({labelValues, label: newLabel});
    };

    changeCaseType = (value) => {
        this.setState({type: value});
    };

    doImportTestCase = async (file) => {
        const formData = new FormData();
        formData.append('file', file);
        formData.append('lastEditedBy', this.props.staffInfo.code,);
        const res = await importTestCase(formData);
        if (res.errCode === 1 && res.data.failCases.length === 0) {
            message.info("上传成功！")
        } else if (res.errCode === 1 && res.data.failCases.length > 0) {
            message.error("有部分用例上传失败！");
            this.downloadFile("FailCase.xls", res.data.failCases);
        } else {
            message.error("上传失败！");
        }
    };

    downloadFile = (fileName, content) => {

        const columns = [["用例id", "所属产品", "所属模块", "用例标题", "用例类型", "优先级", "前置条件", "标签"
            , "步骤", "预期1-DB", "预期2-UI/交互", "预期3-接口", "预期4-其他", "版本"]];


        /* generate worksheet */
        let ws = XLSX.utils.aoa_to_sheet(columns);

        if (content !== undefined) {
            XLSX.utils.sheet_add_aoa(ws, content, {
                origin: 'A2' // 从A2开始增加内容
            });
        }

        /* generate workbook and add the worksheet */
        const wb = XLSX.utils.book_new();
        XLSX.utils.book_append_sheet(wb, ws, 'case');

        /* save to file */
        XLSX.writeFile(wb, fileName);

    };

    addNewTestCase = () => {
        this.props.history.push({
            pathname: '/case/baseCase/list/caseDetail',
            state: {add: true}
        })
    };

    exportTestCase = () => {
        this.downloadFile("CaseModule.xls");
    };


    viewMind = () => {
        this.props.form.validateFieldsAndScroll(async (err, values) => {
                if (!err) {
                    const {label, product, module, selectNodes, expandNodes} = this.state;
                    const param = Object.assign({}, values, {
                        label,
                        family: 0,
                        product,
                        module,
                        selectNodes,
                        expandNodes,
                        lastEditedBy: parseStaffName(values.lastEditedBy)
                    });
                    localStorage.setItem("case_param", JSON.stringify(param));
                    const res = await getXmindBaseCase(param);
                    if (res.data !== null) {
                        localStorage.setItem('mindmap', JSON.stringify(res.data));
                        localStorage.setItem('title', refer.DEFAULT_TITLE);
                        this.props.history.push({
                            pathname: '/case/baseCase/list/caseMindView'
                        })
                    } else {
                        this.setState({caseListView: true})
                    }
                }
            }
        );

    };

    searchBaseCaseList = async ({product, module, pageNum}) => {
        this.props.form.validateFieldsAndScroll(async (err, values) => {
            if (!err) {
                const {label} = this.state;
                pageNum = pageNum === null ? this.state.pageNum : pageNum;
                const formParam = {
                    label,
                    title: values.title,
                    type: values.type,
                    lastEditedBy: parseStaffName(values.lastEditedBy),
                    family: 0
                };
                const moduleParam = {product, module};
                const typeParam = {family: 0};
                const pageNumParam = {pageNum, pageSize: 20};
                const param = Object.assign({}, formParam, moduleParam, typeParam, pageNumParam);
                const res = await getPagedBaseCase(param);
                if (res.errCode === 1) {
                    this.setState({
                        list: res.data.list,
                        current: res.data.currentPage,
                        pageSize: res.data.pageSize,
                        total: res.data.totalCount,
                        product,
                        module
                    });
                }
            }
        });
    };

    changePageNum = async (pageNum) => {
        const {product, module} = this.state;
        this.searchBaseCaseList({product, module, pageNum});
    };

    selectModuleNode = async (node) => {
        this.searchBaseCaseList({product: node.productId, module: node.moduleId});
        this.setState({selectNodes: [node.id], product: node.productId, module: node.moduleId});

    };

    expandNode = value => {
        this.setState({expandNodes: value});
    };

    submit = async e => {
        e.preventDefault();
        const {product, module} = this.state;
        this.searchBaseCaseList({product, module});
    };

    render() {
        const {getFieldDecorator} = this.props.form;
        const {caseType} = this.props.testCase;
        const {
            list, baseLabel, spanInfo, viewTree, productModuleTreeDate, total, labelValues, title, type, lastEditedBy,
            current, pageSize, caseListView, selectNodes, expandNodes
        } = this.state;

        const props = {
            beforeUpload: file => {
                this.doImportTestCase(file);
            }
        };

        const renderTreeNode = (data) => data.map(node => {

            node.title = (
                <span onClick={() => this.selectModuleNode(node)}>{node.name}</span>
            );
            if (node.children) {
                return <TreeNode title={node.title} key={node.id}
                >{renderTreeNode(node.children)}</TreeNode>
            }
            return <TreeNode title={node.title} key={node.id}/>
        });

        return (
            <Card>
                <Form onSubmit={this.submit}>
                    <Row gutter={8}>
                        <Col span={5}>
                            <FormItem label={"用例标题"} {...formItemLayout}>
                                {getFieldDecorator("title", {initialValue: title})(
                                    <Input onChange={this.changeSearchCaseTitle} allowClear/>
                                )}
                            </FormItem>
                        </Col>
                        <Col span={5}>
                            <FormItem label={"标签"} {...formItemLayout}>
                                <Select
                                    mode="multiple"
                                    style={{width: '100%'}}
                                    placeholder="请添加基础标签"
                                    autoClearSearchValue
                                    filterOption={false}
                                    onSearch={this.fetchLabel}
                                    onSelect={this.handleChange}
                                    onDeselect={this.handleClose}
                                    value={labelValues}
                                >
                                    {
                                        baseLabel.map(b => (
                                                <Select.Option key={b.id} value={b.name}>
                                                    <Tooltip placement="topLeft" title={b.name}>{b.name}</Tooltip>
                                                </Select.Option>
                                            )
                                        )
                                    }
                                </Select>
                            </FormItem>
                        </Col>
                        <Col span={5}>
                            <FormItem label={"用例类型"} {...formItemLayout}>
                                {getFieldDecorator("type", {initialValue: type})(
                                    <Select
                                        onChange={this.changeCaseType}
                                    >
                                        {
                                            caseType.map((t) => (
                                                <Select.Option key={t.code} value={t.code}>{t.name}</Select.Option>
                                            ))
                                        }
                                    </Select>
                                )}
                            </FormItem>
                        </Col>
                        <Col span={5}>
                            <FormItem label={"最近更新人"} {...formItemLayout}>
                                {getFieldDecorator("lastEditedBy")(<StaffCode/>
                                )}
                            </FormItem>
                        </Col>
                        <Col span={4}>
                            <FormItem>
                                <Button type="primary" icon="search" htmlType="submit">搜索</Button>
                            </FormItem>
                        </Col>
                    </Row>
                    <Row gutter={8}>
                        <Col span={5}>
                            <FormItem label={"用例形式"} {...formItemLayout}>
                                <Switch checkedChildren="list"
                                        unCheckedChildren="CMind"
                                        defaultChecked
                                        checked={caseListView}
                                        onChange={this.viewMind}
                                />
                            </FormItem>
                        </Col>
                        <Col span={15}/>
                        {
                            this.props.authResourceCodeList &&
                            <Col span={4}>
                                <ButtonGroup>
                                    <Button type="primary" style={{maxWidth: 60}} onClick={this.exportTestCase}>导出</Button>
                                    <Upload {...props} showUploadList={false}>
                                        <Button type="primary" style={{maxWidth: 60}}>导入</Button>
                                    </Upload>
                                    <Button type="primary" style={{maxWidth: 60}} onClick={this.addNewTestCase}>新增</Button>
                                </ButtonGroup>
                            </Col>
                        }
                    </Row>
                </Form>
                <Row type="flex">
                    <Col span={spanInfo.treeSpan}>
                        <Tree showLine style={{overflowX: "scroll"}}
                              selectedKeys={selectNodes}
                              expandedKeys={expandNodes}
                            // onSelect={value => this.selectModuleNode({value})}
                              onExpand={this.expandNode}
                        >
                            {
                                renderTreeNode(productModuleTreeDate)
                            }
                        </Tree>
                    </Col>
                    <Col span={spanInfo.listSpan}>
                        <Row style={{display: "flex"}}>
                            <Icon
                                type={viewTree ? "caret-left" : "caret-right"}
                                style={{
                                    background: "#fafafa",
                                    margin: "0px 10px 0px 0px",
                                    color: "#1890ff",
                                    fontSize: 20
                                }}
                                theme="filled"
                                onClick={() => {
                                    this.setState(viewTree ? {
                                        spanInfo: {treeSpan: 0, listSpan: 24},
                                        viewTree: !viewTree
                                    } : {
                                        spanInfo: {treeSpan: 4, listSpan: 20},
                                        viewTree: !viewTree
                                    })
                                }}
                            />
                            <Table
                                style={{width: "100%"}}
                                rowKey="id"
                                columns={this.columns}
                                dataSource={list}
                                scroll={{x: 1500}}
                                pagination={{
                                    onChange: this.changePageNum,
                                    total,
                                    current,
                                    pageSize
                                }
                                }
                            >
                            </Table>
                        </Row>
                    </Col>
                </Row>
            </Card>
        )
    }
}

