import React from "react";
import CSSModules from "react-css-modules";
import moment from "moment";
import {Button, Card, Col, Form, Input, message, Row, Select, Table, Divider} from "antd";
import styles from "./index.less";
import {programList, updateProgramStatus, getProgram} from "../../../apis";
import AbnormalReason from "./abnormalReason"
import {connect} from "react-redux";
import {addProjectMode, addProjectDetail, addProjectNode, addProjectId} from "../../ProjectManagement/actions";
import {addCurrentView} from "../../Common/actions";

const FormItem = Form.Item;
const Option = Select.Option;

const categories = {
	'program': '立项项目',
	'inner': '内部项目',
	'urgent': '紧急项目',
	'normal': '常规项目',
};


@CSSModules(styles)
@connect(state => ({
	staffInfo: state.common.staffInfo,
	projectStatus: state.common.projectStatus,
}))
@Form.create()
export default class ProjectManagement extends React.Component {
	state = {
		loading: false,
		showAbnormalReason: false,
		programId: "",
		programStatus: "",
		list: [],
		pageNum: 1,
		pageSize: 10,
		total: 0,
		searchData: {
			name: "",
			status: "",
			owner: [],
		}
	};

	viewProjectDocument = (item) => {
		let projectNode = [];
		item.coreNodes.map((node) => {
			projectNode.push(node.projectNode)
		});
		this.props.dispatch(addProjectNode(projectNode));
		this.props.dispatch(addProjectId(item.id));
		this.props.history.push({
			pathname: '/project/document',
		})
	};

	updateStatus = async (id, status) => {
		if (status === "end") {
			const params = {
				programId: id,
				status: status
			};
			const res = await updateProgramStatus(params);
			if (res.errCode === 1) {
				message.info(res.data)
			} else {
				message.error(res.data)
			}
			this.getProgramList(this.state.pageNum, JSON.parse(localStorage.getItem("projectSearchData")));
		} else {
			this.setState({
				showAbnormalReason: true,
				programId: id,
				programStatus: status
			})
		}
	};

	cancelUpdateStatus = () => {
		this.setState({
			showAbnormalReason: false
		})
	};

	changePage = page => {
		this.setState({pageNum: page});
		this.getProgramList(page, JSON.parse(localStorage.getItem("projectSearchData")));
	};

	getProgramList = async (pageNum = 1, param = this.state.searchData) => {
		let data = Object.assign({pageNum, pageSize: 10}, param);
		let owner = param.owner;
		for (let i in owner) {
			switch (owner[i]) {
				case "creator": {
					let creator = this.props.staffInfo.code;
					Object.assign(data, {creator});
					break;
				}
				case "to": {
					let to = this.props.staffInfo.code;
					Object.assign(data, {to});
				}
			}
		}
		const res = await programList(data);
		const {currentPage, pageSize, list, totalCount} = res.data;
		this.setState({
			list,
			pageSize,
			total: totalCount,
			pageNum: currentPage,
			showAbnormalReason: false,
		});
	};

	handleSearch = e => {
		e.preventDefault();
		this.props.form.validateFieldsAndScroll(async (err, values) => {
			if (!err) {
				console.log("Received values of form: ", values);
				this.getProgramList(1, values);
				localStorage.setItem("projectSearchData", JSON.stringify(values))
			}
		});
	};

	handleReset = () => {
		localStorage.setItem("projectSearchData", JSON.stringify(this.state.searchData))
		this.props.form.setFieldsValue({})
	};

	//刷新当前页面
	resetProjectManagement = () => {
		this.getProgramList(this.state.pageNum, JSON.parse(localStorage.getItem("projectSearchData")));
	};

	componentWillMount = () => {
		this.props.dispatch(addCurrentView("ConfigManagement"));
		this.getProgramList(this.state.pageNum, JSON.parse(localStorage.getItem("projectSearchData")));
	};

