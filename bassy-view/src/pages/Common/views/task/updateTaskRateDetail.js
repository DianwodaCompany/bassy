import React from 'react'
import moment from "moment";
import {
	Button,
	Card,
	Col,
	Form,
	Icon,
	Input,
	InputNumber,
	message,
	Popconfirm,
	Radio,
	Row,
	Select,
	Slider,
	Checkbox,
	Upload
} from "antd";
import {connect} from "react-redux";
import {
	getStaffList,
	getTaskHistory,
	getTestSuites,
	pushDocument,
	testCaseDo,
	updateTaskRate,
} from "../../../../apis/index";
import {
	getDictValue,
	getProgramName,
	isAutoTestTask,
	getabnormalReasonName
} from "../../../../utils/index";
import CSSModules from "react-css-modules";
import styles from "../../../App.less";
import FetchZtRequire from "../../../../components/FetchData/FetchZtRequire";
import {getTasks, getAbnormalLog} from "../../../../apis";

const FormItem = Form.Item;
const RadioGroup = Radio.Group;

const TextArea = Input.TextArea;
const Option = Select.Option;

const formLayout = {
	labelCol: {span: 4},
	wrapperCol: {span: 10}
};

let taskLog = {};

@Form.create()
@CSSModules(styles)
@connect(state => ({
	autoTest: state.autoTest,
	staffInfo: state.common.staffInfo,
	taskStatuss: state.common.taskStatus,
	abnormalReasonTeam: state.common.abnormalReasonTeam,
	abnormalReasonType: state.common.abnormalReasonType,
	abnormalReasonLevel: state.common.abnormalReasonLevel,
	allProjects: state.common.allProjects,
	allDict: state.allDict
}))
class UpdateTaskRateDetail extends React.Component {

	state = {
		fileList: [],
		needDetail: false,
		loading: false,
		programId: null,
		taskId: null,
		taskCode: "",
		taskName: "",
		requireId: null,
		startTm: "",
		endTm: "",
		taskStatus: "",
		expectHour: "",
		actualHour: "",
		todayHour: 0,
		percent: 0,
		isNormal: 1,
		isDumplicateAbnormal: false,
		requireWithTasks: [],
		taskWithLogs: [],
		reasonTeam: "",
		reasonType: "",
		reasonLevel: "",
		reasonDetail: "",
		hasAbnormalOwner: true,
		taskExplain: "",
		autoTestResult: "",
		levelDesc: "",
		staffList: [{code: '', name: ''}],
		testSuites: [{id: '', name: ''}],
		suiteId: null,
		type: null,
		receiveEmail: null,
		suiteName: "",
		autoTestId: null,
		autoTestTask: false
	};

	onChange = (e) => {
		console.log('radio checked', e.target.value);
		this.setState({
			isNormal: e.target.value,
			isDumplicateAbnormal: false,
		});
	};
	onDuplicateChange = () => {
		this.setState({
			isDumplicateAbnormal: !this.state.isDumplicateAbnormal,
		});
	};
	//选中重复异常的关联需求后，获取需求关联的任务
	onFetchRequireChange = async (value) => {
		const {allDict} = this.props;
		this.props.form.setFieldsValue({"requires": value});
		let requireId = value.split(",")[0];
		let data = {pageNum: 1, pageSize: 100, searchKey: requireId};
		const res = await getTasks(data);
		if (res.data && res.data.list.length !== 0) {
			let data = res.data.list.map(d => (
				{
					text: `(${d.programName})[任务${d.id}] ${getDictValue(allDict, "TEST_TASK", d.taskCode) || getDictValue(allDict, "INNER_PROJECT_TYPE", d.taskCode)} (${d.testerName == null ? "无负责人" : d.testerName})`,
					value: `${d.id}`,
				}));
			this.setState({
				requireWithTasks: data,
			});
		} else {
			message.error("查询出错!");
		}
	};

