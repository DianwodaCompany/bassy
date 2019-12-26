import {Select} from "antd";
import React from 'react';
import {getStaffList} from "../../../apis";


export default class StaffCode extends React.Component {

	constructor(props) {
		super(props);
		this.state = {
			staffCode: '',
			staffList: []
		}
	}


	componentWillReceiveProps(nextProps) {
		this.setState({
			staffCode: nextProps.value === undefined ? null : nextProps.value,
		});
	}

	handleStaffChange = staffCode => {
		if (!('value' in this.props)) {
			this.setState({staffCode});
		}
		this.triggerChange(staffCode);
	};

	triggerChange = changedValue => {
		// Should provide an event to pass value to Form.
		const {onChange,onSelect} = this.props;
		if (onChange) {
			onChange(changedValue);
		}
		if (onSelect) {
			onSelect(changedValue);
		}
	};

	render() {

		const {staffList} = this.state;
		const changPersonKeyWord = async (input) => {
			if (input === undefined || input === "") {
				return;
			}
			const staffList = await getStaffList(input);
			if (staffList.data) {
				this.setState({staffList: staffList.data})
			}
		};

		return <Select
			showSearch
			placeholder="维护人"
			onSearch={changPersonKeyWord}
			onChange={this.handleStaffChange}
			style={{width: "100%"}}
			value={this.state.staffCode}
		>
			{staffList.map((s) => (
				<Select.Option value={`(${s.code})${s.name}`} key={s.code}>
					{"(" + s.code + ")" + s.name}
				</Select.Option>
			))}
		</Select>
	}

};
