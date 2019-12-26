import React from 'react'
import {connect} from "react-redux";
import moment from "moment";
import {Badge, Card, Col, Form, message, Row, Table} from "antd";
import {getAutoTestReport, getTaskHistory} from "../../../../apis/index";
import {getDictValue, getProgramName} from "../../../../utils/index"

// const bucketName = require('config').get('bucketName')


@Form.create()
@connect(state => ({
    allDict: state.allDict,
    abnormalReasonType: state.common.abnormalReasonType,
    projectStatus: state.common.projectStatus,
    taskStatus: state.common.taskStatus,
    allProjects: state.common.allProjects
}))
export default class TaskUpdateHistory extends React.Component {
    state = {
        loading: false,
        taskHistoryList: [{}],
        pageNum: 1,
        pageSize: 10,
        total: 0,
        taskCode: this.props.location.state.taskCode,
        taskName: this.props.location.state.taskName,
        taskId: this.props.location.state.taskId
    };
    //下载任务附件
    downloadTaskDocument = async (path) => {
        window.location = "http://" + window.__data.bucketName + ".oss-cn-hangzhou.aliyuncs.com/" + path;
    }
    columns = [
        {
            title: "更新时间",
            width: 150,
            dataIndex: "modifyTm",
            fixed: 'left',
            render: record => moment(record).format("YYYY-MM-DD HH:mm:ss")
        },
        {
            title: "状态",
            width: 100,
            // dataIndex: "taskStatus",
            fixed: 'left',
            render: item => (
                <span>
        {item.isNormal == 0 && <Badge status="error"></Badge>}
                    {item.isNormal == 1 && <Badge status="success"></Badge>}
                    {this.props.taskStatus.map((s) => {
                        if (s.code === item.taskStatus) {
                            return s.name
                        }
                    })}
      </span>
            )
        },
        {
            title: "进度",
            width: 80,
            key: "percent",
            dataIndex: "percent",
            fixed: 'left',
            render: record => {
                return record + "%"
            }
        },
        {
            title: "今日耗时（h）",
            key: "todayHour",
            dataIndex: "todayHour",
        },
        {
            title: "完成情况说明",
            key: "taskExplain",
            dataIndex: "taskExplain",
        },
        {
            title: "当前累计工时（h）",
            key: "actualHour",
            dataIndex: "actualHour",

        },
        {
            title: "是否正常",
            key: "isNormal",
            dataIndex: "isNormal",
            render: record => {
                if (record === 1) {
                    return "是"
                } else if (record === 0) {
                    return "否"
                } else {
                    return ""
                }
            }
        },
        {
            title: "异常原因",
            key: "reasonType",
            dataIndex: "reasonType",
            render: record => {
                return this.props.abnormalReasonType.map((s) => {
                        if (s.reasonTypeCode == record)
                            return s.reasonTypeName
                    }
                )
            }
        },
        {
            title: "异常说明",
            key: "reasonDetail",
            dataIndex: "reasonDetail",
        },
        {
            title: "开始时间",
            dataIndex: "startTm",
            render: record => moment(record).format("YYYY-MM-DD HH:mm:ss")
        },
        {
            title: "结束时间",
            dataIndex: "endTm",
            render: record => moment(record).format("YYYY-MM-DD HH:mm:ss")
        },
        {
            title: "预计工时",
            key: "expectHour",
            dataIndex: "expectHour",
        },
        {
            title: "自动化测试",
            key: "autoTestResult",
            render: item => {
                if (item.autoTestId !== null && item.autoTestResult !== null) {
                    return <a onClick={() => this.viewAutoTestReport(item.autoTestId)}> {item.autoTestResult + "%"}</a>
                }
            }
        },
        {
            title: "任务文档",
            key: "document",
            render: item => {
                if (item.document != null) {
                    const doc = JSON.parse(item.document);
                    return doc.map((d) => {
                            return <div><a onClick={() => this.downloadTaskDocument(d.path)}>{d.name}</a><br/></div>
                        }
                    )
                } else {
                    return <div/>
                }

            }
        }
    ];
    //查看自动化测试报告
    viewAutoTestReport = async (autoTestId) => {
        const res = await getAutoTestReport(autoTestId);
        if (res.data) {
            this.props.history.push({
                pathname: '/toReport',
                state: {
                    body: res.data
                }
            })
        } else {
            message.error("下载报告失败!");
        }
    };
    //获取更新信息列表
    getTaskUpdateHistory = async (pageNum = 1) => {
        const params = {
            pageNum,
            pageSize: 10,
            taskId: this.props.location.state.taskId
        }
        const res = await getTaskHistory(params)
        if (res.errCode !== 1) {
            message.error("查询任务更新历史失败！")
            return
        }
        const {currentPage, pageSize, list, totalCount} = res.data;
        this.setState({
            taskHistoryList: list,
            pageSize,
            total: totalCount,
            pageNum: currentPage,
        })
    }

    changePage = pageNum => {
        this.getTaskUpdateHistory(pageNum)
    }

    componentWillMount() {
        this.getTaskUpdateHistory();
    }

    render() {
        const {
            taskHistoryList,
            pageNum,
            taskCode,
            taskName,
            pageSize,
            total
        } = this.state;
        const {allProjects, allDict} = this.props;
        let taskName1 = taskName || getDictValue(allDict, "TEST_TASK", taskCode) || getDictValue(this.props.allDict, "INNER_PROJECT_TYPE", taskCode);
        let {programId} = this.props.location.state
        let programName = getProgramName(allProjects, programId)

        return (
            <div>
                <Card title={"任务信息：" + this.state.taskId + "-" + taskName1}>
                    <Row>
                        <Col span={6}>所属项目：{programName == null ? "无所属项目" : programName}</Col>
                        <Col
                            span={6}>所属需求：{taskHistoryList.length !== 0 ? taskHistoryList[taskHistoryList.length - 1].requireId : ""}</Col>
                    </Row>
                    <Row>
                        <Col span={6}>其他信息：暂无</Col>
                    </Row>
                </Card>
                <Card>
                    <Table
                        rowKey="id"
                        columns={this.columns}
                        dataSource={taskHistoryList}
                        scroll={{x: 2000}}
                        pagination={{
                            current: pageNum,
                            total,
                            pageSize,
                            onChange: this.changePage
                        }}
                    />
                </Card>
            </div>
        )
    }

}