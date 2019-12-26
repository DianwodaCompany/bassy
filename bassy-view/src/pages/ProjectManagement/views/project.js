import React from "react";
import {connect} from "react-redux";
import CSSModules from "react-css-modules";
import moment from "moment";

import {Button, Card, Col, Form, message, Row, Spin} from "antd";
import styles from "./index.less";
import ProjectHead from "./projectHead"
import ProjectName from "./projectName"
import {TemplateContent} from "../../Common";
import {
	addProgram,
	editProgram,
	getAllEnableModules,
	getAllProjects,
	getProcessModuleById,
	getProgramModuleById
} from "../../../apis";
import {addAllEnableProjectTemplates, addAllProjects} from "../../Common/actions";
import {addProjectTime} from "../../ProjectManagement/actions";

const FormItem = Form.Item;

@CSSModules(styles)
@connect(state => ({
	staffInfo: state.common.staffInfo,
	allStages: state.common.allStages,
	user: state.user,
	programTypes: state.programTypes,
	common: state.common,
	processModules: state.processModules,
	headData: state.project.headData,
	nameData: state.project.nameData,
	contentData: state.project.contentData,
	mode: state.project.mode,
	detail: state.project.projectDetail
}))
export default class Project extends React.Component {
	state = {
		visible: false,
		loading: false,
		processModules: [],
		programName: "",
		startTime: "",
		endTime: "",
		projectTime: [moment(new Date(), 'YYYY-MM-DD 00:00:00'), moment(new Date(), 'YYYY-MM-DD 23:59:59')]

	};
	getAllEnableModules = async (pageNum = 1) => {
		let params = {
			pageNum,
			pageSize: 10,
		};
		let result = await getAllEnableModules(params);
		this.props.dispatch(addAllEnableProjectTemplates(result.data));
	};

	viewProjectList = () => {
		this.props.history.push({
			pathname: '/project'
		})
	};
	submit = e => {
		e.preventDefault();
		let obj = {};
		this.props.headData.form.validateFieldsAndScroll((err, values) => {
			if (!err) {
				console.log("Received values of head form: ", values);
				obj = Object.assign(obj, values);
			}
		});
		this.props.nameData.form.validateFieldsAndScroll((err, values) => {
			if (!err) {
				console.log("Received values of name form: ", values);
				obj = Object.assign(obj, values);
			}
		});
		this.props.contentData.form.validateFieldsAndScroll(async (err, values) => {
			if (!err) {
				console.log("Received values of form: ", values);
				const res = this.props.contentData.collectData();
				obj = Object.assign(obj, values, res);
				obj.startTime = new Date(moment(obj.programTm[0]._d).startOf('day').format('YYYY-MM-DD HH:mm:ss'));
				obj.endTime = new Date(moment(obj.programTm[1]._d).endOf('day').format('YYYY-MM-DD HH:mm:ss'));
				obj.coreNodes.map((core) => {
					core.startTime = core.startTime === "" ? obj.startTime : new Date(moment(core.startTime).startOf('day').format('YYYY-MM-DD HH:mm:ss'));
					core.endTime = core.endTime === "" ? obj.endTime : new Date(moment(core.endTime).endOf('day').format('YYYY-MM-DD HH:mm:ss'));
				});
				obj.coreNodes = JSON.stringify(obj.coreNodes);
				// if (obj.programType === "normal" && obj.workId == null && obj.requires.length === 0) {
				// 	message.error("关联需求和关联工单至少填写一项!");
				// 	return;
				// }
				if ((obj.programType === "program" || obj.programType === "urgent") && obj.requires.length === 0) {
					message.error("请关联需求!");
					return;
				}
				for (let r in obj.requires) {
					if (obj.requires[r].requireName == null) {
						message.error("有未填写完整的关联需求!");
						return;
					}
					delete obj.requires[r].key
				}
				obj.persons = JSON.stringify(obj.persons);
				this.setState({loading: true});

				let user = this.props.staffInfo.code;
				if (this.props.mode === "add") {
					obj.creator = user;
					obj.createTm = new Date();
					console.info(obj);
					const insert = await addProgram(obj);
					if (insert.data) {
						message.success("新增项目成功");
						let result = await getAllProjects();
						this.props.dispatch(addAllProjects(result.data));
						this.viewProjectList()
					}
				}

				this.setState({loading: false});
			}
		});
	};

	back = () => {
		this.props.history.goBack();
	};

	update = e => {
		e.preventDefault();
		let obj = {};
		this.props.nameData.form.validateFieldsAndScroll(async (err, values) => {
			if (!err) {
				console.log("Received values of name form: ", values);
				obj = Object.assign(obj, values);
			}
		});

		this.props.contentData.form.validateFieldsAndScroll(async (err, values) => {
			if (!err) {
				console.log("Received values of form: ", values);
				const res = this.props.contentData.collectData();
				obj = Object.assign(obj, values, res);
				obj.modifier = this.props.staffInfo.code;
				obj.id = parseInt(window.location.hash.split("/")[3]);
				obj.coreNodes.map((core) => {
					core.startTime = core.startTime === "" ? obj.startTime : new Date(moment(core.startTime).startOf('day').format('YYYY-MM-DD HH:mm:ss'));
					core.endTime = core.endTime === "" ? obj.endTime : new Date(moment(core.endTime).endOf('day').format('YYYY-MM-DD HH:mm:ss'));
				});
				obj.coreNodes = JSON.stringify(obj.coreNodes);
				obj.persons = JSON.stringify(obj.persons);
				const edit = await editProgram(obj);
				if (edit.data) {
					message.success("编辑项目成功");
					let result = await getAllProjects();
					this.props.dispatch(addAllProjects(result.data));
					this.viewProjectList()
				} else {
					message.error(edit.msg);
				}
			}
		});
	};

	async componentWillMount() {
		this.props.dispatch(addProjectTime(this.state.projectTime));
		this.getAllEnableModules();
		if (this.props.mode === "add") {

		} else {
			const detail = this.props.detail;
			const programModule = await getProgramModuleById(detail.programModule);
			let processModule;
			if (detail && detail.processModule) {
				processModule = await getProcessModuleById(detail.processModule);
			}
			this.setState({
				programModuleDetail: programModule.data,
				programModuleName: programModule.data.moduleName,
				processModuleName: processModule ? processModule.data.moduleName : "",
			});
		}
	}

	render() {
		const {mode} = this.props;
		const {programModuleName, processModuleName} = this.state;
		return (
			<Spin spinning={this.state.loading}>
				<Form onSubmit={mode === "add" ? this.submit : this.update}>
					<Card>
						<ProjectHead
							programModuleName={programModuleName}
							processModuleName={processModuleName}
						/>
						<ProjectName
						/>
						<TemplateContent
							history={this.props.history}
						/>
						{mode === "add" && (
							<Row>
								<Col span={3} offset={8}>
									<FormItem>
										<Button onClick={this.back}>
											取消
										</Button>
									</FormItem>
								</Col>
								<Col span={6}>
									<FormItem>
										<Button type="primary" htmlType="submit">
											确认
										</Button>
									</FormItem>
								</Col>
							</Row>
						)}
						{
							mode === "edit" &&
							<Row>
								<Col span={3} offset={8}>
									<FormItem>
										<Button onClick={this.back}>
											返回
										</Button>
									</FormItem>
								</Col>
								<Col span={6}>
									<FormItem>
										<Button type="primary" htmlType="submit">
											更新
										</Button>
									</FormItem>
								</Col>
							</Row>
						}
					</Card>
				</Form>
			</Spin>
		);
	}
}
