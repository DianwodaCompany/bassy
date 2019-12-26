import React from "react";
import {connect} from "react-redux";
import CSSModules from "react-css-modules";
import {Button, Col, Form, Icon, Input, Row, Select, Spin, message, Popconfirm, Modal} from "antd";
import styles from "../../../ConfigManagement/views/index.less";
import {
	getZentaoStories,
	addProgramRequire,
	getProgram,
	deleteProgramRequire,
	transferProgramRequire,
	getProgramName
} from '../../../../apis/index';
import {addProjectDetail, addProjectMode} from "../../../ProjectManagement/actions";

const FormItem = Form.Item;
const Option = Select.Option;
const InputGroup = Input.Group;

const formLayout = {
	labelCol: {
		span: 5
	},
	wrapperCol: {
		span: 10
	}
};

@CSSModules(styles)
@connect(state => ({
	allStages: state.common.allStages,
	staffInfo: state.common.staffInfo,
	mode: state.common.currentView === "ConfigManagement" ? state.project.mode : state.configManagement.mode,
	detail: state.common.currentView === "ConfigManagement" ? state.project.projectDetail : state.configManagement.projectTemplateDetail
}))
@Form.create()
export default class Requires extends React.Component {
	state = {
		requires: [],
		fetching: false,
		fetchData: [],
		projects: [],
		selectedValue: undefined,
		visibleAddRequireButton: true,
		visibleTransfer: false,
		confirmLoading: false,
		transferRequire: {},
		targetProgramId: -1
	};

	componentWillMount() {
		this.setState({requires: this.props.detail.requires});
	};

	fetchDemands = async (keyword) => {
		if (keyword != null && keyword !== "") {
			this.setState({fetchData: [], fetching: true});
			let resp = await getZentaoStories(keyword);
			const data = resp.data.map(d => (
				{
					text: `[${d.ztStoryId}] ${d.ztStoryName} (项目 ${d.ztProjectName == null ? "未关联项目" : d.ztProjectName} )`,
					value: d.ztProjectId === null ? `${d.ztStoryId},${d.ztStoryName}` : `${d.ztStoryId},${d.ztStoryName},${d.ztProjectId},${d.ztProjectName}`,
				}));
			if (data.length === 0) {
				this.setState({
					fetchData: [{text: "禅道无此需求", value: 0}], fetching: false
				});
			} else {
				this.setState({fetchData: data, fetching: false});
			}
		}
	};

	handleSelect = (currentRequire, value) => {
		const {requires} = this.state;
		//新增需求做去重处理
		const newRequireId = value.key.split(",")[0];
		for (let require of requires) {
			if (require.requireId === newRequireId || require.requireId === parseInt(newRequireId)) {
				message.warn("需求已存在！");
				return;
			}
		}
		for (let require of requires) {
			if (require.key === currentRequire.key) {
				require.requireId = value.key.split(",")[0];
				require.requireName = value.key.split(",")[1];
				require.ztProjectId = value.key.split(",").length > 2 ? value.key.split(",")[2] : null;
				require.ztProjectName = value.key.split(",").length > 2 ? value.key.split(",")[3] : null;
				console.log("选中的值" + value.key);
			}
		}
		this.props.getRequires(requires);
		this.setState({requires: requires, fetchData: [], fetching: false, selectedValue: value.key});
	};

	addRequire = () => {
		const {detail, mode} = this.props;
		const requiresDetail = detail.requires ? detail.requires : [];
		const {requires} = this.state;
		const data = requires.length > 0 ? requires : requiresDetail;
		this.setState({selectedValue: undefined});
		const d = {
			key: +new Date(),
			requireId: undefined,
			requireName: undefined,
			ztProjectId: undefined,
			ztProjectName: undefined,
			newRequire: true
		};
		data.push(d);
		this.props.getRequires(data);
		//编辑模式只允许逐个需求添加
		if (mode === "edit") {
			this.setState({
				requires: data,
				visibleAddRequireButton: false
			});
		} else {
			this.setState({
				requires: data,
			});
		}

	};

