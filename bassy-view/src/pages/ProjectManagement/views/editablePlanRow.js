import React from 'react';
import {
    Table,
    Button,
    Select,
    Form,
    DatePicker,
    InputNumber
} from 'antd';
import {connect} from "react-redux";
import {getStaffList} from "../../../apis/index";
import moment from "moment/moment";
import {batchEditTestPlan, getTestPlan} from "../../../apis";
import {message} from "antd/lib/index";

const Option = Select.Option;
const FormItem = Form.Item;

@connect(state => ({
    staffInfo: state.common.staffInfo,
    testPlan: state.testPlan,
    allDict: state.allDict,
    abnormalReasonTeam: state.common.abnormalReasonTeam,
    abnormalReasonType: state.common.abnormalReasonType,
    allTasksWithProcess: state.common.allTasksWithProcess
}))
@Form.create()
export default class EditableTestPlan extends React.Component {
    constructor(props) {
        super(props);

        this.state = {
            newPlan: props.editing, // 用于接收父组件传过来的参数
            staffList: [{code: '', name: ''}],
        };
    }

    componentWillReceiveProps(nextProps) {
        this.setState({newPlan: this.state.newPlan});
    }

    changPersonKeyWord = async (input) => {
        if (input === undefined || input === "") {
            return;
        }
        const staffList = await getStaffList(input);
        if (staffList.data) {
            this.setState({staffList: staffList.data})
        }
    };

    handlePlanChange = (index, fieldName, value) => {
        const {newPlan} = this.state;
        var findIndex  = newPlan.findIndex( item => {
            return item.id == index
        });
        newPlan[findIndex][fieldName] = value;
    };

    doBatchEdit = e => {
        e.preventDefault();
        this.props.form.validateFieldsAndScroll(
            async (err, values) => {
                if (!err) {
                    const { newPlan } = this.state;
                    let batch = [];
                    for(let i in newPlan) {
                        let r = newPlan[i];
                        r.modifier = this.props.staffInfo.code;
                        batch.push(r);
                    }
                    let resp = await batchEditTestPlan(batch);
                    if(resp.data) {
                        message.success("成功" + resp.data.successCount + "条，失败" + resp.data.failCount + "条");
                        const res = await getTestPlan(newPlan[0].programId);
                        this.props.onCancel();
                        this.props.getNewPlan(res.data);
                    }
                }
            })
    };

    render() {

        const {
            allDict,
            projectBigType,
        } = this.props;
        const { staffList } = this.state;
        const taskTitle = projectBigType === "inner" ? "任务名称" : "阶段任务";
        const taskKey = projectBigType === "inner" ? "taskName" : "taskCode";
        const columns = [{
                title: '关联需求',
                key: 'requireId',
                width: '15%',
                render: (text, record, index) => record.requireId == null ? "无" : "[" + record.requireId + "]" + record.requireRelate,
            }, {
                title: taskTitle,
                key: taskKey,
                width: '11%',
                render: (text, record, index) => {
                    if (projectBigType !== "inner") {
                        let val = "";
                        for(var v of allDict){
                            if (v.dictCode === record.taskCode) {
                                val = v.dictValue;
                                break;
                            }
                        }
                        return val;
                    }
                    else {
                        return record.taskName;
                    }
                },
            },
                {
                    title: '开始/结束时间',
                    key: 'startEndTime',
                    width: '25%',
                    render: (text, record, index) => {
                        return (
                            <FormItem style={{ margin: 0 }}>
                                {this.props.form.getFieldDecorator("startEndTime_" + record.id, {
                                    rules: [{
                                        required: true,
                                        message: "时间不能为空！"
                                    }],
                                    getValueFromEvent: val => {
                                        this.handlePlanChange(record.id, "startTm", new Date(moment(val[0]._d).startOf('day').format('YYYY-MM-DD HH:mm:ss')));
                                        this.handlePlanChange(record.id, "endTm", new Date(moment(val[1]._d).endOf('day').format('YYYY-MM-DD HH:mm:ss')));
                                        return val;
                                        },
                                    initialValue: [moment(record.startTm, 'YYYY-MM-DD'), moment(record.endTm, 'YYYY-MM-DD')],
                                })(
                                    <DatePicker.RangePicker/>
                                )}
                            </FormItem>
                        )
                    }
                },
                {
                    title: '测试负责人',
                    key: 'testerName',
                    width: '20%',
                    render: (text, record, index) => {
                        return (
                            <FormItem style={{ margin: 0}}>
                                {this.props.form.getFieldDecorator("tester_" + record.id, {
                                    rules: [{
                                        required: true,
                                        message: "负责人不能为空！"
                                    }],
                                    getValueFromEvent: val => {
                                        this.handlePlanChange(record.id, "tester", /\((\d+)\)/.exec(val)[1]);
                                        this.handlePlanChange(record.id, "testerName", /[\u4e00-\u9fa5]{0,}$/.exec(val)[0]);
                                        console.log(/[\u4e00-\u9fa5]{0,}$/.exec(val)[0]);
                                        return val;
                                    },
                                    initialValue: record.tester ? "(" + record.tester + ")" + record.testerName : undefined
                                })(
                                    <Select
                                        showSearch
                                        placeholder="工号或姓名"
                                        onSearch={this.changPersonKeyWord}
                                        style={{width: 150}}
                                    >
                                        {staffList.map((s) => (
                                            <Option value={`(${s.code})${s.name}`} key={s.code}>
                                                {"(" + s.code + ")" + s.name}
                                            </Option>
                                        ))}
                                    </Select>
                                )}
                            </FormItem>
                        )
                    }
                },
                {
                    title: '工时(H)',
                    key: 'expectHour',
                    width: '6%',
                    render: (text, record, index) => {
                        return (
                            <FormItem style={{ margin: 0 }}>
                                {this.props.form.getFieldDecorator("expectHour_" + record.id, {
                                    rules: [
                                        {
                                            required: true,
                                            message: "工时不能为空！"
                                        }
                                    ],
                                    getValueFromEvent: val => {
                                        this.handlePlanChange(record.id, "expectHour", val);
                                        return val;
                                    },
                                    initialValue: record.expectHour
                                })(<InputNumber step={0.5}/>)}
                            </FormItem>
                        );
                    }
                },
            ];

        const { newPlan } = this.state;
        return (
            <Form layout="inline" onSubmit={this.doBatchEdit}>
                <Table
                    bordered
                    rowKey={record => record.id}
                    columns={columns}
                    dataSource={newPlan}
                    pagination={false}
                    rowClassName="editable-row"
                    scroll={{ y: 400 }}
                />
                <Button type="primary" htmlType="submit" style={{ marginLeft: '40%', marginTop: '1%' }}>
                    提交
                </Button>
                <Button style={{ marginLeft: 8 }} onClick={this.props.onCancel}>
                    取消
                </Button>
            </Form>
        );
    }
}
