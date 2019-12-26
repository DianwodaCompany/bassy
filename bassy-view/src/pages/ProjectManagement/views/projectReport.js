import React from "react";
import {Button, Card, Col, Dropdown, Form, Icon, Input, Menu, message, Popconfirm, Row, Select, Table} from "antd";
import {connect} from "react-redux";
import {addProgramReport, getProgramReportCount, getProgramRequire} from "../../../apis/index"
import {getProgramName} from "../../../utils/index";

import style from "./index.less";
import CSSModules from "react-css-modules";

const TextArea = Input.TextArea;
const EditableContext = React.createContext();
const FormItem = Form.Item;

const EditableRow = ({form, index, ...props}) => (
	<EditableContext.Provider value={form}>
		<tr {...props} />
	</EditableContext.Provider>
);

const EditableFormRow = Form.create()(EditableRow);

class EditableCell extends React.Component {
	getInput = () => {
		if (this.props.inputType === 'select') {
			return (
				<Select style={{width: 150}}>
				</Select>
			);
		}
		return <Input/>;
	};

	render() {
		const {
			editing,
			dataIndex,
			title,
			record,
			...restProps
		} = this.props;
		return (
			<EditableContext.Consumer>
				{(form) => {
					const {getFieldDecorator} = form;
					return (
						<td {...restProps}>
							{editing ? (
								<FormItem style={{margin: 0}}>
									{getFieldDecorator(dataIndex, {
										rules: [{
											required: true,
											message: "Please Input ${title}!",
										}],
										initialValue: record[dataIndex]
									})(this.getInput())}
								</FormItem>
							) : restProps.children}
						</td>
					);
				}}
			</EditableContext.Consumer>
		);
	}
}

@CSSModules(style)
@connect(state => ({
	staffInfo: state.common.staffInfo,
	allDict: state.allDict,
	projectStatus: state.common.projectStatus,
	taskStatus: state.common.taskStatus,
	allProjects: state.common.allProjects,
	projectNode: state.project.projectNode
}))
@Form.create()
export default class ProjectReport extends React.Component {

	state = {
		editMode: true,
		programStage: {
			stageValue: ""
		},
		projectRisk: null,
		requireTaskProcess: [],
		programBugInfo: [],
		requires: [],
		editingId: null
	};

	componentWillMount() {
		// 查看日报，采用预览模式
		const {view, add, reportInfo, programId} = this.props.location.state;
		if (view) {
			this.viewInitState(reportInfo);
			this.setState({editMode: false})
		}
		if (add) {
			this.addInitState(programId);
		}
	}

	viewInitState = (reportInfo) => {
		this.setState({
			editMode: this.state.editMode,
			projectRisk: reportInfo.projectRisk,
			requireTaskProcess: JSON.parse(reportInfo.taskProgress),
			programBugInfo: JSON.parse(reportInfo.bugSummary),
			programStage: {stageCode: reportInfo.projectStage}
		});
	};

	addInitState = async (programId) => {
		let requires = await getProgramRequire(programId);
		this.state.requires = requires.data;
		this.getReportInfo(programId);
	};

	getReportInfo = async (programId) => {
		let reportRes = await getProgramReportCount({programId, requires: this.state.requires});
		const {requireTaskProcess, programBugInfo, programStage} = reportRes.data;
		this.setState(
			{
				requireTaskProcess,
				programBugInfo,
				programStage
			});
	};

	taskColumns = [
		{
			title: "需求",
			key: "demand",
			render: item => {
				if (item.requireId !== null && item.requireRelate !== null) {
					return item.requireId + " " + item.requireRelate;
				}
				return "无";
			},
		},
		{
			title: "任务名称",
			key: "taskName",
			dataIndex: "taskName",
			align: 'center',
		},
		{
			title: "任务状态",
			dataIndex: "taskStatus",
			align: 'center',
			render: record => {
				return this.props.projectStatus.map((s) => {
					if (s.code === record) {
						return s.name
					}
				})
			}
		},
		{
			title: "进度",
			key: "taskPercent",
			dataIndex: "taskPercent",
			align: 'center',
			render: record => {
				return record + "%"
			}
		},
		{
			title: "完成情况说明",
			key: "taskExplain",
			dataIndex: "taskExplain",
			align: 'center',
		},
		{
			title: "测试人员",
			key: "testerName",
			dataIndex: "testerName",
			align: 'center',

		}, {
			title: '操作',
			key: "requireId",
			dataIndex: 'requireId',
			render: requireId => (
				<Popconfirm title="Sure to delete?" onConfirm={() => this.handleTaskDelete(requireId)}>
					<a href="javascript:;">Delete</a>
				</Popconfirm>
			),
		}
	];
	//根据编辑预览模式获取不同的表格列
	getTaskColumns = () => {
		if (this.state.editMode) {
			return this.taskColumns
		}
		//预览模式去除操作列
		const columns = Object.assign([], [], this.taskColumns);
		columns.pop();
		return columns
	};
	//删除项目任务
	handleTaskDelete = (requireId) => {
		this.state.requires = this.state.requires.filter(require => require !== requireId);
		this.getReportInfo(this.props.location.state.programId);
	};

