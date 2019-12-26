import React from 'react'
import moment from "moment";
import {connect} from "react-redux";
import {Button, Card, DatePicker, Form, Select, Table} from "antd";
import {getProgramTasks} from "../../apis/index";

const Option = Select.Option
const FormItem = Form.Item;

const {RangePicker} = DatePicker;

@Form.create()
@connect(state => ({
    allDict: state.allDict,
    taskStatus: state.common.taskStatus
}))
export default class ProgramTaskDetail extends React.Component {
    state = {
        loading: false,
        list: [],
        pageNum: 1,
        pageSize: 10,
        total: 0
    };
    getUpdateHistory = async (item) => {
        this.props.history.push({
            pathname: '/task/updateTaskHistory',
            state: {
                taskId: item.id,
                programId: this.props.location.state.id,
                taskCode: item.taskCode
            }
        })
    }
    columns = [
        {
            title: "任务Id",
            dataIndex: "id",
            key: "id"
        },
        {
            title: "任务名称",
            key: "taskCode",
            render: item => {
                if(item.taskName != null) {
                    return item.taskName;
                } else {
                    return this.props.allDict.map((s) => {
                        if ("TEST_TASK,INNER_PROJECT_TYPE".includes(s.dictGroup) && s.dictCode === item.taskCode) {
                            return s.dictValue
                        }
                    })
                }
            },
        },
        {
            title: "关联需求",
            key: "requireId",
            dataIndex: "requireId",
        },
        {
            title: "任务状态",
            dataIndex: "status",
            render: record => {
                return this.props.taskStatus.map((s) => {
                    if (s.code === record) {
                        return s.name
                    }
                })
            }

        },
        {
            title: "开始时间",
            dataIndex: "startTm",
            render: record => moment(record).format("YYYY-MM-DD")
        },
        {
            title: "结束时间",
            dataIndex: "endTm",
            render: record => moment(record).format("YYYY-MM-DD")
        },
        {
            title: "处理人",
            dataIndex: "tester",
        },
        {
            title: "总工时",
            dataIndex: "expectHour",
        },
        {
            title: "当前工时",
            dataIndex: "actualHour",
        },
        {
            title: "实际进度",
            dataIndex: "percent",
            render: record => {
                return record == null ? "" : record + "%"
            }
        },
        {
            title: "进度状态",
            dataIndex: "isNormal",
            render: record => {
                if (record === 1) {
                    return "正常"
                } else if (record === 0) {
                    return "异常"
                } else {
                    return ""
                }
            }
        },
        {
            title: "更新明细",
            render: item => {
                return (
                    <Button type="primary" size="small" onClick={() => this.getUpdateHistory(item)}>历史更新记录</Button>
                )
            }
        }
    ];
    getProgramTaskList = async (pageNum = 1, programId = 1, status = '', startTm, endTm) => {
        const data = {pageNum, pageSize: 10, programId, status, startTm, endTm};
        const res = await getProgramTasks(data);
        console.info("tasks:" + res)
        const {currentPage, pageSize, list, totalCount} = res.data;
        this.setState({
            list,
            pageSize,
            total: totalCount,
            pageNum: currentPage
        });
    }
    changePage = page => {
        const programId = this.props.location.state.id;
        this.getProgramTaskList(page, programId);
    };
    handleReset = () => {
        this.props.form.resetFields()
    }
    handleSearch = e => {
        e.preventDefault();
        console.log(this.props.form.getFieldsValue());
        const {status, startEndTm} = this.props.form.getFieldsValue();
        const programId = this.props.location.state.id;
        const {pageNum} = this.state;
        if (startEndTm !== undefined) {
            this.getProgramTaskList(pageNum, programId, status, startEndTm[0]._d, startEndTm[1]._d);
        } else {
            this.getProgramTaskList(pageNum, programId, status);
        }
    };

    componentWillMount() {
        const programId = this.props.location.state.id;
        const pageNum = 1;
        this.getProgramTaskList(pageNum, programId);
    }

    render() {
        const {getFieldDecorator} = this.props.form;
        const {programName} = this.props.location.state
        const {taskStatus, allDict} = this.props
        const {
            list,
            pageNum,
            pageSize,
            total
        } = this.state;

        return (
            <Card title={programName}>
                <Form
                    layout="inline"
                    onSubmit={this.handleSearch}
                    style={{marginBottom: 12}}
                >
                    <FormItem label="任务状态">
                        {getFieldDecorator("status")(
                            <Select
                                style={{width: 150}}
                            >
                                {taskStatus.map(v => <Option key={v.code}>{v.name}</Option>)}
                            </Select>
                        )}
                    </FormItem>
                    <FormItem label="时间区间">
                        {getFieldDecorator("startEndTm", {
                            rule: [
                                {
                                    required: false
                                }
                            ]
                        })(<RangePicker
                            style={{
                                display: "inline-block",
                                marginLeft: "6"
                            }}
                        />)}
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
                </Form>

                <Table
                    rowKey="id"
                    bordered
                    columns={this.columns}
                    dataSource={list}
                    pagination={{
                        current: pageNum,
                        total,
                        pageSize,
                        onChange: this.changePage
                    }}
                />
            </Card>
        )
    }
}