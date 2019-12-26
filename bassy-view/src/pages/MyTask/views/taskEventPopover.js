import React from 'react'
import {Button, message, Popover, Table} from 'antd';
import {connect} from "react-redux";
import {startTask} from "../../../apis/index";

@connect(state => ({
    staffInfo: state.common.staffInfo,
    allDict: state.allDict,
    taskStatus: state.common.taskStatus,
}))
export default class TaskEventPopover extends React.Component {
    state = {};
    editCustomTask = (record) => {
        this.props.event.editCustomTask(record);
    };
    updateTaskRate = async (record) => {
        this.props.event.history.push({
            pathname: '/myTask/updateTask',
            state: {
                taskId: record.id,
                taskCode: record.taskCode,
                taskName: record.taskName,
                callSource: "myTask",
            }
        })
    };
    startTask = async (record, status) => {
        if (record.tester === "" || record.tester == null) {
            message.info('未分配负责人，无法开始任务哦~');
            return;
        }
        const params = {
            taskId: record.id,
            status,
            modifier: this.props.staffInfo.code
        };
        const res = await startTask(params);
        if (res.errCode === 1) {
            message.success("开始任务成功！");
            this.props.event.refreshCalendarTask();
        } else {
            message.error("开始任务失败！")
        }
    };
    setTaskStatus = async (record, state) => {
        this.props.event.history.push({
            pathname: '/myTask/updateTask',
            state: {
                taskId: record.id,
                taskStatus: state,
                isNormal: state === 'end' ? 1 : 0,
                taskCode: record.taskCode,
                taskName: record.taskName,
                callSource: "myTask",
            }
        })
    };
    getUpdateHistory = async (record) => {
        this.props.event.history.push({
            pathname: '/myTask/updateTaskHistory',
            state: {
                taskId: record.id,
                taskCode: record.taskCode,
                taskName: record.taskName,
                callSource: "myTask",
                programId: record.programId,
            }
        })
    };
    columns = [
        {
            title: "所属项目",
            key: "programName",
            render: (text, record, index) => {
                return !record.programId ? "无关联项目" : "(" + record.programId + ")" + record.programName;
            }
        },
        {
            title: "关联需求",
            key: "requireId",
            dataIndex: "requireId",
            render: (text, record, index) => {
                if (record.requireId) {
                    return "(" + record.requireId + ")" + record.requireRelate;
                }
                else {
                    return "";
                }
            }
        },
        {
            title: "任务",
            key: "taskCode",
            render: (text, record, index) => {
                if (record.taskName != null) return "(" + record.id + ")" + record.taskName;
                else {
                    return this.props.allDict.map((s) => {
                        if ("TEST_TASK,INNER_PROJECT_TYPE".includes(s.dictGroup) && s.dictCode === record.taskCode) {
                            return "(" + record.id + ")" + s.dictValue;
                        }
                    })
                }
            }
        },
        {
            title: "任务状态(进度)",
            key: "status",
            dataIndex: "status",
            render: (text, record, index) => {
                return this.props.taskStatus.map((s) => {
                    if (s.code === text) {
                        if (text === "init") {
                            return s.name;
                        } else {
                            return s.name + "(" + record.percent + "%)";
                        }
                    }
                })
            }
        },
        {
            title: "操作",
            key: 'action',
            render: (text, record, index) => {
                return (
                    <span>
                        {record.status === "processing" &&
                        <Button type="primary" size="small" style={{marginLeft: 4}}
                                onClick={() => this.updateTaskRate(record)}>更新进度</Button>}
                        {record.programId === 0 && record.status !== "close" && record.status !== "end" &&
                        <Button type="primary" size="small" style={{marginLeft: 4}}
                                onClick={() => this.editCustomTask(record)}>编辑</Button>}
                        {record.status !== "processing" && record.status !== "close" && record.status !== "end" &&
                        <Button type="primary" size="small" style={{marginLeft: 4}}
                                onClick={() => this.startTask(record, "processing")}>开始</Button>}
                        {record.status === "processing" && record.status !== "pause" &&
                        <Button type="primary" size="small" style={{marginLeft: 4}}
                                onClick={() => this.setTaskStatus(record, "pause")}>暂停</Button>}
                        {record.status === "processing" && record.status !== "close" &&
                        <Button type="primary" size="small" style={{marginLeft: 4}}
                                onClick={() => this.setTaskStatus(record, "close")}>关闭</Button>}
                        {record.status === "processing" && record.status !== "end" &&
                        <Button type="primary" size="small" style={{marginLeft: 4}}
                                onClick={() => this.setTaskStatus(record, "end")}>完成</Button>}
                        <Button type="primary" size="small" style={{marginLeft: 4}}
                                onClick={() => this.getUpdateHistory(record)}>历史更新记录</Button>
                </span>
                )
            }
        }
    ];

    render() {
        const {event} = this.props;
        let list = [];
        let programTask = event.programTask;
        list.push({...programTask, programName: event.programName});
        const content = (
            <div>
                <Table
                    rowKey="id"
                    columns={this.columns}
                    dataSource={list}
                    pagination={false}
                />
            </div>
        );
        return <Popover {...this.props} content={content}>
        </Popover>;
    }
}