	bugColumns = [
		{
			title: "总bug数",
			dataIndex: "todayTotal",
			align: 'center',
			editable: true
		},
		{
			title: "未修改",
			dataIndex: "notResolvedTotalBug",
			align: 'center',
			editable: true
		},
		{
			title: "今日新增",
			dataIndex: "todayNewTotalBug",
			align: 'center',
			editable: true
		},
		{
			title: "今日关闭",
			dataIndex: "todayCloseTotalBug",
			align: 'center',
			editable: true
		},
		{
			title: "≥24小时未解决",
			dataIndex: "notResolvedTotalBugMoreThanOneDay",
			align: 'center',
			editable: true
		},
		{
			title: "≥24小时未回归",
			dataIndex: "resolvedTotalBugMoreThanOneDay",
			align: 'center',
			editable: true
		},
		{
			title: '操作',
			dataIndex: 'operation',
			render: (text, record) => {
				const editable = this.isBugEditing(record);
				return (
					<div>
						{editable ? (
							<span>
                                <EditableContext.Consumer>
                                    {form => (
										<a
											href="javascript:;"
											onClick={() => this.bugSave(form, record.id)}
											style={{marginRight: 8}}
										>
											保存
										</a>
									)}
                                </EditableContext.Consumer>
                                <Popconfirm
									title="Sure to cancel?"
									onConfirm={() => this.bugCancel(record.id)}
								>
                                    <a>取消</a>
                                </Popconfirm>
                             </span>) : (<a onClick={() => this.bugEdit(record.id)}>修改</a>)
						}
					</div>
				);
			}
		}
	];

	//编辑bug信息
	bugEdit = (id) => {
		this.setState({editingId: id});
	};
	//确定bug编辑行
	isBugEditing = record => record.id === this.state.editingId;
	//取消bug信息编辑
	bugCancel = () => {
		this.setState({editingId: null});
	};
	//保存bug信息
	bugSave = (form, id) => {
		form.validateFields(async (error, row) => {
			if (error) {
				return;
			}
			const programBugInfo = [...this.state.programBugInfo];
			const index = programBugInfo.findIndex(item => id === item.id);
			if (index > -1) {
				const item = programBugInfo[index];
				programBugInfo.splice(index, 1, {
					...item,
					...row,
				});
				this.setState({programBugInfo, editingId: null});
			}
		});
	};
	//bug信息根据编辑预览模式返回不同的列表头
	getBugColumns = () => {
		const bugColumns = this.bugColumns.map((col) => {
			if (!col.editable) {
				return col;
			}
			return {
				...col,
				onCell: record => ({
					record,
					dataIndex: col.dataIndex,
					title: col.title,
					editing: this.isBugEditing(record),
				}),
			};
		});
		if (this.state.editMode) {
			return bugColumns
		}
		//非编辑模式去除操作列
		const columns = Object.assign([], [], bugColumns);
		columns.pop();
		return columns;
	};


	//切换编辑预览模式
	onModeChange = () => {
		this.setState({editMode: !this.state.editMode})
	};
	//生成项目阶段列
	getStageColumn = () => {
		const {projectNode} = this.props;
		let sc = [{title: "项目", dataIndex: "projectName", align: 'center'}];
		projectNode.map((p, i) => {
			this.props.allDict.find((d) => {
				if (d.dictGroup === "PROJECT_NODE" && p === d.dictCode)
					sc.push({title: d.dictValue, dataIndex: "stage" + i, align: 'center'})
			})
		});
		return sc;
	};
	//获取项目阶段数据
	getStageData = () => {
		const {programId} = this.props.location.state;
		const {projectNode} = this.props;
		let projectName = getProgramName(this.props.allProjects, programId);
		let b = {projectName};
		projectNode.map((s, i) => {
			if (this.state.programStage.stageCode === s) {
				Object.assign(b, {["stage" + i]: "☆"});
			} else {
				Object.assign(b, {["stage" + i]: ""});
			}
		});
		return [b];
	};
	//修改项目当前阶段
	onStageChange = e => {
		this.setState({programStage: {stageCode: e.key}});
		this.getStageData();
	};
	//返回项目文档页面
	back = () => {
		this.props.history.goBack();
	};

