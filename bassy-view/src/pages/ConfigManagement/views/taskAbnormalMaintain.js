import {Button, Card, Col, Form, Input, message, Popconfirm, Row, Select, Spin, Table, Modal} from "antd";
import React from "react";
import {Ellipsis} from "ant-design-pro";
import styles from "./index.less";
import moment from "moment";
import {
    updateTaskLog, getStaffList, getTaskAbnormalListByCondition, invalidTaskLog,
    getAbnormalLog, getTasks
} from "../../../apis";
import {connect} from "react-redux";
import {actions} from "./../index";
import {parseStaffName} from "./../../../utils";
import {getabnormalReasonName, getDictValue} from "../../../utils";
import FetchZtRequire from "../../../components/FetchData/FetchZtRequire";


const FormItem = Form.Item;
const Option = Select.Option;
const formLayout = {
    labelCol: {span: 5},
    wrapperCol: {span: 15}
};

const EditableContext = React.createContext();
const EditableRow = ({form, index, ...props}) => (
    <EditableContext.Provider value={form}>
        <tr {...props} />
    </EditableContext.Provider>
);

const EditableFormRow = Form.create()(EditableRow);

@connect(state => ({
    abnormalReasonTeam: state.common.abnormalReasonTeam,
    abnormalReasonType: state.common.abnormalReasonType,
    selectedTeamCode: state.configManagement.selectedTeamCode,
}))
class TaskAbnormalEditableCell extends React.Component {
    state = {
        staffList: [{code: '', name: ''}]
    };

    changPersonKeyWord = async (input) => {
        if (input === undefined || input === "") {
            return;
        }
        const staffList = await getStaffList(input);
        if (staffList.data) {
            this.setState({staffList: staffList.data})
        }
    };

    selectReasonTeam = teamCode => {
        this.props.dispatch(actions.addTaskAbnormalSelectedTeamCode(teamCode));
    };

    getEditCell = () => {
        const {
            record,dataIndex,abnormalReasonTeam,abnormalReasonType,selectedTeamCode
        } = this.props;
        const {staffList} = this.state;
        if (dataIndex === 'reasonDetail') {
            return <Input.TextArea style={{width: 200}}/>;
        } else if (dataIndex === 'reasonTeamId') {
            return < Select
                style={{width: 100}}
                onChange={value => this.selectReasonTeam(value)}
            >
                {abnormalReasonTeam && abnormalReasonTeam.map((t) => (
                    <Option value={t.reasonTeamCode} key={t.reasonTeamCode}>
                        {t.reasonTeamName}
                    </Option>
                ))}
            </Select>;
        }else if (dataIndex === 'reasonTypeId') {
            return <Select
                style={{width: 160}}
            >
                {abnormalReasonType.map((s) => (
                    s.reasonTeamCode === selectedTeamCode &&
                    <Option value={s.reasonTypeCode} key={s.reasonTypeCode}>
                        {s.reasonTypeName}
                    </Option>
                ))}
            </Select>;
        }else if(dataIndex === "abnormalOwnerName"){
            return <Select
                showSearch
                placeholder="工号或姓名"
                onSearch={this.changPersonKeyWord}
                style={{width: 160}}
            >
                {staffList.map((s) => (
                    <Option value={"(" + s.code + ")" + s.name} key={s.code}>
                        {"(" + s.code + ")" + s.name}
                    </Option>
                ))}
            </Select>
        }
    };

    render() {
        const {
            editing,
            dataIndex,
            record,
            abnormalReasonTeam,
            abnormalReasonType,
            selectedTeamCode,
            dispatch,
            ...restProps
        } = this.props;
        let initVal;
        if(editing){
            if(dataIndex === 'reasonTeamId'){
                initVal = record["reasonTeamName"];
            }else if(dataIndex === "reasonTypeId"){
                initVal = record["reasonTypeName"];
            }else {
                initVal = record[dataIndex];
            }
        }
        return (
            <EditableContext.Consumer>
                {(form) => {
                    const {getFieldDecorator} = form;
                    return (
                        <td {...restProps}>
                            {editing ? (
                                <FormItem style={{margin: 0}}>
                                    {getFieldDecorator(dataIndex, {
                                        initialValue: initVal,
                                    })(this.getEditCell())}
                                </FormItem>
                            ) : restProps.children}
                        </td>
                    );
                }}
            </EditableContext.Consumer>
        );
    }
}

