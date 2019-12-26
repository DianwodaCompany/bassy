import React from 'react';
import {connect} from "react-redux";
import {Avatar, Button, Col, Form, Input, Modal, Row, Select, Spin, Table, Tag, Tree,message} from 'antd';
import style from '../../case.less';
import {getLabelList, getPagedBaseCase, getProductList, getProductModuleTree, importFromBase} from "../../../../apis";
import {addAllProduct} from "../../actions";
import StaffCode from "../../../Common/views/staffCode";
import {parseStaffName} from "../../../../utils";
import moment from "moment/moment";
import InfiniteScroll from 'react-infinite-scroller';


const FormItem = Form.Item;
const {TreeNode} = Tree;

const formItemLayout = {
    labelCol: {
        xs: {span: 24},
        sm: {span: 7},
    },
    wrapperCol: {
        xs: {span: 24},
        sm: {span: 14},
    },
};

@connect(state => ({
    staffInfo: state.common.staffInfo,
    testCase: state.testCase
}))
@Form.create()
export default class ImportFromBaseCase extends React.Component {


    state = {
        list: [],
        importModalView: false,
        baseLabel: [],
        label: [],
        spanInfo: {
            treeSpan: 4,
            listSpan: 20
        },
        loading: false,
        hasMore: true,
        selectedRows: [],
        selectedRowKeys:[],
        productModuleTreeDate: [],
        productId: null,
        moduleId: null,
        pageNum: 1,
        pageSize: 15,
        total: 0,
        searchParam: {},
    };

    componentDidMount() {
        this.initCaseCommon();
        this.initProductTree();
    }

    initCaseCommon = async () => {
        const res2 = await getProductList({keyWords: ""});
        this.props.dispatch(addAllProduct(res2.data));
    };

    initProductTree = async () => {
        const res = await getProductModuleTree({});
        this.setState({productModuleTreeDate: res.data});
    };

    fetchLabel = async value => {
        this.setState({data: [], fetching: true});
        const res = await getLabelList({type: "base", keyWords: value});
        this.setState({baseLabel: res.data.list, fetching: false});
    };

    handleChange = value => {
        let {label} = this.state;
        if (value && label.indexOf(value) === -1) {
            label.map(lab => {
                if (lab.labelName === value) {
                    return;
                }
            });
            this.state.baseLabel.map(lab => {
                if (lab.name === value) {
                    label = [...label, {type: "base", labelName: lab.name, id: lab.id}];
                }
            })
        }
        console.log(label);
        this.setState({
            baseLabel: [],
            label,
            fetching: false
        });
    };
    handleClose = removedTag => {
        const newLabel = this.state.label.filter(lab => lab.labelName !== removedTag);
        console.log(newLabel);
        this.setState({label: newLabel});
    };

    searchBaseCaseList = async (node) => {
        this.props.form.validateFieldsAndScroll(async (err, values) => {
            if (!err) {
                this.setState({ selectedRows: [], selectedRowKeys: [], });
                let param = Object.assign({}, values, {product: node.productId, module: node.moduleId, family: 0, pageNum: 1,  pageSize: this.state.pageSize,});
                let res = await getPagedBaseCase(param);
                if (res.errCode === 1) {
                    this.setState({list: res.data.list, total: res.data.totalCount, productId: node.productId, moduleId: node.moduleId, searchParam: param});
                }
            }
        });
    };

    doSearch = e => {
        e.preventDefault();
        this.props.form.validateFieldsAndScroll(async (err, values) => {
                if (!err) {
                    console.info(values);
                    this.setState({ selectedRows: [], selectedRowKeys: [], });
                    const param = Object.assign({}, values, {
                        label: this.state.label,
                        lastEditedBy: parseStaffName(values.lastEditedBy),
                        product: this.state.productId,
                        module: this.state.moduleId,
                        family: 0,
                        pageNum: 1,
                        pageSize: this.state.pageSize,
                    });
                    console.info(param);
                    let res = await getPagedBaseCase(param);
                    this.setState({list: res.data.list, total: res.data.totalCount, searchParam: param,});
                }
            }
        );
    };

