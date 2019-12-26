import React from "react";
import {Button, Card, Col, Form, Icon, Input, message, Modal, Popconfirm, Row, Table, Upload} from "antd";
import CSSModules from "react-css-modules";
import {connect} from "react-redux";
import {deleteProgramDocument, getHistoryReports, getTaskDocumentList, pushDocument} from "../../../apis/index"
import {getProgramName} from "../../../utils/index"
import styles from "../../App.less";
import moment from "moment";

const FormItem = Form.Item;

const formLayout = {
	labelCol: {span: 6},
	wrapperCol: {span: 15}
};

@CSSModules(styles)
@connect(state => ({
	allStages: state.common.allStages,
	allProjects: state.common.allProjects,
	allDict: state.allDict,
	staffInfo: state.common.staffInfo,
	project: state.project
}))
@Form.create()
export default class ProjectDocument extends React.Component {

	state = {
		fileList: [],
		needRequireId: true,
		visibleUpload: false,
		testDailyReportList: [],
		testDailyReportPagination: {
			current: 1,
			total: 0,
			pageSize: 10,
			onChange: null
		},
		autoTestReportList: [],
		autoTestReportListPagination: {
			current: 1,
			total: 0,
			pageSize: 10,
			onChange: null
		},
		taskDocumentList: [],
		taskDocumentListPagination: {
			current: 1,
			total: 0,
			pageSize: 10,
			onChange: null
		},
		otherDocumentList: [],
		otherDocumentListPagination: {
			current: 1,
			total: 0,
			pageSize: 10,
			onChange: null
		},
	};
	autoTestReport = [
		{
			title: "项目名称",
			dataIndex: "taskCode",
			key: "programName"
		}, {
			title: "日期",
			dataIndex: "taskCode",
			key: "data"
		}, {
			title: "日报详情",
			key: "report",
			render: item => {
				return <div>
					<a>详情</a>
				</div>
			}
		}
	]

	componentWillMount() {
		this.getDocuments();
		this.getReports();
	}

	viewReport = (item) => {
		this.props.history.push({
			pathname: "/project/document/report",
			state: {
				programId: item.projectId,
				view: true,
				reportInfo: item
			}
		})
	};
	testDailyReportColumns = [
		{
			title: "项目名称",
			dataIndex: "projectName",
			key: "projectName",
		}, {
			title: "生成时间",
			dataIndex: "reportDate",
			key: "reportDate",
			render: record => moment(record).format("YYYY-MM-DD hh:mm:ss")
		},
		{
			title: "日报详情",
			key: "report",
			render: item => {
				return <a onClick={() => this.viewReport(item)}>查看详情</a>
			}
		}
	];
	getReports = async (current = 1, pageSize = 10) => {
		const {projectId} = this.props.project;
		const res = await getHistoryReports({projectId, current, pageSize});
		this.setState({
			testDailyReportList: res.data.list,
			testDailyReportPagination: {
				current: res.data.current,
				total: res.data.total,
				pageSize: res.data.pageSize,
				onChange: this.changeReportPage
			}
		});
	};
	changeReportPage = (current) => {
		this.getReports(current);
	};
	downloadDoc = (path) => {
		window.location = "http://" + window.__data.bucketName + ".oss-cn-hangzhou.aliyuncs.com/" + path;
	};
	getDocuments = async () => {
		const {project} = this.props;
		const res = await getTaskDocumentList(project.projectId);
		if (res.errCode !== 1) {
			return
		}
		const documents = res.data;
		let taskDocumentList = [];
		let otherDocumentList = [];
		documents.map((d) => {
			if (d.requireId === 0) {
				otherDocumentList.push(d)
			} else {
				taskDocumentList.push(d)
			}
		});
		this.setState({
			taskDocumentList,
			otherDocumentList,
		})
	};
	uploadDocument = () => {
		this.setState({
			visibleUpload: true
		})
	};
	cancelUpload = () => {
		this.setState({
			visibleUpload: false
		})
	};
	handleUploadFile = async () => {
		this.props.form.validateFieldsAndScroll(async (err, values) => {
			if (!err) {
				console.log("Received values of form: ", values);
				const {fileList} = this.state;
				const {taskId, requireId} = values;
				const {projectId} = this.props.project;
				const formData = new FormData();
				if (fileList.length === 0) {
					message.info("请选择需要上传的附件！")
					return
				}
				fileList.forEach((file) => {
					formData.append('files', file);
				});
				formData.append('programId', projectId);
				formData.append('requireId', requireId === undefined ? 0 : requireId);
				formData.append('taskId', taskId === undefined ? 0 : taskId);
				formData.append('creator', this.props.staffInfo.code);
				const res = await pushDocument(formData)
				if (res.errCode === 1) {
					message.success("上传文档成功！")
					this.setState({
						visibleUpload: false,
						fileList: []
					});
					this.getDocuments();
				} else {
					message.error("上传文档失败！")
				}
			}
		})

	};
	handleDeleteDoc = async (documentId) => {
		const param = {
			documentId
		};
		const res = await deleteProgramDocument(param);
		if (res.errCode !== 1) {
			message.error(res.data)
		} else {
			message.success("删除项目成功");
			this.getDocuments();
		}
	};
	cancelDeleteDoc = () => {
		console.log(e);
		message.error('Click on No');
	};
	taskDocumentColumns = [
		{
			title: "需求ID",
			dataIndex: "requireId",
			key: "requireId"
		}, {
			title: "任务名称",
			dataIndex: "taskCode",
			key: "taskCode",
			render: record => {
				return this.props.allDict.map((d) => {
					if (d.dictCode === record) {
						return d.dictValue
					}
				})
			}
		}, {
			title: "关联文档",
			dataIndex: "documentName",
			key: "documentName"
		}, {
			title: "操作",
			key: "path",
			render: item => {
				return <div>
					<a onClick={() => this.downloadDoc(item.documentPath)}>下载</a>&nbsp;&nbsp;&nbsp;&nbsp;
					<Popconfirm title="Are you sure delete this taskDocument?"
								onConfirm={() => this.handleDeleteDoc(item.id)} onCancel={() => this.cancelDeleteDoc}
								okText="Yes" cancelText="No">
						<a href="#">删除</a>
					</Popconfirm>
				</div>
			}
		}
	]
	otherDocumentColumns = [
		{
			title: "文档名称",
			dataIndex: "documentName",
			key: "documentName"
		}, {
			title: "操作",
			key: "path",
			render: item => {
				return <div>
					<a onClick={() => this.downloadDoc(item.documentPath)}>下载</a>&nbsp;&nbsp;&nbsp;&nbsp;
					<Popconfirm title="Are you sure delete this taskDocument?"
								onConfirm={() => this.handleDeleteDoc(item.id)} onCancel={() => this.cancelDeleteDoc}
								okText="Yes" cancelText="No">
						<a href="#">删除</a>
					</Popconfirm>
				</div>
			}
		}
	]