	addProgram = () => {
		this.props.dispatch(addProjectMode("add"));
		this.props.dispatch(addProjectDetail(
			{
				parentCode: "normal",
				isLoop: "N",
				workId: "",
				coreNodes: [{"projectNode": "", "startTime": "", "endTime": ""}],
				requires:[]
			}
		));
		this.props.history.push({
			pathname: '/project/add'
		})
	};

	viewProgram = async (item) => {
		let result = await getProgram(item.id);
		this.props.dispatch(addProjectDetail(result.data));
		this.props.dispatch(addProjectMode("view"));
		this.props.history.push({
			pathname: '/project/detail/' + item.id
		})
	};

	editProgram = async (item) => {
		let result = await getProgram(item.id);
		this.props.dispatch(addProjectDetail(result.data));
		this.props.dispatch(addProjectMode("edit"));
		this.props.history.push({
			pathname: '/project/detail/' + item.id
		})
	};

	editTestPlan = async (item, edit) => {
		let result = await getProgram(item.id);
		this.props.dispatch(addProjectDetail(result.data));
		this.props.dispatch(addProjectMode("view"));
		this.props.history.push({
			pathname: '/project/testPlan/edit',
			state: {
				edit
			}
		});
	};

	columns = [
		{
			title: "id",
			dataIndex: "id",
			key: "programId"
		}, {
			title: "项目名称",
			key: "programName",
			render: item => <a onClick={() => this.viewProgram(item)}>{item.programName}</a>,
		},
		{
			title: "项目类型",
			key: "programType",
			render: item => {
				return categories[item.programType]
			}
		},
		{
			title: "项目状态",
			dataIndex: "status",
			render: record => {
				return this.props.projectStatus.map(v => {
					if (v.code === record) {
						return v.name;
					}
				});
			}
		},
		{
			title: "创建时间",
			dataIndex: "createTm",
			render: record => moment(record).format("YYYY-MM-DD HH:mm:ss")
		},
		{
			title: "开始时间",
			dataIndex: "startTime",
			render: record => moment(record).format("YYYY-MM-DD HH:mm:ss")
		},
		{
			title: "结束时间",
			dataIndex: "endTime",
			render: record => moment(record).format("YYYY-MM-DD HH:mm:ss")
		},
		{
			title: "操作",
			render: item => {
				switch (item.status) {
					case "init":
						return (
							<div>
								<Button
									type="primary"
									size="small"
									onClick={() => this.editProgram(item)}
								>
                                    编辑
								</Button>&nbsp;&nbsp;
								<Button
									type="primary"
									size="small"
									onClick={() => this.editTestPlan(item, true)}
								>
									新建测试计划
								</Button>&nbsp;&nbsp;
								<Button type="danger" size="small" onClick={() => this.updateStatus(item.id, "close")}>
									关闭
								</Button>
							</div>
						);
					case "scheduled":
						return (
							<div>
								<Button
									type="primary"
									size="small"
									onClick={() => this.editProgram(item)}
								>
									编辑
								</Button>&nbsp;&nbsp;
								<Button
									type="primary"
									size="small"
									onClick={() => this.editTestPlan(item, true)}
								>
									修改测试计划
								</Button>&nbsp;&nbsp;
								<Button type="primary" size="small" onClick={() => this.updateStatus(item.id, "pause")}>
									暂停
								</Button>&nbsp;&nbsp;
								<Button type="danger" size="small" onClick={() => this.updateStatus(item.id, "close")}>
									关闭
								</Button>
							</div>
						);
					case "processing":
						return (
							<div>
								<Button
									type="primary"
									size="small"
									onClick={() => this.editProgram(item)}
								>
									编辑
								</Button>&nbsp;&nbsp;
								<Button
									type="primary"
									size="small"
									onClick={() => this.editTestPlan(item, true)}
								>
									修改测试计划
								</Button>&nbsp;&nbsp;
								<Button type="primary" size="small" onClick={() => this.updateStatus(item.id, "pause")}>
									暂停
								</Button>&nbsp;&nbsp;
								<Button type="danger" size="small" onClick={() => this.updateStatus(item.id, "close")}>
									关闭
								</Button>&nbsp;&nbsp;
								<Button type="danger" size="small" onClick={() => this.updateStatus(item.id, "end")}>
									完成
								</Button>
							</div>
						);
					case "pause":
						return (
							<div>
								<Button
									type="primary"
									size="small"
									onClick={() => this.editProgram(item)}
								>
									编辑
								</Button>&nbsp;&nbsp;
								<Button
									type="primary"
									size="small"
									onClick={() => this.editTestPlan(item, true)}
								>
									重修测试计划
								</Button>&nbsp;&nbsp;
								<Button type="danger" size="small" onClick={() => this.updateStatus(item.id, "close")}>
									关闭
								</Button>
							</div>
						);
					case "close":
						return (
							<div>
								<Button
									disabled={item.hasTestPlan}
									type="primary"
									size="small"
									onClick={() => this.editTestPlan(item, false)}
								>
									查看测试计划
								</Button>
							</div>
						);
					case "end":
						return (
							<div>
								<Button
									disabled={item.hasTestPlan}
									type="primary"
									size="small"
									onClick={() => this.editTestPlan(item, false)}
								>
									查看测试计划
								</Button>
							</div>
						);
				}
			}
		},
		{
			title: "相关文档",
			key: "document",
			render: item => {
				return (
					<a onClick={() => this.viewProjectDocument(item)}>查看明细</a>
				)
			}
		}
	];

