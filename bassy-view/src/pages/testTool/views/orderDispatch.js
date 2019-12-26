import React from 'react'
import {Button, Card, Divider, Form, Icon, Input, Select, Spin, Col, Row} from 'antd';
import {dispatchAppsVerify, dispatchFindRider, dispatchRiderFilter} from "../../../apis/index";

const FormItem = Form.Item;
const Option = Select.Option;
@Form.create()
export default class OrderDispatch extends React.Component {

	state = {
		verifyInfo: {
			appList: [
				{
					appName: "",
					envList: [
						{
							envName: "",
							status: true
						}
					]
				}
			],
			unFoundAppList: []
		},
		finderInfo: {
			exist: false,
			riders: []
		},
		filterInfo: {
			isTrue: true,
			loseReasons: [
				{
					code: "",
					desc: ""
				}
			]
		},
		loading: false,
		init: true
	};

	submit = async () => {
		this.props.form.validateFieldsAndScroll(async (err, values) => {
			if (!err) {
				console.log("Received values of form: ", values);
				this.setState({
					loading: true,
					init: false
				});
				let verify = await dispatchAppsVerify(values);
				let finder = await dispatchFindRider(values);
				let filter = await dispatchRiderFilter(values);
				if(verify.errCode===1&&verify.data!==""){
					this.state.verifyInfo=verify.data;
				}
				if(finder.errCode===1){
					this.state.finderInfo=finder.data;
				}
				if(filter.errCode===1){
					this.state.filterInfo=filter.data;
				}
				this.setState({
					loading: false,
				});
			}
		});
	};

	renderVerifyApps = (antIcon) => {

		const {loading, verifyInfo, init} = this.state;

		if (init || verifyInfo === null) {
			return;
		}
		if (loading) {
			return (<Spin indicator={antIcon}/>);
		}

		const foundList = verifyInfo.appList.map((app) => {
				const envList = app.envList;
				return (envList.map((env) => (
						<p>{app.appName + "【" + env.envName + "】"}
							<Icon type={env.status ? "check" : "close"}
								  style={{color: env.status ? "green" : "red"}}/>
						</p>
					))
				)
			}
		);

		const unFoundList = verifyInfo.unFoundAppList.map((uf) => (<p>{uf}</p>));

		return (
			<Row>
				<Col span={12}>
					<b><p>存在服务列表：</p></b>
					{foundList}
				</Col>
				<Col span={12}>
					<b><p>不存在服务列表：</p></b>
					{unFoundList}
				</Col>
			</Row>
		);
	};
	renderFindRider = (antIcon) => {
		const {init, finderInfo, loading} = this.state;

		if (init || finderInfo === null) {
			return;
		}
		if (loading) {
			return (<Spin indicator={antIcon}/>);
		}

		return (
			<div>
				<p>{"订单找到的骑手有：" + finderInfo.riders}</p>
				<p>订单找人目标骑手{finderInfo.exist ? "【找到】" : "【未找到】"}</p>
			</div>
		)
	};
	renderFilterOrder = (antIcon) => {
		const {init, filterInfo, loading} = this.state;

		if (init || filterInfo === null) {
			return;
		}

		if (loading) {
			return (<Spin indicator={antIcon}/>);
		}

		return (
			<div>
				<p>订单派单到目标骑手{filterInfo.isTrue ? "【成功】" : "【失败】"}</p>
				<p>{filterInfo.isTrue ? "" : "不满足以下条件："}</p>
				{filterInfo.loseReasons.map((lr) => (
					<p>{"【" + lr.code + "】" + lr.desc}</p>
				))}
			</div>
		)
	};

	render() {
		const {getFieldDecorator} = this.props.form;
		const antIcon = <Icon type="loading" style={{fontSize: 24}} spin/>;
		return (
			<Card
				title="派单异常自助查询"
				bodyStyle={{minHeight: 700}}
			>
				<Form
					layout="inline"
					style={{marginBottom: 12}}
					onSubmit={this.submit}
				>
					<FormItem label="环境">
						{getFieldDecorator("env")(
							<Select style={{width: 200}}>
								<Option key={"qa1"} value={"http://dwd-test-cherry-qa1.dwbops.com"}>{"qa1"}</Option>
								<Option key={"qa2"} value={"http://dwd-test-cherry-qa2.dwbops.com"}>{"qa2"}</Option>
								<Option key={"qa3"} value={"http://dwd-test-cherry-qa3.dwbops.com"}>{"qa3"}</Option>
							</Select>
						)}
					</FormItem>
					<FormItem label="订单id">
						{getFieldDecorator("orderId")(<Input style={{width: 200}}/>)}
					</FormItem>
					<FormItem label="骑手id">
						{getFieldDecorator("riderId")(<Input style={{width: 200}}/>)}
					</FormItem>
					<FormItem>
						<Button type="primary" htmlType="submit">
							检查
						</Button>
					</FormItem>
				</Form>
				<Divider orientation="left">服务检测</Divider>
				{
					this.renderVerifyApps(antIcon)
				}
				<Divider orientation="left">订单找人</Divider>
				{
					this.renderFindRider(antIcon)
				}
				<Divider orientation="left">派单过滤</Divider>
				{
					this.renderFilterOrder(antIcon)
				}
			</Card>
		)
	}
}