@Form.create()
@connect(state => ({
    abnormalReasonTeam: state.common.abnormalReasonTeam,
    abnormalReasonType: state.common.abnormalReasonType,
    allDict: state.allDict,
}))
export default class TaskAbnormalMaintain extends React.Component {
    state = {
        loading: false,
        staffList: [{code: '', name: ''}],
        taskAbnormalList: [],
        editingId: '',
        pageNum: 1,
        pageSize: 10,
        total: 0,
        editingItem: {},
        isDumplicateAbnormal: false,
        requireWithTasks: [],
        taskWithLogs: [],
    };
    columns = [/*{
        title: '编号',
        dataIndex: 'id',
        key: 'id',
    },*/ {
        title: '项目',
        dataIndex: 'projectName',
        render: (text, record, index) => {
            return "(" + record.projectId + ")" + record.projectName;
        }
    }, {
        title: '需求',
        dataIndex: 'storyTitle',
        render: (text, record, index) => {
            return "(" + record.storyId + ")" + record.storyTitle;
        }
    }, {
        title: '任务',
        dataIndex: 'taskName',
        render: (text, record, index) => {
            return "(" + record.taskId + ")" + record.taskName;
        }
    }, {
        title: '任务状态',
        dataIndex: 'taskStatus'
    }, {
        title: '一级原因',
        dataIndex: 'reasonTeamId',
        editable: true,
        render: (text, record, index) => {
            return record.reasonTeamName;
        }
    }, {
        title: '二级原因',
        dataIndex: 'reasonTypeId',
        editable: true,
        render: (text, record, index) => {
            return record.reasonTypeName;
        }
    }, {
        title: '严重等级',
        dataIndex: 'reasonLevel'
    }, {
        title: '异常说明',
        dataIndex: 'reasonDetail',
        width: 200,
        editable: true,
        render: (text, record, index) => {
            return <Ellipsis length={60} tooltip>{text}</Ellipsis>;
        }
    }, {
        title: '重复异常',
        dataIndex: 'dumplicateDetail',
    }, {
        title: '异常类型',
        dataIndex: 'abnormalType',
        render: (text, record, index) => {
            if (record.abnormalType === 0) {
                return "计划变更异常";
            } else if (record.abnormalType === 1) {
                return "任务进度异常";
            }
            return "null";
        }
    }, {
        title: '最新状态',
        dataIndex: 'currentNormalState',
        render: (text, record, index) => {
            return record.currentNormalState === 0 ? <span className={styles.abnormal}>异常</span> : "正常";
        }
    }, {
        title: '测试人员',
        dataIndex: 'testerName',
    }, {
        title: '责任人',
        dataIndex: 'abnormalOwnerName',
        editable:true
    }, {
        title: '更新时间',
        dataIndex: 'modifyTime',
        render: (text, record, index) => {
            return moment(text).format("YYYY-MM-DD HH:mm:ss");
        }
    }, {
        title: '操作',
        key: 'action',
        render: (text, record) => {
            const editable = this.isEditing(record);
            return (
                <div>
                    {editable ? (
                        <span>
                          <EditableContext.Consumer>
                            {form => (
                                <a
                                    href="javascript:;"
                                    onClick={() => this.save(form, record.id)}
                                    style={{marginRight: 8}}
                                >
                                    保存
                                </a>
                            )}
                          </EditableContext.Consumer>
                          <Popconfirm
                              title="Sure to cancel?"
                              onConfirm={() => this.cancel(record.id)}
                          >
                            <a>取消</a>
                          </Popconfirm>
                        </span>
                    ) : (
                        <span>
                            <Row style={{ marginBottom: 2 }}><Button type="primary" icon="edit" size="small" onClick={() => this.edit(record)}>编辑</Button></Row>
                            <Row style={{ marginBottom: 2 }}><Button type="primary" size="small" onClick={() => this.handleDuplicateClick(record)} icon="edit" >重复</Button></Row>
                            <Popconfirm title="确认作废?" onConfirm={() => this.deleteModel(record)} okText="确认"
                                        cancelText="取消">
                                <Row><Button type="danger" icon="delete" size="small">作废</Button></Row>
                            </Popconfirm>
                        </span>
                    )}
                </div>
            )
        }
    }];
    handleSearch = e => {
        e.preventDefault();
        this.props.form.validateFieldsAndScroll(async (err, values) => {
            if (!err) {
                console.log("Received values of form: ", values);
                this.getTaskAbnormalListByCondition(1, {...values, abnormalOwner: parseStaffName(values.abnormalOwner)});
            }
        });
    };
    handleReset = () => {
        this.props.form.resetFields()
    };
    changePage = page => {
        this.setState({pageNum: page});
        this.props.form.validateFieldsAndScroll(async (err, values) => {
            if (!err) {
                console.log("Received values of form: ", values);
                this.getTaskAbnormalListByCondition(page, {...values, abnormalOwner: parseStaffName(values.abnormalOwner)})
            }
        });
    };
    deleteModel = async (record) => {
        const res = await invalidTaskLog(record.id);
        if (res.data) {
            message.success('作废成功');
            this.props.form.validateFieldsAndScroll(async (err, values) => {
                if (!err) {
                    console.log("Received values of form: ", values);
                    this.getTaskAbnormalListByCondition(this.state.pageNum, {...values, abnormalOwner: parseStaffName(values.abnormalOwner)})
                }
            });
        } else {
            message.error(res.msg);
        }
    };
    getTaskAbnormalListByCondition = async (pageNum = 1, param = {}) => {
        let requestParameters = Object.assign({pageNum, pageSize: 10}, param);
        requestParameters.sDate = "2019-01-06";
        requestParameters.eDate = moment().add(1, 'days').format("YYYY-MM-DD");
        const res = await getTaskAbnormalListByCondition(requestParameters);
        const {currentPage, pageSize, list, totalCount} = res.data;
        this.setState({
            taskAbnormalList: list,
            pageSize,
            total: totalCount,
            pageNum: currentPage
        });
    };
    changPersonKeyWord = async (input) => {
        if (input === undefined || input === "") {
            return;
        }
        const staffList = await getStaffList(input);
        if (staffList.data) {
            this.setState({staffList: staffList.data})
        }
    };
    isEditing = record => record.id === this.state.editingId;
    cancel = () => {
        this.setState({editingId: ''});
    };
    save(form, id) {
        form.validateFieldsAndScroll(async (err, values) => {
            if (!err) {
                console.log("Received values of form: ", values);
                this.setState({editingId: ''});
                let obj = {};
                obj = Object.assign(obj, values, {id:id});
                const update = await updateTaskLog({...obj, abnormalOwnerName: parseStaffName(obj.abnormalOwnerName)});
                if (update.data) {
                    message.success("修改成功");
                    this.props.form.validateFieldsAndScroll(async (err, searchVals) => {
                        if (!err) {
                            this.getTaskAbnormalListByCondition(this.state.pageNum, {...searchVals, abnormalOwner: parseStaffName(searchVals.abnormalOwner)})
                        }
                    });
                } else {
                    message.error(insert.msg);
                }
            }
        });
    }