	//选择重复异常关联的任务后，获取任务的所有异常
	onSelectTask = async (value) => {
		const {abnormalReasonType} = this.props;
		let taskId = value.split(",")[0];
		const res = await getAbnormalLog(taskId);
		if (res.data) {
			let data = res.data.map(d => (
				{
					text: `[${getabnormalReasonName(abnormalReasonType, d.reasonType).reasonTeamName}-${getabnormalReasonName(abnormalReasonType, d.reasonType).reasonTypeName}-${d.reasonLevel}] ${d.reasonDetail} (责任人:  ${d.abnormalOwner == null ? "无" : d.abnormalOwner} )`,
					value: `${d.id},${d.reasonTeam},${d.reasonType},${d.reasonLevel},${d.reasonDetail},${d.abnormalOwner},${d.abnormalDepart}`,
				}));
			this.setState({
				taskWithLogs: data,
			});
			this.props.form.setFieldsValue({"abnormal": {key: data[0].value}});
		} else {
			message.error("查询出错!");
		}
	};

	selectReasonTeam = (team) => {
		this.setState({
			reasonTeam: team,
			reasonType: ""
		});
		console.info(this.state)
	};
	//P3级别非必填
	selectReasonLevel = (level) => {
		if (level === "P3") {
			this.setState({
				levelDesc: "能自行消化,未造成项目延期",
				needDetail: false
			});
		} else if (level === "P2") {
			this.setState({
				levelDesc: "项目延期1-2个工作日",
				needDetail: true
			});
		} else {
			this.setState({
				levelDesc: "项目延期2个工作日以上",
				needDetail: true
			});
		}
	};
	handleStatusChange = (value) => {
		if (value === 'end')
			this.setState({
				percent: 100,
			});
		else if (value === 'pause' || value === 'close')
			this.setState({isNormal: 0});
		else
			this.setState({
				percent: taskLog.percent,
			});
	};
	//内部项目的文档没有需求，传-1
	handleUploadFile = async () => {
		const {fileList, programId, requireId, taskId} = this.state;
		const formData = new FormData();
		fileList.forEach((file) => {
			formData.append('files', file);
		});
		formData.append('programId', programId);
		formData.append('requireId', requireId === null ? -1 : requireId);
		formData.append('taskId', taskId);
		formData.append('creator', this.props.staffInfo.code);
		return await pushDocument(formData);
	};

