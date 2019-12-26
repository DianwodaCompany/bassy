import {
	Update_Test_Case_Detail,
	Add_All_Product,
	Case_Pri,
	Case_Type,
	Product_Module_List,
	Update_Case_Label,
	Update_Case_Step,
	Update_Conflict_Case,
	Clear_Conflict_Case_Result,
	Add_Conflict_Case_List,
	Add_Conflict_Case,
	Clear_Conflict_Case,
	View_Conflict_Solve,
	View_Conflict_List,
	Remove_Conflict_Case_List,
	Update_Program_Case_Info
} from './actionTypes';

export const addAllProduct = (detail) => ({
	type: Add_All_Product,
	allProduct: detail
});
export const getCaseType = (detail) => ({
	type: Case_Type,
	caseType: detail
});
export const getCasePri = (detail) => ({
	type: Case_Pri,
	casePri: detail
});
export const updateProductModuleList = (detail) => ({
	type: Product_Module_List,
	productModuleList: detail
});
export const updateTestCaseDetail = (detail) => ({
	type: Update_Test_Case_Detail,
	caseDetail: detail
});
export const updateCaseStep = (detail) => ({
	type: Update_Case_Step,
	caseDetail: {step: detail}
});
export const updateCaseLabel = (detail) => ({
	type: Update_Case_Label,
	caseDetail: {label: detail}
});
export const addConflictCase = (detail) => ({
	type: Add_Conflict_Case,
	conflictCase: detail
});
export const clearConflictCase = (detail) => ({
	type: Clear_Conflict_Case,
	conflictCase: detail
});
export const addConflictCaseList = (detail) => ({
	type: Add_Conflict_Case_List,
	conflictCaseList: detail
});
export const removeConflictCaseList = (detail) => ({
	type: Remove_Conflict_Case_List,
	removeCase: detail
});
export const updateConflictCaseInfo = ({field, value, stepId, labelId, caseInfo}) => ({
	type: Update_Conflict_Case,
	conflictCaseResult: {
		field,
		value,
		stepId,
		labelId,
		caseInfo
	}
});
export const clearConflictCaseResultInfo = (detail) => ({
	type: Clear_Conflict_Case_Result,
	conflictCaseResult: detail
});
export const viewConflictSolve = (detail) => ({
	type: View_Conflict_Solve,
	conflictSolveVisible: detail
});
export const viewConflictList = (detail) => ({
	type: View_Conflict_List,
	conflictListVisible: detail
});
export const updateProgramCaseInfo = (detail) => ({
	type: Update_Program_Case_Info,
	programCaseInfo: detail
});