    edit(record) {
        this.setState({editingId: record.id});
        this.props.dispatch(actions.addTaskAbnormalSelectedTeamCode(record.reasonTeamId));
    }

    componentWillMount() {
        this.getTaskAbnormalListByCondition();
    }

    handleDuplicateClick = (record) => {
        this.setState({
            isDumplicateAbnormal: true,
            editingItem: record,
        });
    };
    //选中重复异常的关联需求后，获取需求关联的任务
    onFetchRequireChange = async(value) => {
        const {allDict} = this.props;
        this.props.form.setFieldsValue({"requires": value});
        let requireId = value.split(",")[0];
        let data = {pageNum:1, pageSize: 100, searchKey:requireId};
        const res = await getTasks(data);
        if (res.data && res.data.list.length !== 0) {
            let data = res.data.list.map(d => (
                {
                    text: `(${d.programName})[任务${d.id}] ${getDictValue(allDict, "TEST_TASK", d.taskCode) || getDictValue(allDict, "INNER_PROJECT_TYPE", d.taskCode)} (${d.testerName == null ? "无负责人" : d.testerName})`,
                    value: `${d.id}`,
                }));
            this.setState({
                requireWithTasks:data,
            });
        } else {
            message.error("查询出错!");
        }
    };

    //选择重复异常关联的任务后，获取任务的所有异常
    onSelectTask = async(value) => {
        const {abnormalReasonType} = this.props;
        let taskId = value.split(",")[0];
        const res = await getAbnormalLog(taskId);
        if (res.data) {
            let data = res.data.map(d => (
                {
                    text: `[${getabnormalReasonName(abnormalReasonType,d.reasonType).reasonTeamName}-${getabnormalReasonName(abnormalReasonType,d.reasonType).reasonTypeName}-${d.reasonLevel}] ${d.reasonDetail} (责任人:  ${d.abnormalOwner == null ? "无" : d.abnormalOwner } )`,
                    value: `${d.id},${d.reasonTeam},${d.reasonType},${d.reasonLevel},${d.reasonDetail},${d.abnormalOwner},${d.abnormalDepart}`,
                }));
            this.setState({
                taskWithLogs:data,
            });
            this.props.form.setFieldsValue({"abnormal": {key:data[0].value}});
        } else {
            message.error("查询出错!");
        }
    };