    fetchMoreData = async() => {
        this.setState({loading: true,});
        if (this.state.list.length >= 500 || this.state.total === this.state.list.length) {
            message.warning('没有更多数据');
            this.setState({
                hasMore: false,
                loading: false,
            });
            return;
        }
        let newSearchParam = Object.assign(this.state.searchParam, {pageNum: this.state.pageNum + 1});
        let res = await getPagedBaseCase(newSearchParam);
        this.setState({
            loading: false,
            pageNum: this.state.pageNum + 1,
            list: this.state.list.concat(res.data.list)
        });
    };

    onSelectChange = (selectedRowKeys, selectedRows) => {
        console.log('selectedRows changed: ', selectedRows);
        this.setState({ selectedRowKeys,selectedRows });
    };

    doImport = async() => {
        const { selectedRows, selectedRowKeys } = this.state;
        if(selectedRows.length ===0) {
            message.info("至少选中一条用例");
            return;
        }
        let param = Object.assign({});
        param.lastEditedBy = this.props.staffInfo.code;
        param.baseIds = selectedRowKeys;
        param.require = this.props.require;

        let resp = await importFromBase(param);
        if(resp.data === selectedRows.length) {
            message.success("导入" + resp.data + "条用例", );
        } else {
            let fail = selectedRows.length - resp.data;
            message.warn("导入" + resp.data + "条用例," + fail + "条已存在或导入失败");
        }
        this.setState({ selectedRows: [], selectedRowKeys: [], });
        this.props.refreshProgramCase(this.props.require);
    };

