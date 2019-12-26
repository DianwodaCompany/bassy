import React from 'react'
import {Button, Card, Col, DatePicker, Form, Input, message, Row, Table, Select} from "antd";
import {connect} from "react-redux";
import {getTasks} from "../../apis/index";
import {getStaffList, startTask} from "../../apis";
import CustomTask from "../Common/views/task/customTask";
import moment from "moment";


const FormItem = Form.Item;
const Option = Select.Option;

const {RangePicker} = DatePicker;


@Form.create()
@connect(state => ({
	staffInfo: state.common.staffInfo,
	taskStatus: state.common.taskStatus,
	allDict: state.allDict
}))
export default class TaskManagement extends React.Component {

	state = {
		loading: false,
		list: [],
		pageNum: 1,
		pageSize: 10,
		total: 0,
		addVisible: false,
		operateMode: '',
		startEndTm: [moment().startOf('month'), moment().endOf('month')],
		customTaskInfo: {
			taskName: "",
			expectHour: 1,
		},
		staffList: [{code: '', name: ''}],
	};
	getTaskList = async (pageNum = 1, searchKey, status, tester, process, startTm, endTm) => {
		const data = {pageNum, pageSize: 10, searchKey, startTm, endTm, status, tester, process};
		const res = await getTasks(data);
		if (res.data) {
			const {currentPage, pageSize, list, totalCount} = res.data;
			this.setState({
				list,
				pageSize,
				total: totalCount,
				pageNum: currentPage,
				loading: false,
			});
		} else {
			message.error("查询出错!");
		}
	};
	changePage = page => {
		const {searchKey, startEndTm, status, testerName, process} = this.props.form.getFieldsValue();
		let tester = null;
		if (testerName != null)
			tester = /\((\d+)\)/.exec(testerName)[1];
		if (startEndTm !== undefined) {
			this.getTaskList(page, searchKey, status, tester, process, startEndTm[0]._d, startEndTm[1]._d);
		} else {
			this.getTaskList(page, searchKey, status, tester, process);
		}
	};
	handleReset = () => {
		this.props.form.resetFields()
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

	handleSearch = e => {
		e.preventDefault();
		const {searchKey, status, testerName, startEndTm, process} = this.props.form.getFieldsValue();
		let tester = null;
		if (testerName != null)
			tester = /\((\d+)\)/.exec(testerName)[1];
		if (startEndTm !== undefined) {
			this.getTaskList(1, searchKey, status, tester, process, startEndTm[0]._d, startEndTm[1]._d);
		} else {
			this.getTaskList(1, searchKey, status, tester, process);
		}
	};
	viewProgramTaskDetail = (item) => {
		this.props.history.push({
			pathname: '/programTaskDetail',
			state: {
				id: item.programId,
				programName: item.programName
			}
		})
	};
	startTask = async (item, status) => {
		if (item.tester === "" || item.tester == null) {
			message.info('未分配负责人，无法开始任务哦~');
			return;
		}
		const params = {
			taskId: item.id,
			status,
			modifier: this.props.staffInfo.code
		};
		const res = await startTask(params);
		if (res.errCode === 1) {
			message.success("开始任务成功！");
			await this.changePage(this.state.pageNum);
		} else {
			message.error("开始任务失败！")
		}
	};
	editCustomTask = item => {
		this.setState({addVisible: true, operateMode: 'edit', customTaskInfo: item})
	};
	setTaskStatus = async (item, state) => {
		this.props.history.push({
			pathname: '/task/updateTask',
			state: {
				taskId: item.id,
				taskStatus: state,
				isNormal: state === 'end' ? 1 : 0,
				taskCode: item.taskCode,
				taskName: item.taskName,
			}
		})
	};
	getUpdateHistory = async (item) => {
		this.props.history.push({
			pathname: '/task/updateTaskHistory',
			state: {
				taskId: item.id,
				taskCode: item.taskCode,
				taskName: item.taskName,
				callSource: "task",
				programId: item.programId,
			}
		})
	};
	updateTaskRate = async (item) => {
		this.props.history.push({
			pathname: '/task/updateTask',
			state: {
				taskId: item.id,
				taskCode: item.taskCode,
				taskName: item.taskName,
				callSource: "task",
			}
		})
	};

	addCustomTask = () => {
		this.setState({addVisible: true});
	};

	cancelAdd = () => {
		this.props.form.resetFields();
		this.setState({addVisible: false});
	};

	columns = [
		{
			title: "所属项目",
			key: "programName",
			render: item => !item.programId ? "无关联项目" :
				<a onClick={() => this.viewProgramTaskDetail(item)}>({item.programId}){item.programName}</a>
		},
		{
			title: "所属阶段",
			key: "programProcess",
			render: item => {
				return this.props.allDict.map((s) => {
						if (s.dictGroup === 'PROJECT_NODE' && s.dictCode === item.programProcess) {
							return s.dictValue
						}
					}
				)
			}
		},
		{
			title: "关联需求",
			key: "requireId",
			dataIndex: "requireId",
			render: (text, record, index) => {
				if (record.requireId) {
					return "(" + record.requireId + ")" + record.requireRelate;
				} else {
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
			title: "测试负责人",
			key: "tester",
			dataIndex: "tester",
			render: (text, record, index) => {
				if (record.tester) {
					return "(" + record.tester + ")" + record.testerName;
				} else {
					return "";
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
			title: "资源分配",
			key: "time_count",
			render: (text, record, index) => {
				if (record.status === "end") {
					return <div>
						<div>实际开始:{moment(record.actualStartTm).format("YYYY-MM-DD HH:mm:ss")}</div>
						<div>实际结束:{moment(record.actualEndTm).format("YYYY-MM-DD HH:mm:ss")}</div>
						<div>实际用时:{record.actualHour}</div>
						{record.excludeDate && <div>排除节假日:{record.excludeDate}</div>}
						{record.includeDate && <div>包括特殊周末:{record.includeDate}</div>}
					</div>;
				} else {
					return <div>
						<div>计划开始:{moment(record.startTm).format("YYYY-MM-DD HH:mm:ss")}</div>
						<div>计划结束:{moment(record.endTm).format("YYYY-MM-DD HH:mm:ss")}</div>
						<div>计划用时:{record.expectHour}</div>
						{record.excludeDate && <div>排除节假日:{record.excludeDate}</div>}
						{record.includeDate && <div>包括特殊周末:{record.includeDate}</div>}
					</div>;
				}
			}
		},
		{
			title: "操作",
			key: 'action',
			render: item => {
				return (
					<span>
                        {item.status === "processing" &&
						<Button type="primary" size="small" style={{marginLeft: 4}}
								onClick={() => this.updateTaskRate(item)}>更新进度</Button>}
						{item.programId === 0 && item.status !== "close" && item.status !== "end" &&
						<Button type="primary" size="small" style={{marginLeft: 4}}
								onClick={() => this.editCustomTask(item)}>编辑</Button>}
						{item.status !== "processing" && item.status !== "close" && item.status !== "end" &&
						<Button type="primary" size="small" style={{marginLeft: 4}}
								onClick={() => this.startTask(item, "processing")}>开始</Button>}
						{item.status === "processing" && item.status !== "pause" &&
						<Button type="primary" size="small" style={{marginLeft: 4}}
								onClick={() => this.setTaskStatus(item, "pause")}>暂停</Button>}
						{item.status === "processing" && item.status !== "close" &&
						<Button type="primary" size="small" style={{marginLeft: 4}}
								onClick={() => this.setTaskStatus(item, "close")}>关闭</Button>}
						{item.status === "processing" && item.status !== "end" &&
						<Button type="primary" size="small" style={{marginLeft: 4}}
								onClick={() => this.setTaskStatus(item, "end")}>完成</Button>}
						<Button type="primary" size="small" style={{marginLeft: 4}}
								onClick={() => this.getUpdateHistory(item)}>历史更新记录</Button>
                </span>
				)
			}
		}
	];

	componentWillMount() {
		this.setState({loading: true});
		this.getTaskList(1, null, null, null, null, this.state.startEndTm[0]._d, this.state.startEndTm[1]._d);
	}

	render() {
		const {getFieldDecorator} = this.props.form;
		const {
			list,
			pageNum,
			pageSize,
			total,
			loading,
			addVisible,
			operateMode,
			customTaskInfo,
			staffList,
			startEndTm
		} = this.state;

		console.info("before render");
		console.info(list);
		const {taskStatus, allDict} = this.props;
		return (
			<Card bordered={false}>
				<Form
					layout="inline"
					onSubmit={this.handleSearch}
					style={{marginBottom: 12}}
				>
					<Row type="flex" justify="start" align="bottom">
						<Col span={22}>
							<FormItem>
								{getFieldDecorator("searchKey")(<Input placeholder="支持根据任务id/名,需求id/名,项目id/名搜索"
																	   style={{width: 320}}/>)}
							</FormItem>
							<FormItem label="项目阶段">
								{getFieldDecorator("process")(
									<Select
										style={{width: 160}}
									>
										{allDict.map(v => {
											if (v.dictGroup === 'PROJECT_NODE') {
												return <Option key={v.dictCode}>{v.dictValue}</Option>;
											}
										})}
									</Select>
								)}
							</FormItem>
							<FormItem label="任务状态">
								{getFieldDecorator("status")(
									<Select
										style={{width: 100}}
									>
										{taskStatus.map(v => <Option key={v.code}>{v.name}</Option>)}
									</Select>
								)}
							</FormItem>
							<FormItem label="测试负责人">
								{getFieldDecorator("testerName")(
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
							<FormItem label="时间区间">
								{getFieldDecorator("startEndTm", {
									rule: [
										{
											required: false
										}
									],
									initialValue: startEndTm
								})(<RangePicker
									style={{
										display: "inline-block",
										marginLeft: "5"
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
						</Col>
						<Col span={2}>
							<FormItem>
								<Button type={"primary"} icon={"plus"} style={{marginLeft: 6}}
										onClick={this.addCustomTask}>任务</Button>
							</FormItem>
						</Col>
					</Row>
				</Form>

				<Table
					id="taskManagementTable"
					loading={loading}
					rowKey="id"
					columns={this.columns}
					dataSource={list}
					pagination={{
						current: pageNum,
						total,
						pageSize,
						onChange: this.changePage
					}}
				/>
				<div>
					{
						addVisible &&
						<CustomTask
							operateMode={operateMode}
							customTaskInfo={customTaskInfo}
							toMe={false}
							callback={() => this.getTaskList()}
							onCancel={this.cancelAdd}
						/>
					}
				</div>
			</Card>
		)
	}
}