    handleDuplicateClose = () => {
        // this.props.form.resetFields();
        this.setState({
            isDumplicateAbnormal: false,
        });
    };

    submitDumplicate = () => {
        this.props.form.validateFieldsAndScroll(async (err, values) => {
            let obj = {};
            obj = Object.assign(obj, values, {id : this.state.editingItem.id});
            if(this.state.isDumplicateAbnormal) {
                obj.duplicateAbnormal = values.abnormal.key.split(",")[0];
                obj.reasonTeamId = values.abnormal.key.split(",")[1];
                obj.reasonTypeId = values.abnormal.key.split(",")[2];
                obj.reasonLevel = values.abnormal.key.split(",")[3];
                obj.reasonDetail = values.abnormal.key.split(",")[4];
                obj.abnormalOwnerName = values.abnormal.key.split(",")[5];
            }
            const update = await updateTaskLog({...obj});
            if (update.data) {
                message.success("修改成功");
                this.setState({isDumplicateAbnormal: false});
                this.props.form.validateFieldsAndScroll(async (err, searchVals) => {
                    if (!err) {
                        this.getTaskAbnormalListByCondition(this.state.pageNum, {...searchVals, abnormalOwner: parseStaffName(searchVals.abnormalOwner)})
                    }
                });
            } else {
                message.error(insert.msg);
            }
        })
    };

