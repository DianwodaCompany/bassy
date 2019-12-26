import {Button, List, Modal} from "antd/lib/index";
import React, {useContext} from "react";
import {addConflictCase, viewConflictList, viewConflictSolve} from "../../actions";
import {context} from "./provider";

const ConflictCaseList = () => {

	const {state,dispatch} = useContext(context);
	const {conflictCaseList,conflictListVisible} = state;
	const solveConflictCase = (caseInfo) => {
		dispatch(addConflictCase(caseInfo));
		dispatch(viewConflictSolve(true));
	};

	const notConflictSolve = ()=>{
		dispatch(viewConflictList(false));
	};

	return <div>
		<Modal
			title={"版本冲突用例"}
			visible={conflictListVisible}
			onCancel={notConflictSolve}
			footer={[
				<Button type={"primary"} onClick={notConflictSolve}>不处理</Button>
			]}>
			<List
				rowKey={"caseId"}
				dataSource={conflictCaseList}
				renderItem={item => (
					<List.Item
						actions={[<a onClick={() => solveConflictCase(item)}>去处理</a>]}>{item.caseTitle}</List.Item>)
				}
			/>
		</Modal>
	</div>
};

export default ConflictCaseList;