	deleteRequire = async index => {
		const {detail, mode} = this.props;
		const {requires} = this.state;
		if (mode === "edit") {
			const require = requires[index];
			if (require.requireId !== undefined) {
				const params = {
					programId: detail.id,
					requireId: require.requireId,
					modifier: this.props.staffInfo.code
				};
				const res = await deleteProgramRequire(params);
				if (res.errCode === 0) {
					message.error("删除需求失败！");
					return
				}
			}
		}
		requires.splice(index, 1);
		this.props.getRequires(requires);
		this.setState({
			requires,
			visibleAddRequireButton: true
		});
	};

	/**
	 * 该需求是否可以被编辑
	 * 查看模式不能被编辑
	 * 编辑模式中的老需求（非新增需求）不能被编辑
	 */
	requireCanBeEdit = (currentRequire) => {
		const {mode} = this.props;
		return mode === "view" || (mode === "edit" && currentRequire.requireId !== undefined)
	};

	/**
	 * 编辑模式下新增需求
	 */
	addNewRequire = async (currentRequire) => {
		if (currentRequire.requireId === undefined) {
			message.warn("需求id不能为空");
			return;
		}
		const {detail} = this.props;
		let requires = [];
		requires.push(currentRequire);
		detail.requires = requires;
		detail.coreNodes = JSON.stringify(detail.coreNodes);
		detail.persons = JSON.stringify(detail.persons);
		const res = await addProgramRequire(detail);
		if (res.errCode === 1) {
			let result = await getProgram(detail.id);
			this.props.dispatch(addProjectDetail(result.data));
			this.props.dispatch(addProjectMode("view"));
			this.props.history.push({
				pathname: '/project/testPlan/edit',
				state: {
					edit: true
				}
			});
		} else {
			message.error(res.msg);
		}
	};

	//迁移需求
	transferRequire = async () => {
		const {
			transferRequire,
			targetProgramId,
			requires
		} = this.state;
		const sourcesProgramId = this.props.detail.id;
		this.setState({confirmLoading: true});
		const param = {
			requireId: transferRequire.requireId,
			sourcesProgramId,
			targetProgramId
		};
		const res = await transferProgramRequire(param);
		if (res.errCode === 1) {
			let newRequires = [];
			requires.map((r) => {
				if (r.requireId !== transferRequire.requireId) {
					newRequires.push(r);
				}
			});
			console.info(newRequires);
			this.props.getRequires(newRequires);
			this.setState({confirmLoading: false, visibleTransfer: false, projects: [], requires: newRequires})
		}
	};
	//取消迁移
	cancelTransfer = () => {
		this.setState({
			visibleTransfer: false,
			confirmLoading: false
		})
	};

	//筛选项目
	fetchProject = async (keyword) => {
		if (keyword != null && keyword !== "") {
			const res = await getProgramName(keyword);
			if (res.errCode === 1) {
				const projects = res.data.map((p) => ({
					key: p.programId,
					value: `[` + p.programId + `]` + p.programName
				}));
				this.setState({projects});
			}
		}
	};

	//选择迁移项目
	selectProject = (targetProgramId) => {
		this.setState({targetProgramId})
	};