    render() {
        const {loading, staffList, taskAbnormalList, pageNum, pageSize, total, isDumplicateAbnormal, requireWithTasks, taskWithLogs} = this.state;
        const {getFieldDecorator} = this.props.form;
        const {abnormalReasonTeam} = this.props;
        const components = {
            body: {
                row: EditableFormRow,
                cell: TaskAbnormalEditableCell,
            },
        };

        const columns = this.columns.map((col) => {
            if (!col.editable) {
                return col;
            }
            return {
                ...col,
                onCell: record => ({
                    record,
                    dataIndex: col.dataIndex,
                    editing: this.isEditing(record),
                }),
            };
        });

        return (
            <div>
                <Form
                    layout="inline"
                    onSubmit={this.handleSearch}
                    style={{marginBottom: 12}}
                >
                    <Row type="flex" justify="start" align="bottom">
                        <Col span={24}>
                            <FormItem>
                                {getFieldDecorator("story")(<Input placeholder="支持根据需求id/名,任务id搜索" style={{width: 220}}/>)}
                            </FormItem>
                            <FormItem label="变更原因">
                                {getFieldDecorator('reasonTeam')(
                                    < Select
                                        style={{width: 100}}
                                    >
                                        {abnormalReasonTeam.map((t) => (
                                            <Option value={t.reasonTeamCode} key={t.reasonTeamCode}>
                                                {t.reasonTeamName}
                                            </Option>
                                        ))}
                                    </Select>
                                )}
                            </FormItem>
                            <FormItem label="责任人/测试">
                                {getFieldDecorator("abnormalOwner")(
                                    <Select
                                        showSearch
                                        placeholder="工号或姓名"
                                        onSearch={this.changPersonKeyWord}
                                        style={{width: 120}}
                                    >
                                        {staffList.map((s) => (
                                            <Option value={"(" + s.code + ")" + s.name} key={s.code}>
                                                {"(" + s.code + ")" + s.name}
                                            </Option>
                                        ))}
                                    </Select>
                                )}
                            </FormItem>
                            <FormItem label="异常类型">
                                {getFieldDecorator('abnormalType')(
                                    < Select
                                        style={{width: 130}}
                                    >
                                        <Option value={0} key={0}>
                                            计划变更异常
                                        </Option>
                                        <Option value={1} key={1}>
                                            任务进度异常
                                        </Option>
                                    </Select>
                                )}
                            </FormItem>
                            <FormItem>
                                <Button type="primary" htmlType="submit">
                                    搜索
                                </Button>
                            </FormItem>
                            <FormItem>
                                <Button type="primary" onClick={this.handleReset}>
                                    重置
                                </Button>
                            </FormItem>
                        </Col>
                    </Row>
                </Form>
                <Spin spinning={loading}>
                    <Card title="所有异常问题">
                        <Table
                            rowKey="id"
                            components={components}
                            columns={columns}
                            dataSource={taskAbnormalList}
                            rowClassName="editable-row"
                            scroll={{x:true}}
                            pagination={{
                                current: pageNum,
                                total,
                                pageSize,
                                onChange: this.changePage
                            }}
                        />
                    </Card>
                </Spin>
                <Modal onOk={this.submitDumplicate} onCancel={this.handleDuplicateClose} width={'50%'} visible={isDumplicateAbnormal} title={'编辑重复异常'} destroyOnClose={true}>
                    <FormItem label={"相关需求"}  {...formLayout}>
                        {getFieldDecorator('requires', {
                            rules: [{required: isDumplicateAbnormal, message: '该字段不能为空!'}]
                        })(
                            <FetchZtRequire disabled={false} handleSelect={this.onFetchRequireChange}/>
                        )}
                    </FormItem>
                    <FormItem label={"相关任务"}  {...formLayout}>
                        {getFieldDecorator('tasks', {
                            rules: [{required: isDumplicateAbnormal, message: '该字段不能为空!'}]
                        })(
                            <Select
                                placeholder="选择该需求下的任务"
                                filterOption={false}
                                showArrow={false}
                                onSelect={value => this.onSelectTask(value)}
                                style={{width: '100%'}}
                            >
                                {requireWithTasks.map(fetch =>
                                    <Option key={fetch.value}>{fetch.text}</Option>
                                )}
                            </Select>
                        )}
                    </FormItem>
                    <FormItem label={"相关异常"}  {...formLayout}>
                        {getFieldDecorator('abnormal', {
                            rules: [{required: isDumplicateAbnormal, message: '该字段不能为空!'}]
                        })(
                            <Select
                                labelInValue
                                placeholder="选择任务下的重复异常"
                                filterOption={false}
                                showArrow={false}
                                style={{width: '100%'}}
                            >
                                {taskWithLogs.map(fetch =>
                                    <Option key={fetch.value} title={fetch.text}>{fetch.text}</Option>
                                )}
                            </Select>
                        )}
                    </FormItem>
                </Modal>
            </div>);
    }
}

