import React from 'react'
import {connect} from 'react-redux'
import {Button, Card, Col, message, Modal, Popconfirm, Row, Select, Spin, Table} from 'antd'
import {deleteModule} from '../../../apis/index'
import {
    addProjectTemplateDetail,
    addProjectTemplateMode
} from '../actions.js';
import {
    addAllEnableProjectTemplates,
    addProjectBigTypes,
} from '../../Common/actions.js';
import {getAllEnableModules, getProgramModuleById, getProgramType} from "../../../apis";
import {haveAdminAuth} from "../../../utils";
import {addCurrentView} from "../../Common/actions";

const Option = Select.Option;

const categories = {
    'program': '立项项目',
    'inner': '内部项目',
    'urgent': '紧急项目',
    'normal': '常规项目',
};

@connect(state => ({
    currentView: state.common.currentView,
    projectBigTypes: state.common.projectBigTypes,
    allEnableProjectTemplates: state.common.allEnableProjectTemplates,
    authResourceCodeList: state.common.authResourceCodeList,
}))
export default class ProjectTemplate extends React.Component {
    state = {
        models: [],
        loading: false,
        loading_m: false,
        showVisible: false,
        addVisible: false,
        templateBigType: '',
        current: 1,
        total: 0,
        pageSize: 10,
    };

    getAllEnableProjectTemplates = async (pageNum = 1) => {
        let params = {
            pageNum,
            pageSize: 10,
        };
        let result = await getAllEnableModules(params);
        this.props.dispatch(addAllEnableProjectTemplates(result.data));
    };
    changeNum = (pageNum) => {
        this.getAllEnableProjectTemplates(pageNum)
    };
    /**
     * 删除模板
     */
    deleteModel = async (item) => {
        const res = await deleteModule(item.id);
        if (res.data) {
            message.success('删除成功');
            this.getAllEnableProjectTemplates();
        } else {
            message.error(res.msg);
        }
    };
    editModel = async (item) => {
        let result = await getProgramModuleById(item.id);
        this.props.dispatch(addProjectTemplateDetail(result.data));
        this.props.dispatch(addProjectTemplateMode("edit"));
        this.props.history.push({
            pathname: '/projectTemplate/edit/' + item.id
        })
    };
    showModel = async (item) => {
        let result = await getProgramModuleById(item.id);
        this.props.dispatch(addProjectTemplateDetail(result.data));
        this.props.dispatch(addProjectTemplateMode("view"));
        this.props.history.push({
            pathname: '/projectTemplate/detail/' + item.id
        })
    };

    columns = [{
        title: '模板编号',
        dataIndex: 'id',
        key: 'id',
    }, {
        title: '项目大类',
        key: 'category',
        render: item => {
            return categories[item.parentCode]
        }
    }, {
        title: '项目模版名称',
        dataIndex: 'moduleName',
        key: 'moduleName',
    }, {
        title: '定义',
        dataIndex: 'description',
        key: 'description',
    }, {
        title: '操作',
        key: 'action',
        render: item => {
            return (
                <span>
                  <Button type="primary" icon="eye" size="small" onClick={() => this.showModel(item)}>查看</Button>&nbsp;&nbsp;
                    {item.readOnly === 'N' && haveAdminAuth(this.props.authResourceCodeList) &&
                    <Button type="primary" size="small" icon="edit"
                            onClick={() => this.editModel(item)}>编辑</Button>} &nbsp;&nbsp;
                    {item.readOnly === 'N' && haveAdminAuth(this.props.authResourceCodeList) &&
                    <Popconfirm title="确认删除?" onConfirm={() => this.deleteModel(item)} okText="确认" cancelText="取消">
                        <Button type="primary" icon="delete" size="small">删除</Button>
                    </Popconfirm>
                    }
        </span>
            )
        }
    }];
    addModel = () => {
        this.setState({
            addVisible: true
        })
    };
    doAddModel = () => {
        this.props.dispatch(addProjectTemplateMode("add"));
        const {templateBigType} = this.state;
        this.props.dispatch(addProjectTemplateDetail(
            {
                parentCode: templateBigType,
                isLoop: "N",
                workId: "",
                coreNodes: [{"projectNode":"","startTime":"","endTime":""}]
            }
        ));
        this.props.history.push({
            pathname: '/projectTemplate/add'
        })
    };
    onTemplateBigTypeChange = v => {
        this.setState({
            templateBigType: v
        })
    };

