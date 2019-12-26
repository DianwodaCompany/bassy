import React from "react";
import ProgramCaseDetail from "./programCaseDetail";
import Provider from "./provider";
import {connect} from "react-redux";

@connect(state => ({
    staffInfo: state.common.staffInfo,
    authResourceCodeList: state.common.authResourceCodeList,
}))
export default class Index extends React.Component {

	render() {
		return <Provider>
			<ProgramCaseDetail props={this.props}/>
		</Provider>

	}
}