	render() {
		const {detail, mode} = this.props;
		const {
			requires, fetching, fetchData, visibleAddRequireButton,
			visibleTransfer, confirmLoading, transferRequire,
			projects
		} = this.state;
		return (
			<div>
				<Row>
					<Col span={5} style={{textAlign: "right"}}>
						项目相关需求：
					</Col>
				</Row>
				<FormItem>
					{requires.map((currentRequire, index) => {
						return (
							<FormItem
								label="需求："
								labelCol={{
									span: 5
								}}
								wrapperCol={{
									span: 16
								}}
								key={index}
								required
							>
								<Row type="flex" gutter={4}>
									<InputGroup>
										<Col span={3}>
											<Input
												placeholder="需求ID"
												disabled={this.requireCanBeEdit(currentRequire)}
												value={currentRequire.requireId}
												type="number"
											/>
										</Col>
										<Col span={10}>
											<Select
												labelInValue
												combobox
												showSearch={true}
												placeholder={mode === "view" || mode === "edit" ? currentRequire.requireName : "输入关键字搜索禅道需求"}
												notFoundContent={fetching ? <Spin size="small"/> : null}
												filterOption={false}
												showArrow={false}
												onSearch={this.fetchDemands}
												onSelect={value => this.handleSelect(currentRequire, value)}
												style={{width: '100%'}}
												disabled={this.requireCanBeEdit(currentRequire)}
											>
												{fetchData.map(fetch =>
													<Option key={fetch.value}>{fetch.text}</Option>
												)}
											</Select>
										</Col>
										<Col span={6}>
											<Input
												placeholder={mode === "add" ? "禅道项目" : ""}
												disabled={this.requireCanBeEdit(currentRequire)}
												value={currentRequire.ztProjectName === null || currentRequire.ztProjectName === "null" ? "无关联项目" : currentRequire.ztProjectName}
											/>
										</Col>
										{mode !== "view" && (
											<Col span={1}>
												<Popconfirm title="关联任务将一并设置为关闭状态，确定删除需求吗？"
															icon={<Icon type="question-circle-o"
																		style={{color: 'red'}}/>}
															okText="确定" cancelText="取消"
															onConfirm={() => this.deleteRequire(index)}
												>
													<Icon
														type="minus-circle"
														style={{color: 'red', fontSize: '24px', marginLeft: '6px'}}
													/>
												</Popconfirm>
											</Col>
										)}
										{mode !== "view" && (
											<Col span={1}>
												<Popconfirm title="确定迁移需求吗？"
															icon={<Icon type="question-circle-o"
																		style={{color: 'red'}}/>}
															okText="确定" cancelText="取消"
															onConfirm={() => (this.setState({
																visibleTransfer: true,
																transferRequire: currentRequire
															}))}
												>
													<Icon type="logout"
														  style={{color: 'red', fontSize: '24px', marginLeft: '6px'}}/>
												</Popconfirm>
											</Col>
										)}
										{mode === "edit" && currentRequire.newRequire && (
											<Col span={1}>
												<Button
													type="primary"
													onClick={() => this.addNewRequire(currentRequire)}
												>
													确定新增
												</Button>
											</Col>
										)
										}
									</InputGroup>
								</Row>
							</FormItem>
						);
					})}
					{mode !== "view" && visibleAddRequireButton && (
						<Row>
							<Col
								span={6}
								style={{
									textAlign: "right"
								}}
							>
								<Button
									icon="plus"
									type="primary"
									size="small"
									onClick={this.addRequire}
								>
									新增需求
								</Button>
							</Col>
						</Row>
					)}

				</FormItem>
				<Modal
					title="需求迁移"
					visible={visibleTransfer}
					confirmLoading={confirmLoading}
					width={550}
					onOk={this.transferRequire}
					onCancel={this.cancelTransfer}
				>
					<Row
						type="flex"
						align="middle"
						style={{height: "60px"}}
					>
						<Col span={4}>迁移需求：</Col>
						<Col span={20}>{transferRequire.requireName}</Col>
					</Row>
					<Row
						type="flex"
						align="middle"
						style={{height: "60px"}}
					>
						<Col span={4}>当前项目：</Col>
						<Col span={20}>{this.props.detail.programName}</Col>
					</Row>
					<Row
						type="flex"
						align="middle"
						style={{height: "60px"}}
					>
						<Col span={4}>目标项目：</Col>
						<Col span={20}
						>
							{(
								<Select
									filterOption={false}
									style={{width: "100%"}}
									showSearch={true}
									onSearch={this.fetchProject}
									onSelect={value => this.selectProject(value)}
								>
									{projects.map((p) => (
										<Option key={p.key}>{p.value}</Option>
									))}
								</Select>
							)}
						</Col>
					</Row>
				</Modal>
			</div>
		);
	}
}
