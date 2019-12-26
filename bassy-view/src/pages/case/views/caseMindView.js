import React from 'react'
import Provider from '../rmind/context/';
import ThemeProvider from '../rmind/features/ThemeProvider'
import CaseProvider from '../../case/views/programCase/provider'
import Nav from '../rmind/features/Nav';
import Main from '../rmind/features/Main'
import {connect} from "react-redux";

@connect(state => ({
    authResourceCodeList: state.common.authResourceCodeList,
}))
export default class CaseMindView extends React.Component {

    render() {
        const {authResourceCodeList} = this.props;
        return (
            <Provider>
                <ThemeProvider>
                    <CaseProvider>
                        <div id={"caseRoot"}>
                            <Nav authResourceCodeList={authResourceCodeList}/>
                            <Main/>
                        </div>
                    </CaseProvider>
                </ThemeProvider>
            </Provider>
        )
    }
}