	render() {
		const {getFieldDecorator} = this.props.form;
		const {list, pageNum, pageSize, total, showAbnormalReason, programId, programStatus} = this.state;
		const {projectStatus} = this.props;
		const owners = [
			{
				id: 'creator',
				name: '由我创建',
			},
			{
				id: 'to',
				name: '我是大TO',
			},
		];
		const search = JSON.parse(localStorage.getItem("projectSearchData"));
		return (
			<div>
				<Card bordered={false}>
					<div>
						<Form
							layout="inline"
							onSubmit={this.handleSearch}
							style={{marginBottom: 12}}
						>
							<Row type="flex" justify="start" align="bottom">
								<Col span={7}>
									<FormItem label="项目名称">
										{getFieldDecorator("name", {
											initialValue: search.name,
										})(<Input placeholder="填写 项目名称" style={{width: 200}}/>)}
									</FormItem>
								</Col>
								<Col span={7}>
									<FormItem label="项目状态">
										{getFieldDecorator("status", {
											initialValue: search.status || "",
										})(
											<Select style={{width: 200}} placeholder="选择 项目状态">
												{projectStatus.map(v => <Option key={v.code}>{v.name}</Option>)}
											</Select>
										)}
									</FormItem>
								</Col>
								<Col span={10}>
									<FormItem label="owner">
										{getFieldDecorator("owner", {
											initialValue: search.owner,
										})(
											<Select
												mode="multiple"
												style={{width: 400}}
												placeholder="选择 owner"
											>
												{owners.map(owner => <Option key={owner.id}>{owner.name}</Option>)}
											</Select>
										)}
									</FormItem>
								</Col>
							</Row>
							<Row type="flex" justify="start" align="bottom">
								<Col>
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
										<Button
											icon="plus"
											style={{textAlign: 'right'}}
											type="primary"
											onClick={this.addProgram}>
											新增
										</Button>
									</FormItem>
								</Col>
							</Row>
						</Form>
					</div>

					<Table
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
					{showAbnormalReason && <AbnormalReason
						resetProjectManagement={this.resetProjectManagement}
						programId={programId}
						status={programStatus}
						cancelUpdateStatus={this.cancelUpdateStatus}
					/>
					}
				</Card>
			</div>

		);
	}
}