    render() {

        const caseColumns = [
            {
                title: "产品",
                dataIndex: "productName",
                key: "productName",
            }, {
                title: "模块",
                dataIndex: "moduleName",
                key: "moduleName",
            }, {
                title: "优先级(P)",
                dataIndex: "pri",
                key: "pri",
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
            }, {
                title: "用例标题",
                dataIndex: "title",
                key: "title",
            }, {
                title: "系统标签",
                dataIndex: "systemLabel",
                key: "systemLabel",
                render: labels => {
                    return (
                        labels.map((label,num) => {
                                const colors = ["magenta", "volcano", "gold", "green", "blue", "purple", "red", "orange", "lime", "cyan", "geekblue",];
                                let index = Math.floor((Math.random() * colors.length));
                                const isLongLab = label.length > 5;
                                return <Tag key={num}
                                            color={colors[index]}>{isLongLab ? label.slice(0, 5) + '...' : label}</Tag>
                            }
                        )
                    )
                }
            }, {
                title: "基础标签",
                dataIndex: "baseLabel",
                key: "baseLabel",
                render: labels => {
                    return (
                        labels.map((label,num) => {
                                const colors = ["magenta", "volcano", "gold", "green", "blue", "purple", "red", "orange", "lime", "cyan", "geekblue",];
                                let index = Math.floor((Math.random() * colors.length));
                                const isLongLab = label.length > 5;
                                return <Tag key={num + 1}
                                            color={colors[index]}>{isLongLab ? label.slice(0, 5) + '...' : label}</Tag>
                            }
                        )
                    )
                }
            }, {
                title: "类型",
                dataIndex: "type",
                key: "type",
                render: type => {
                    const {caseType} = this.props.testCase;
                    return caseType.map((ct) => {
                        if (type === ct.code) {
                            return ct.name;
                        }
                    });
                }
            }, {
                title: "最后更新",
                dataIndex: "lastEditedBy",
                key: "lastEditedBy",
            }, {
                title: "更新时间",
                dataIndex: "lastEditedDate",
                key: "lastEditedDate",
                render: record => moment(record).format("YYYY-MM-DD HH:mm:ss")
            }, {
                title: "版本",
                dataIndex: "version",
                key: "version",
            },
        ];
        const {baseLabel, productModuleTreeDate, list, selectedRowKeys, selectedRows, hasMore, loading} = this.state;
        const {caseType,} = this.props.testCase;
        const {getFieldDecorator} = this.props.form;
        const {onCancel, view} = this.props;
        const renderTreeNode = (data) => data.map(node => {
            node.title = (
                <span onClick={() => {
                    this.searchBaseCaseList(node)
                }}>{node.name}</span>
            );
            if (node.children) {
                return <TreeNode title={node.title} key={node.id}
                                 style={{fontSize: 16}}>{renderTreeNode(node.children)}</TreeNode>
            }
            return <TreeNode title={node.title} key={node.id} style={{fontSize: 16}}/>
        });

        const rowSelection = {
            selectedRowKeys,
            selectedRows,
            onChange: this.onSelectChange,
        };
        return (
            <Modal visible={view}
                   onOk={this.doImport}
                   onCancel={onCancel}
                   title={"导入基线用例"}
                   width={'90%'}
                   style={{ top: 20 }}
                   okText={"导入"}
                   cancelText={"取消"}
            >
                <Row gutter={24}>
                    <Col span={4}>
                        <Tree showLine style={{overflowX:"scroll"}}>
                            {
                                renderTreeNode(productModuleTreeDate)
                            }
                        </Tree>
                    </Col>
                    <Col span={20}>
                        <Form onSubmit={this.doSearch}>
                            <Row>
                                <Col span={8}>
                                    <FormItem label={"用例标题"} {...formItemLayout}>
                                        {getFieldDecorator("title", {})(
                                            <Input/>
                                        )}
                                    </FormItem>
                                </Col>
                                <Col span={9}>
                                    <FormItem label={"标签"} {...formItemLayout}>
                                        <Select
                                            mode="multiple"
                                            style={{width: '100%'}}
                                            placeholder="标签"
                                            autoClearSearchValue
                                            filterOption={false}
                                            onSearch={this.fetchLabel}
                                            onSelect={this.handleChange}
                                            onDeselect={this.handleClose}
                                        >
                                            {
                                                baseLabel.map(b => (
                                                        <Select.Option key={b.id} value={b.name}>{b.name}</Select.Option>
                                                    )
                                                )
                                            }
                                        </Select>
                                    </FormItem>
                                </Col>
                            </Row>
                            <Row>
                                <Col span={8}>
                                    <FormItem label={"用例类型"} {...formItemLayout}>
                                        {getFieldDecorator("type", {})(
                                            <Select>
                                                {
                                                    caseType.map((t) => (
                                                        <Select.Option key={t.code}>{t.name}</Select.Option>
                                                    ))
                                                }
                                            </Select>
                                        )}
                                    </FormItem>
                                </Col>
                                <Col span={9}>
                                    <FormItem label={"最近更新人"} {...formItemLayout}>
                                        {getFieldDecorator("lastEditedBy", {})(<StaffCode/>
                                        )}
                                    </FormItem>
                                </Col>
                                <Col span={3}>
                                    <FormItem>
                                        <Button type="primary" icon="search" htmlType="submit">搜索</Button>
                                    </FormItem>
                                </Col>
                            </Row>
                        </Form>
                        <Row style={{marginBottom:2}}>
                            <span style={{ marginLeft: 8 }}>{selectedRows.length > 0 ? `Selected ${selectedRows.length} cases` : ''}
                            </span>
                        </Row>
                        <Row>
                            <div style={{height: "55vh", overflow: "auto"}}>
                                <InfiniteScroll
                                    initialLoad={false}
                                    pageStart={0}
                                    loadMore={this.fetchMoreData.bind(this)}
                                    hasMore={!loading && hasMore}
                                    useWindow={false}
                                >
                                    <Table dataSource={list}
                                           columns={caseColumns}
                                           rowSelection={rowSelection}
                                           rowKey={record =>record.id}
                                           pagination={false}
                                           loading={loading}
                                    />
                                    {loading && hasMore && (
                                        <div className={style["loading-container"]}>
                                            <Spin />
                                        </div>
                                    )}
                                    {!loading && !hasMore ? <div className={style["end-text"]}>---到底了，呵呵---</div> : ""}
                                </InfiniteScroll>
                            </div>
                        </Row>
                    </Col>
                </Row>
            </Modal>
        )
    }
}
