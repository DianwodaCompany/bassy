import {Col, message, Modal, Row} from "antd/lib/index";
import React, {useContext} from 'react';
import ConflictCaseForm from "./conflictCaseForm";
import {
	clearConflictCase,
	clearConflictCaseResultInfo,
	removeConflictCaseList,
	updateConflictCaseInfo,
	viewConflictSolve
} from "../../actions";
import {solveConflictCase} from "../../../../apis";
import {context} from "./provider";

const ConflictCaseSolve = ({initCaseList}) => {
	const {state, dispatch} = useContext(context);
	const {conflictSolveVisible, conflictCase} = state;

	const selectCurrentRowData = (field, value) => {
		dispatch(updateConflictCaseInfo({field, value}));
	};
	const selectCurrentCaseData = (caseInfo) => {
		dispatch(updateConflictCaseInfo({caseInfo}));
		console.info(caseInfo);
	};

	const clearResult = () => {
		dispatch(viewConflictSolve(false));
		dispatch(clearConflictCase());
		dispatch(clearConflictCaseResultInfo());
	};
	const confirm = async () => {
		let param = state.conflictCaseResult;
		param["programCaseId"] = conflictCase.programCase.id;
		param["baseCaseId"] = conflictCase.baseCase.id;
		param["version"] = conflictCase.baseCase.version;
		if (param.product == null || param.module == null || param.title == null
			|| param.pri == null || param.type == null || param.label == null) {
			message.error("结果用例属性不合法！");
			return;
		}
		await solveConflictCase(param);
		dispatch(viewConflictSolve(false));
		dispatch(clearConflictCase());
		dispatch(clearConflictCaseResultInfo());
		dispatch(removeConflictCaseList(conflictCase))
		initCaseList();
	};

	return <Modal title={"用例名称" + conflictCase.caseTitle}
				  width={1500}
				  visible={conflictSolveVisible}
				  onCancel={clearResult}
				  onOk={confirm}
				  cancelText="取消合并"
				  okText="确认合并"
	>
		<Row gutter={12}>
			<Col span={8}>
				<ConflictCaseForm type="end" caseInfo={conflictCase.programCase}
								  selectCurrentRowData={(field, value) => selectCurrentRowData(field, value)}
								  selectCurrentCaseData={(caseInfo) => selectCurrentCaseData(caseInfo)}
				/>
			</Col>
			<Col span={8}>
				<ConflictCaseForm type="center" caseInfo={state.conflictCaseResult}/>
			</Col>
			<Col span={8}>
				<ConflictCaseForm type="start" caseInfo={conflictCase.baseCase}
								  selectCurrentRowData={(field, value) => selectCurrentRowData(field, value)}
								  selectCurrentCaseData={(caseInfo) => selectCurrentCaseData(caseInfo)}
				/>
			</Col>
		</Row>
	</Modal>
};

export default ConflictCaseSolve;
