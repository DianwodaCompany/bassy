import React from 'react'
import {connect} from 'react-redux'
import CSSModules from 'react-css-modules'
import styles from './index.less'
import {ASYNC_GET_ALL_ENABLE_PROCESS_MODULES, ASYNC_GET_PROGRAM_TYPE} from "../../actionTypes/index";
import {deleteProcessModule} from "../../apis/index";
import {haveAdminAuth} from "../../utils";

import {Button, Card, Col, Form, message, Modal, Popconfirm, Row, Select, Spin, Table,} from 'antd'

const FormItem = Form.Item
const Option = Select.Option

const categories = {
    'program': '立项项目',
    'inner': '内部项目',
    'urgent': '紧急项目',
    'normal': '常规项目',
}

@CSSModules(styles)
@connect(state => ({
    programTypes: state.programTypes,
    processModules: state.processModules,
    projectTemplates: state.projectTemplates,
    authResourceCodeList: state.common.authResourceCodeList,
}))
export default class ProcessMode extends React.Component {
    state = {
        models: [],
        loading: false,
        loading_m: false,
        showVisible: false,
        addVisible: false,
        templateBigType: '',
        current: 1,
        list: [],
        pageNum: 1,
        pageSize: 10,
        total: 0
    };

    getAllEnableModules = async (pageNum = 1) => {
        this.props.dispatch({
            type: ASYNC_GET_ALL_ENABLE_PROCESS_MODULES,
            payload: {
                pageNum,
                pageSize: 10,
            }
        });
    }
    deleteModel = async (item) => {
        const res = await deleteProcessModule(item.id);
        if (res.data) {
            message.success('删除成功');
            this.getAllEnableModules();
        } else {
            message.error(res.msg);
        }
    }
    editModel = item => {
        this.props.history.push({
            pathname: '/processMode/edit/' + item.id,
            state: {
                id: item.id,
                programModuleId: item.programModuleEntity.id,
                style: item.programModuleEntity.parentCode,
            }
        })
    }
    showModel = item => {
        this.props.history.push({
            pathname: '/processMode/detail/' + item.id,
            state: {
                id: item.id,
                programModuleId: item.programModuleEntity.id,
                check: true,
                style: item.programModuleEntity.parentCode
            }
        })
    }
    columns = [{
        title: '模板编号',
        dataIndex: 'id',
        key: 'id',
    }, {
        title: '项目大类',
        key: 'category',
        render: item => {
            return categories[item.programModuleEntity.parentCode]
        }
    }, {
        title: '项目模板名称',
        dataIndex: 'programModuleEntity.moduleName',
        key: 'programModuleEntity.moduleName',
    }, {
        title: '流程模板名称',
        dataIndex: 'moduleName',
        key: 'moduleName',
    }, {
        title: '操作',
        key: 'action',
        render: item => {
            return (
                <span>
                    <Button type="primary" icon="eye" size="small"
                            onClick={() => this.showModel(item)}>查看</Button> &nbsp;&nbsp;
                    {item.readOnly === 'N' && haveAdminAuth(this.props.authResourceCodeList) &&
                    <Button type="primary" icon="edit" size="small"
                            onClick={() => this.editModel(item)}>编辑</Button>} &nbsp;&nbsp;
                    {item.readOnly === 'N' && haveAdminAuth(this.props.authResourceCodeList) &&
                    <Popconfirm title="确认删除?" onConfirm={() => this.deleteModel(item)} okText="确认" cancelText="取消">
                        <Button type="primary" icon="delete" size="small">删除</Button>
                    </Popconfirm>
                    }
                </span>
            )
        }
    }]
    addModel = () => {
        this.setState({
            addVisible: true
        })
    }
    doAddModel = () => {
        const {templateBigType} = this.state
        this.props.history.push({
            pathname: '/processMode/add',
            state: {
                style: templateBigType
            }
        })
    }
    modelChange = v => {
        this.setState({
            templateBigType: v
        }, () => {
            console.log(this.state.templateBigType)
        })
    }
    changeNum = (num) => {
        this.getAllEnableModules(num)
    }

    componentWillMount() {
        this.getAllEnableModules();
        this.props.dispatch({
            type: ASYNC_GET_PROGRAM_TYPE
        })
    }

    componentDidMount() {

    }

    render() {
        const {programTypes, processModules, programModules, authResourceCodeList} = this.props
        delete programTypes.inner
        const {models, loading, loading_m, showVisible, addVisible} = this.state
        const {list, currentPage, totalCount, pageSize} = processModules
        return (
            <Spin spinning={loading}>
                <Card>
                  { haveAdminAuth(authResourceCodeList) &&
                    <Button
                        style={{marginBottom: '16px', marginRight: '12px'}}
                        type="primary"
                        icon="plus"
                        onClick={() => this.addModel()}
                    >添加流程模版
                    </Button>
                  }
                    <Table
                        rowKey=""
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
                    title={'新增流程模版'}
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
                                onChange={this.modelChange}
                                style={{width: 200}}
                                placeholder="选择项目模版类型"
                                optionFilterProp="children"
                            >
                                {programTypes && Object.keys(programTypes).map((m, i) =>
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
            </Spin>

        )
    }
}