	//生成日报
	updateReport = async () => {
		this.props.form.validateFieldsAndScroll(async (err, values) => {

			if (!err) {
				console.log("Received values of form: ", values);
				const {programStage, requireTaskProcess, programBugInfo, editingId, projectRisk} = this.state;
				if (editingId !== null) {
					message.warn("请先保存bug编辑信息！");
					return;
				}
				const {programId} = this.props.location.state;
				const params = {
					projectId: programId,
					projectStage: programStage.stageCode,
					taskPercent: JSON.stringify(requireTaskProcess),
					bugSummary: JSON.stringify(programBugInfo),
					creator: this.props.staffInfo.code,
					projectRisk
				};
				await addProgramReport(params);
				message.success("生成项目日报成功！");
				this.props.history.push({
					pathname: "/project/document",
				});
			}
		})
	};

	changeProjectRisk = (value) => {
		this.setState({projectRisk: value.target.value})
	};

	render() {

		const {view, add} = this.props.location.state;
		const {projectNode} = this.props;
		const {editMode, requireTaskProcess, programBugInfo, projectRisk} = this.state;
		const {getFieldDecorator} = this.props.form;
		const {allDict} = this.props;

		const components = {
			body: {
				row: EditableFormRow,
				cell: EditableCell,
			},
		};

		const menu = (
			<Menu onClick={this.onStageChange}>
				{projectNode.map((s) => {
					return <Menu.Item key={s}>
						{allDict.map((d) => {
							if (d.dictGroup === "PROJECT_NODE" && s === d.dictCode) {
								return d.dictValue
							}
						})}
					</Menu.Item>
				})}
			</Menu>
		);

		return (
			<div>
				<Card title="生成日报"
					  bodyStyle={{display: "inline-block", textAlign: "center"}}
				>
					<Card title="项目阶段及风险"
						  style={{width: "1000px"}}
						  extra={
							  editMode && add &&
							  <Dropdown overlay={menu}>
								  <a className="ant-dropdown-link">
									  切换阶段 <Icon type="down"/>
								  </a>
							  </Dropdown>
						  }>
						<Table
							key="123"
							columns={this.getStageColumn()}
							dataSource={this.getStageData()}
							pagination={false}
						/>
						{
							editMode && add ?
								<TextArea
									placeholder={"在此处输入风险说明"}
									autosize={{minRows: 2}}
									disabled={false}
									onChange={this.changeProjectRisk}
									style={{color: '#ff0005'}}
									className="ant-input"
								/> :<div style={{textAlign:"left",color:"red"}}>
									<pre>{projectRisk}</pre>
								</div>
						}
					</Card>
					<h1/>
					<Card title="今日进度"
						  style={{width: "1000px"}}
					>
						<Table
							key="taskId"
							columns={this.getTaskColumns()}
							dataSource={requireTaskProcess}
							pagination={false}
						/>
					</Card>
					<h1/>
					<Card title="BUG统计"
						  style={{width: "1000px"}}
					>
						<FormItem>
							{getFieldDecorator('bugs', {})(
								<Table
									key="todayTotal"
									components={components}
									rowClassName="editable-row"
									columns={this.getBugColumns()}
									dataSource={programBugInfo}
									pagination={false}
								/>
							)}
						</FormItem>
					</Card>
					<h1/>
					<Row>
						<Col span={9}/>
						<Col span={3}>
							{add && <Button onClick={this.onModeChange}
											type="primary">{Boolean(editMode) ? "预览" : "编辑"}</Button>}
						</Col>
						<Col span={3}>
							{add &&
							<Button onClick={() => this.updateReport()} type="primary"
									htmlType="submit">生成日报</Button>}
						</Col>
						<Col span={3}>
							<Button onClick={() => this.back()}>返回</Button>
						</Col>
						<Col span={9}/>
					</Row>
				</Card>
			</div>
		);
	}
}