    componentWillMount() {
        if(this.props.currentView !== "ProjectTemplate"){
            this.props.dispatch(addCurrentView("ProjectTemplate"));
        }
        this.getAllEnableProjectTemplates();
    }

    componentDidMount() {
        this.setState({
            models: [
                {
                    index: 1,
                    category: '内部',
                    name: '默认',
                    description: '这是内部项目默认模版'
                }, {
                    index: 2,
                    category: '立项',
                    name: '默认',
                    description: '这是立项项目默认模版'
                }, {
                    index: 3,
                    category: '紧急',
                    name: '默认',
                    description: '这是紧急项目默认模版'
                }, {
                    index: 4,
                    category: '常规',
                    name: '默认',
                    description: '这是常规项目默认模版'
                }, {
                    index: 5,
                    category: '内部',
                    name: '内部默认-1',
                    description: '这是内部项目默认模版-1'
                }
            ]
        })
    }

    render() {
        const {allEnableProjectTemplates, projectBigTypes, authResourceCodeList} = this.props;
        const {models, loading, loading_m, showVisible, addVisible} = this.state;
        if (!allEnableProjectTemplates || !projectBigTypes) return (null);
        const {list, currentPage, totalCount, pageSize} = allEnableProjectTemplates;
        return (
            <Spin spinning={loading}>
                <Card>
                  { haveAdminAuth(authResourceCodeList) &&
                    <Button
                        style={{marginBottom: '16px', marginRight: '12px'}}
                        type="primary"
                        icon="plus"
                        onClick={() => this.addModel()}
                    >添加项目模版
                    </Button>
                  }
                    <Table
                        rowKey="id"
                        columns={this.columns}
                        dataSource={list}
                        pagination={{
                            onChange: this.changeNum,
                            total: totalCount,
                            current: currentPage,
                            pageSize: pageSize,
                        }}
                    />
                </Card>
                <Modal
                    title={'新增项目模版'}
                    visible={addVisible}
                    footer={null}
                    width={600}
                    onCancel={() => this.setState({addVisible: false})}
                >
                    <Row type="flex" justify="center">
                        <Col span={16}>
                            <label>模版类型：</label>
                            <Select
                                id="selectType"
                                showSearch
                                rowKey="id"
                                onChange={this.onTemplateBigTypeChange}
                                style={{width: 200}}
                                placeholder="选择项目模版类型"
                                optionFilterProp="children"
                            >
                                {projectBigTypes && Object.keys(projectBigTypes).map((m, i) =>
                                    <Option value={m} key={i}>{categories[m]}</Option>
                                )}

                            </Select>
                        </Col>
                    </Row>
                    <Row type="flex" justify="end" style={{marginTop: '36px'}}>
                        <Col span={4}>
                            <Button
                                style={{marginRight: '8px'}}
                                onClick={() => this.setState({addVisible: false})}
                            >取消</Button>
                        </Col>
                        <Col span={4}>
                            <Button
                                style={{marginRight: '12px'}}
                                type="primary"
                                onClick={() => this.doAddModel()}
                            >确认</Button>
                        </Col>
                    </Row>
                </Modal>
                <Modal
                    title={'查看模版'}
                    visible={showVisible}
                    footer={null}
                    width={800}
                    //onOk={this.addUnitConfig}
                    onCancel={() => this.setState({showVisible: false})}
                >
                    <Spin spinning={loading_m}>
                    </Spin>
                </Modal>
            </Spin>
        )
    }
}
