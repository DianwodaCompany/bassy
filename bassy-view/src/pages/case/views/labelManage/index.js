import React from 'react'
import {connect} from "react-redux";
import {Button, Card, Col, Form, Input, Popconfirm, Row, Select, Table} from 'antd/lib/index';
import LabelModal from "./labelModal";
import {deleteLabel, getLabelList, updateLabelInfo} from "../../../../apis";

const FormItem = Form.Item;

const formItemLayout = {
	labelCol: {
		xs: {span: 24},
		sm: {span: 8},
	},
	wrapperCol: {
		xs: {span: 24},
		sm: {span: 16},
	},
};

@connect(state => ({
	authResourceCodeList: state.common.authResourceCodeList,
	staffInfo: state.common.staffInfo,
	testCase: state.testCase
}))
@Form.create()
export default class BaseCase extends React.Component {

	state = {
		addLabel: false,
		labelList: []
	};

	columns = [
		{
			title: "编号",
			dataIndex: "id",
			key: "id",
			align: "center",
			width: "80px"
		},
		{
			title: "标签名称",
			dataIndex: "name",
			key: "name",
			align: "center"
		},
		{
			title: "标签类型",
			dataIndex: "type",
			key: "type",
			align: "center",
			render: item => {
				return this.props.testCase.allLabel.map(label => {
					if (label.type === item) return label.name;
				})
			}
		},
		{
			title: "引用次数",
			dataIndex: "citations",
			key: "citations",
			align: "center"
		},
		{
			title: "操作",
			align: "center",
			render: item => {
				return (
					<div>
						{
							<div>
								<Popconfirm
									title="测试用例关联的标签一并会被删除?"
									onConfirm={() => this.deleteLabel(item)}
								>
									<Button
										type="primary"
										size="small"
									>
										删除
									</Button>
								</Popconfirm>
								&nbsp;&nbsp;
								<Button
									type="primary"
									size="small"
									onClick={() => this.updateLabel(item)}
								>
									编辑
								</Button>
							</div>
						}
					</div>
				)
			}
		}
	];

	componentDidMount() {
		this.initLabelListInfo()
	}

	initLabelListInfo = async () => {
		this.searchLabelListInfo({});
	};

	searchLabelListInfo = async (param) => {
		const res = await getLabelList(param);
		if (res.data) {
			this.setState({labelList: res.data.list})
		}
	};

	addLabel = () => {
		this.setState({addLabel: true})
	};

	reallyAddLabel = () => {
		this.labelForm.props.form.validateFieldsAndScroll(async (err, values) => {
			if (!err) {
				const {defaultLabelInfo} = this.state;
				const res = await updateLabelInfo({
					id: defaultLabelInfo === undefined ? null : defaultLabelInfo.id,
					name: values.name,
					type: values.type
				});
				if (res.errCode === 1) {
					this.initLabelListInfo();
				}
				this.labelForm.props.form.resetFields();
				this.setState({addLabel: false, defaultLabelInfo: undefined})
			}
			console.info(values);
		});
	};

	cancelAddLabel = () => {
		this.labelForm.props.form.resetFields();
		this.setState({addLabel: false, defaultLabelInfo: undefined})
	};

	submit = () => {
		this.props.form.validateFieldsAndScroll(async (err, values) => {
			const res = await this.searchLabelListInfo({name: values.name, type: values.type});
			if (res.errCode === 1) {
				this.setState({labelList: res.data.list});
			}
			console.info(values);
		})
	};

	deleteLabel = async (item) => {
		const res = await deleteLabel({id: item.id});
		if (res.errCode === 1) {
			this.initLabelListInfo();
		}
	};

	updateLabel = async (item) => {
		this.setState({addLabel: true, defaultLabelInfo: item})
	};

	render() {
		const {addLabel, defaultLabelInfo, labelList} = this.state;
		const {allLabel} = this.props.testCase;
		const {getFieldDecorator} = this.props.form;
		return <Card>
			<Row gutter={24}>
				<Form onSubmit={this.submit}>
					<Col span={4}>
						<FormItem label={"标签名称"} {...formItemLayout}>
							{getFieldDecorator("name", {})(
								<Input/>
							)}
						</FormItem>
					</Col>
					<Col span={4}>
						<FormItem label={"标签类型"} {...formItemLayout}>
							{getFieldDecorator("type", {})(
								<Select>
									{
										allLabel.map(label => (
												<Select.Option value={label.type}>{label.name}</Select.Option>
											)
										)
									}
								</Select>
							)}
						</FormItem>
					</Col>
					<Col span={2}>
						<FormItem>
							<Button type="primary" onClick={() => {
								this.props.form.resetFields();
							}}>
								重置
							</Button>
						</FormItem>
					</Col>
					<Col span={2}>
						<FormItem>
							<Button type="primary" htmlType="submit">
								搜索
							</Button>
						</FormItem>
					</Col>
					<Col span={10}/>
					<Col span={2}>
						{
							<Button type="primary" onClick={this.addLabel}>+标签</Button>
						}
					</Col>
				</Form>
			</Row>
			<Row>
				<Table
					rowKey="id"
					columns={this.columns}
					dataSource={labelList}
				/>
			</Row>
			<LabelModal
				wrappedComponentRef={(form) => this.labelForm = form}
				addLabel={addLabel}
				reallyAddLabel={this.reallyAddLabel}
				cancelAddLabel={this.cancelAddLabel}
				labelInfo={defaultLabelInfo}
			/>
		</Card>
	}
}
