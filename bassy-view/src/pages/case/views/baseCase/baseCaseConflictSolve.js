import {context} from "../programCase/provider";
import React, {useContext, useEffect} from "react";
import {addConflictCase, viewConflictSolve} from "../../actions";
import ConflictCaseSolve from "../programCase/conflictCaseSolve";

const BaseCaseConflictSolve = ({conflictCaseInfo}) => {

	const {state, dispatch} = useContext(context);
	const solveConflictCase = (caseInfo) => {
		if (caseInfo.caseId === null) return;
		dispatch(addConflictCase(caseInfo));
		dispatch(viewConflictSolve(true));
	};

	useEffect(() => {
		solveConflictCase(conflictCaseInfo)
	}, [conflictCaseInfo]);
	return <ConflictCaseSolve/>
};

export default BaseCaseConflictSolve;