	addProjectReport = async (programId) => {
		this.props.history.push({
			pathname: "/project/document/report",
			state: {
				programId,
				add: true
			}
		})
	};

	render() {
		const {allProjects,project} = this.props;
		const {
			testDailyReportList, taskDocumentList, otherDocumentList, visibleUpload,
			testDailyReportPagination
		} = this.state;
		const {getFieldDecorator} = this.props.form;
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
			<Card title={getProgramName(allProjects, project.projectId)}>
				<Row>
					<Col span={5}/>
					<Col span={8}>
						<h3>测试日报</h3>
					</Col>
					<Col span={2}>
						<Button onClick={() => this.addProjectReport(project.projectId)} type="primary" block> 生成日报</Button>
					</Col>
				</Row>
				<Row justify="end">
					<Col span={5}/>
					<Col span={10}>
						<Table
							rowKey="id"
							bordered
							columns={this.testDailyReportColumns}
							dataSource={testDailyReportList}
							pagination={testDailyReportPagination}
						/>
					</Col>
				</Row>
				<Row>
					<Col span={5}/>
					<Col span={10}>
						<h1/>
						<h3>自动化测试报告</h3>
						<Table
							rowKey="id"
							bordered
							columns={this.autoTestReport}
							pagination={false}
						/></Col>
				</Row>
				<h1/>
				<Row>
					<Col span={5}/>
					<Col span={8}>
						<h3>任务关联文档</h3>
					</Col>
					<Col span={2}>
						<Button onClick={this.uploadDocument} type="primary" block> 新增文档</Button>
					</Col>
				</Row>
				<Row>
					<Col span={5}/>
					<Col span={10}>
						<Table
							rowKey="id"
							bordered
							columns={this.taskDocumentColumns}
							dataSource={taskDocumentList}
							pagination={false}
						/>
					</Col>
				</Row>
				<h1/>
				<Row>
					<Col span={5}/>
					<Col span={8}>
						<h3>其他文档</h3>
					</Col>
					<Col span={2}>
						<Button onClick={this.uploadDocument} type="primary" block> 新增文档</Button>
					</Col>
				</Row>
				<Row>
					<Col span={5}/>
					<Col span={10}>
						<Table
							rowKey="id"
							bordered
							columns={this.otherDocumentColumns}
							dataSource={otherDocumentList}
							pagination={false}
						/>
					</Col>
				</Row>
				<Modal
					title={'上传附件'}
					width={600}
					visible={visibleUpload}
					onCancel={() => this.cancelUpload()}
					onOk={() => this.handleUploadFile()}
				>
					<Form>
						<FormItem  {...formLayout} label="需求Id">
							{getFieldDecorator('requireId', {})(<Input/>)}
						</FormItem>
						<FormItem  {...formLayout} label="任务id">
							{getFieldDecorator('taskId', {})(<Input/>)}
						</FormItem>
						<FormItem  {...formLayout} label="附件">
							<Upload  {...props}>
								<Button>
									<Icon type="upload"/>选择需要上传文件
								</Button>
							</Upload>
						</FormItem>
					</Form>
				</Modal>
			</Card>
		);
	}
}
