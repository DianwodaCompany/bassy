import {connect} from "react-redux";
import {Form, Input, Modal, Select} from "antd";
import React from "react";


const formLayout = {
	labelCol: {
		span: 5
	},
	wrapperCol: {
		span: 15
	}
};

@connect(state => ({
	staffInfo: state.common.staffInfo,
	testCase: state.testCase
}))
@Form.create()
export default class LabelModal extends React.Component {


	render() {
		const {reallyAddLabel, cancelAddLabel, addLabel, labelInfo} = this.props;
		const {allLabel} = this.props.testCase;
		const {getFieldDecorator} = this.props.form;
		return <Modal
			title={labelInfo === undefined ? "新增标签" : "编辑标签"}
			visible={addLabel}
			onOk={reallyAddLabel}
			onCancel={cancelAddLabel}
		>
			<Form {...formLayout}>
				<Form.Item label={"标签名称"}>
					{getFieldDecorator("name", {
						rules: [{
							required: true,
							message: "不能为空"
						}],
						initialValue: labelInfo === undefined ? "" : labelInfo.name
					})(
						<Input/>
					)}
				</Form.Item>
				<Form.Item label={"标签类型"}>
					{getFieldDecorator("type", {
						rules: [{
							required: true,
							message: "不能为空"
						}],
						initialValue: labelInfo === undefined ? "" : labelInfo.type
					})(
						<Select>
							{
								allLabel.map(label => (
										<Select.Option value={label.type}>{label.name}</Select.Option>
									)
								)
							}
						</Select>
					)}
				</Form.Item>
			</Form>
		</Modal>

	}

}