	viewTask = () => {
		if (this.props.location.state.callSource === "myTask") {
			this.props.history.push({
				pathname: '/myTask'
			})
		} else {
			this.props.history.push({
				pathname: '/task'
			})
		}

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

	submit = e => {
		e.preventDefault();
		this.props.form.validateFieldsAndScroll(async (err, values) => {
			if (!err) {
				this.setState({loading: true});
				const {fileList, taskId, programId, requireId, hasAbnormalOwner, isDumplicateAbnormal} = this.state;
				let obj = Object.assign({}, this.state, values);
				if (obj.taskStatus === "end" && obj.percent !== 100) {
					message.error("任务完成时，进度必须为100%");
					this.setState({loading: false});
					return;
				}
				//有附件时上传附件
				if (fileList.length > 0) {
					obj.document = this.getAllDocumentName(taskId, programId, requireId, fileList);
					const res = this.handleUploadFile();
					if (res.errCode === 0) {
						message.error("上传文档失败！");
						return;
					}
				}
				delete obj.fileList;
				obj.modifier = this.props.staffInfo.code;
				if (this.state.isNormal === 0) {
					obj.abnormalType = 1;
					if (programId !== 0 && hasAbnormalOwner && !isDumplicateAbnormal) {
						obj.abnormalOwner = /\((\d+)\)/.exec(values.abnormalOwner)[1];
					}
					if (isDumplicateAbnormal) {
						obj.duplicateAbnormal = values.abnormal.key.split(",")[0];
						obj.reasonTeam = values.abnormal.key.split(",")[1];
						obj.reasonType = values.abnormal.key.split(",")[2];
						obj.reasonLevel = values.abnormal.key.split(",")[3];
						obj.reasonDetail = values.abnormal.key.split(",")[4];
						obj.abnormalOwner = values.abnormal.key.split(",")[5];
						obj.abnormalDepart = values.abnormal.key.split(",")[6];
					}
				}
				let updateRateRes = await updateTaskRate(obj);
				if (updateRateRes.errCode === 1) {
					message.success("更新任务进度成功！");
				} else {
					message.error("更新任务进度失败！");
				}
				this.setState({loading: false});
				this.viewTask();
			}
		});
	};
	getAllDocumentName = (taskId, programId, requireId, fileList) => {
		const {taskCode} = this.state;
		if (requireId === null) {
			requireId = 0
		}
		let name = [];
		for (let i in fileList) {
			const fileInfo = {
				type: taskCode,
				name: this.state.fileList[i].name,
				path: "bassy/project-test/" + programId + "/" + requireId + "/" + taskId + "/" + this.state.fileList[i].name
			};
			name.push(fileInfo)
		}
		return JSON.stringify(name).toString();
	};
	formatter = (value) => {
		return `${value}%`;
	};
	todayHourFormatter = (value) => {
		return `${value}h`;
	};
	changeTodayHour = (value) => {
		this.setState({
			todayHour: value
		});
	};
	changePercent = (value) => {
		this.setState({
			percent: value
		});
		if (value === 100) {
			this.props.form.setFieldsValue({'taskStatus': 'end'});
		} else {
			this.props.form.setFieldsValue({'taskStatus': 'processing'});
		}
	};

	//选择测试项目
	selectAutoTestProject = async (type) => {
		const res = await getTestSuites({
			pageNum: 1,
			pageSize: 1000,
			type
		});
		if (res.data) {
			this.setState({type: type, testSuites: res.data.list, suiteName: ""});
		} else {
			message.error("获取测试套件失败！");
		}
	};

	//选择测试套件
	selectAutoTestSuite = (suiteId, option) => {
		this.setState({suiteId, suiteName: option.props.children});
	};

	//接收报告邮箱修改
	receiveEmailChange = (event) => {
		this.setState({receiveEmail: event.target.value + "@dianwoda.com"});
	};
	//执行测试用例
	execute = async () => {
		const {suiteId, type, receiveEmail} = this.state;
		if (type === null) {
			message.warn("请先选择测试项目套件！");
			return;
		}
		if (suiteId === null) {
			message.warn("请先选择测试套件！");
			return;
		}
		if (receiveEmail === null) {
			message.warn("请先选择报告接受邮箱！");
			return;
		}
		const creator = this.props.staffInfo.code;
		const testCaseName = type + "自动化测试回归";
		const params = {
			testCaseName,
			type,
			executeType: "IMMEDIATE",
			suiteId,
			creator,
			receiveEmail,
			ccEmail: receiveEmail
		};
		const res = await testCaseDo(params);
		if (res.errCode === 1) {
			this.setState({autoTestId: res.data.autoTestId});
			message.success("开始执行测试用例！");
		} else {
			message.error("执行测试用例失败！");
		}
	};

	async componentWillMount() {
		const {taskId, taskStatus, isNormal, taskCode, taskName} = this.props.location.state;
		const autoTestTask = isAutoTestTask(taskCode);
		const param = {
			pageSize: 1,
			pageNum: 1,
			taskId: taskId
		};
		const res = await getTaskHistory(param);
		taskLog = res.data.list[0];

		let statusChange = {
			taskStatus: taskLog.taskStatus,
			isNormal: 1
		};
		if (taskStatus !== undefined && isNormal !== undefined) {
			statusChange.taskStatus = taskStatus;
			statusChange.isNormal = isNormal;
		}
		if (taskStatus === 'end')
			this.setState({
				percent: 100,
			});
		else
			this.setState({
				percent: taskLog.percent,
			});
		this.setState({
			programId: taskLog.programId,
			taskId: taskLog.taskId,
			requireId: taskLog.requireId,
			startTm: taskLog.startTm,
			endTm: taskLog.endTm,
			taskStatus: statusChange.taskStatus,
			expectHour: taskLog.expectHour,
			actualHour: taskLog.actualHour,
			isNormal: statusChange.isNormal,
			taskCode: taskCode,
			taskName: taskName,
			autoTestId: taskLog.autoTestId,
			autoTestTask
		});

	}

	render() {
		const {allProjectType} = this.props.autoTest;
		const {getFieldDecorator} = this.props.form;
		const {taskStatuss, abnormalReasonTeam, abnormalReasonType, abnormalReasonLevel, allProjects, allDict} = this.props;
		const {
			programId, taskCode, taskName, requireId,
			startTm, endTm, expectHour,
			actualHour, taskStatus, percent,
			isNormal, staffList, needDetail,
			levelDesc, hasAbnormalOwner, testSuites,
			suiteName, autoTestTask, autoTestId,
			isDumplicateAbnormal, requireWithTasks, taskWithLogs
		} = this.state;
		const props = {
			onRemove: (file) => {
				this.setState(({fileList}) => {
					const index = fileList.indexOf(file);
					const newFileList = fileList.slice();
					newFileList.splice(index, 1);
					return {
						fileList: newFileList,
					};
				});
			},
			beforeUpload: (file) => {
				this.setState(({fileList}) => ({
					fileList: [...fileList, file],
				}));
				return false;
			},
			fileList: this.state.fileList,
		};
		return (
			<Card title={taskStatus === "processing" ? "更新任务进度" : "变更任务状态"}>
				<Form onSubmit={this.submit}>
					<FormItem {...formLayout} label="任务名称">
						<Input disabled
							   value={taskName || getDictValue(allDict, "TEST_TASK", taskCode) || getDictValue(allDict, "INNER_PROJECT_TYPE", taskCode)}/>
					</FormItem>
					<FormItem {...formLayout} label="所属项目">
						<Input disabled value={getProgramName(allProjects, programId)}/>
					</FormItem>
					<FormItem {...formLayout} label="所属需求">
						<Input disabled value={requireId}/>
					</FormItem>
					<FormItem {...formLayout} label="开始时间">
						<Input disabled value={moment(startTm).format('YYYY-MM-DD HH:mm:ss')}/>
					</FormItem>
					<FormItem {...formLayout} label="结束时间">
						<Input disabled value={moment(endTm).format('YYYY-MM-DD HH:mm:ss')}/>
					</FormItem>
					<FormItem {...formLayout} label="任务状态">
						{getFieldDecorator('taskStatus', {
							rules: [{message: '该字段不能为空!'}],
							initialValue: taskStatus
						})(
							<Select onChange={this.handleStatusChange}>
								{taskStatuss.map((s) => (
									<Option key={s.code}>{s.name}</Option>)
								)}
							</Select>
						)}
					</FormItem>
					<FormItem {...formLayout} label="预计工时（小时）">
						<Input disabled value={expectHour}/>
					</FormItem>
					<FormItem {...formLayout} label="当前累计工时">
						<Input disabled value={actualHour}/>
					</FormItem>
					<FormItem {...formLayout} label="今日耗时（小时）">
						<Slider tipFormatter={this.todayHourFormatter} onChange={this.changeTodayHour} max={12}
								step={0.5}/>
					</FormItem>
					<FormItem {...formLayout} label="进度（%）">
						<Slider tipFormatter={this.formatter} onChange={this.changePercent} value={percent}/>
					</FormItem>
					<FormItem {...formLayout} label="进度是否异常">
						<RadioGroup onChange={this.onChange} value={isNormal}>
							<Radio value={1}>正常</Radio>
							<Radio value={0}>异常</Radio>
						</RadioGroup>
						{isNormal === 0 && programId !== 0 &&
						<Checkbox onChange={this.onDuplicateChange} checked={isDumplicateAbnormal}>与前置异常相同</Checkbox>}
					</FormItem>
					{isDumplicateAbnormal && <div>
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
					</div>}
					{!isDumplicateAbnormal && isNormal === 0 && programId !== 0 && <div>
						<FormItem {...formLayout} label="异常原因">
							{getFieldDecorator('reasonTeam', {
								rules: [{required: true, message: '异常类型不能为空!'}],
								initialValue: ""
							})(
								<Select
									onChange={this.selectReasonTeam}
								>
									{abnormalReasonTeam.map((t) => (
										<Option value={t.reasonTeamCode} key={t.reasonTeamCode}>
											{t.reasonTeamName}
										</Option>
									))}
								</Select>
							)}
						</FormItem>
						<FormItem wrapperCol={{span: 7, offset: 4}}>
							{getFieldDecorator('reasonType', {
								rules: [{required: true, message: '二级原因不能为空!'}]
							})(
								<Select>
									{abnormalReasonType.map((s) => (
										s.reasonTeamCode === this.state.reasonTeam &&
										<Option value={s.reasonTypeCode} key={s.reasonTypeCode}>
											{s.reasonTypeName}
										</Option>
									))}
								</Select>
							)}
						</FormItem>
						<FormItem {...formLayout} label="责任人">
							{getFieldDecorator('hasAbnormalOwner', {
								rules: [{required: hasAbnormalOwner, message: '责任人不能为空!'}],
								initialValue: 1
							})(
								<RadioGroup onChange={() => {
									this.setState({hasAbnormalOwner: !hasAbnormalOwner})
								}}>
									<Radio value={1}>关联责任人</Radio>
									<Radio value={0}>无责任人</Radio>
								</RadioGroup>
							)}
						</FormItem>
						{hasAbnormalOwner &&
						<FormItem wrapperCol={{span: 7, offset: 4}}>
							{getFieldDecorator('abnormalOwner', {
								rules: [{required: hasAbnormalOwner, message: '责任人不能为空!'}],
							})(
								<Select
									showSearch
									placeholder="工号或姓名"
									onSearch={this.changPersonKeyWord}
								>
									{staffList.map((s) => (
										<Option value={`(${s.code})${s.name}`} key={s.code}>
											{"(" + s.code + ")" + s.name}
										</Option>
									))}
								</Select>
							)}
						</FormItem>
						}
						<FormItem {...formLayout} label="影响级别">
							{getFieldDecorator('reasonLevel', {
								rules: [{required: true, message: '影响级别不能为空!'}]
							})(
								<Select
									onChange={this.selectReasonLevel}>
									{abnormalReasonLevel.map((s) => (
										<Option value={s} key={s}>
											{s}
										</Option>
									))}
								</Select>
							)}
							<span>{levelDesc}</span>
						</FormItem>
						<FormItem {...formLayout} label="异常说明">
							{getFieldDecorator('reasonDetail', {
								rules: [{required: needDetail, message: '异常说明不能为空!'}]
							})(
								<TextArea rows={4}/>
							)}
						</FormItem>
					</div>
					}
					<FormItem {...formLayout} label="完成情况说明">
						{getFieldDecorator('taskExplain', {
							rules: [{required: false, message: '该字段不能为空!'}]
						})(
							<TextArea rows={4}/>
						)}
					</FormItem>
					{autoTestTask && <div>
						<FormItem {...formLayout} label="自动化项目">
							<Row gutter={10}>
								<Col span={8}>
									<Select
										onChange={value => this.selectAutoTestProject(value)}
									>
										{allProjectType.map((tt) => (
												<Option key={tt}>{tt}</Option>
											)
										)}
									</Select>
								</Col>
								<Col span={16}>
									<Select
										onChange={(value, option) => this.selectAutoTestSuite(value, option)}
										value={suiteName}
									>
										{testSuites.map((t) => (
											<Option value={t.id} key={t.id}>
												{t.name}
											</Option>
										))}
									</Select>
								</Col>
							</Row>
						</FormItem>
						<FormItem {...formLayout} label="接收报告邮箱">
							<Row gutter={10}>
								<Col span={19}>
									<Input onChange={this.receiveEmailChange}
										   addonAfter="@dianwoda.com"/>
								</Col>
								<Col span={5}>
									<Button type="primary" block
											onClick={this.execute}>{this.state.autoTestId ? "重新执行" : "确定执行"}</Button>
								</Col>
							</Row>
						</FormItem>
						{autoTestId !== null &&
						<FormItem {...formLayout} label="测试用例通过率">
							{getFieldDecorator('autoTestResult', {})(
								<InputNumber
									min={0}
									max={100}
									formatter={value => `${value}%`}
									parser={value => value.replace('%', '')}
								/>
							)}
						</FormItem>
						}
					</div>
					}
					<FormItem {...formLayout} label="选择上传文件">
						<Upload  {...props}>
							<Button>
								<Icon type="upload"/>选择需要上传文件
							</Button>
						</Upload>
					</FormItem>
					<Row>
						<Col span={7}/>
						<Col span={17}>
							<FormItem>
								{this.state.todayHour === 0 ?
									<Popconfirm title="今日耗时为0，是否确认更新？" onConfirm={this.submit} okText="Yes"
												cancelText="No">
										<Button type="primary" loading={this.state.loading}>更新</Button>
									</Popconfirm>
									: <Button type="primary" htmlType="submit">更新</Button>
								}
							</FormItem>
						</Col>
					</Row>
				</Form>
			</Card>
		)

	}
}

export default Form.create()(UpdateTaskRateDetail)