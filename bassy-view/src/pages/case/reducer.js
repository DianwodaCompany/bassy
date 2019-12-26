import {
	Add_All_Product,
	Add_Conflict_Case,
	Add_Conflict_Case_List,
	Update_Test_Case_Detail,
	Case_Pri,
	Case_Type,
	Clear_Conflict_Case,
	Clear_Conflict_Case_Result,
	Product_Module_List,
	Remove_Conflict_Case_List,
	Update_Case_Label,
	Update_Case_Step,
	Update_Conflict_Case,
	Update_Program_Case_Info,
	View_Conflict_List,
	View_Conflict_Solve
} from './actionTypes';

export const init = {
	allProduct: [
		{
			id: 1,
			name: ""
		}
	],
	productModuleList: [
		{
			id: -1,
			name: ""
		},
	],
	caseType: [
		{
			code: "0",
			name: "接口用例"
		}, {
			code: "1",
			name: "功能用例"
		}
	],
	casePri: [
		{
			code: 1,
		},
		{
			code: 2,
		},
		{
			code: 3,
		}
	],
	caseDetail: {
		id: -1,
		product: -1,
		module: -1,
		title: '',
		type: '',
		precondition: '',
		pri: '',
		step: [],
		label: [],
		lastEditedBy: '',
		lastEditedDate: '',
		version: ''
	},
	allLabel: [
		{
			type: "base",
			name: "基础标签"
		},
		{
			type: "system",
			name: "系统标签"
		},
	],
	conflictCaseResult: {
		id: null,
		product: null,
		module: null,
		require: null,
		title: null,
		pri: null,
		type: null,
		status: null,
		parentCase: null,
		precondition: null,
		step: [],
		label: []
	},
	conflictCaseList: [
		{
			caseId: null,
			caseTitle: null,
			baseCase: {
				step: [],
				label: []
			}, programCase: {
				step: [],
				label: []
			}
		}
	],
	conflictCase: {
		caseId: null,
		caseTitle: null,
		baseCase: {
			step: [],
			label: []
		}, programCase: {
			step: [],
			label: []
		}
	},
	conflictSolveVisible: false,
	conflictListVisible: false,
	programCaseInfo: {
		requireId: -1,
		requireName: "",
		caseList: [],
		importModalView: false,
		total: 0,
		pageNum: 1,
		pageSize: 20,
		caseListView:true,
		editByMe: false,
		notPass: false
	}
};

export default (state = init, action) => {
	switch (action.type) {
		case Add_All_Product: {
			return {
				...state,
				allProduct: action.allProduct
			}
		}
		case Product_Module_List: {
			return {
				...state,
				productModuleList: action.productModuleList
			}
		}
		case Case_Type: {
			return {
				...state,
				caseType: action.caseType
			}
		}
		case Update_Test_Case_Detail: {
			return {
				...state,
				caseDetail: action.caseDetail
			}
		}
		case Update_Case_Step: {
			return {
				...state,
				step: action.step
			}
		}
		case Update_Case_Label: {
			return {
				...state.caseDetail,
				label: action.label
			}
		}
		case Case_Pri: {
			return {
				...state,
				casePri: action.casePri
			}
		}
		case Add_Conflict_Case: {
			return {
				...state,
				conflictCase: action.conflictCase
			}
		}
		case Clear_Conflict_Case: {
			return {
				...state,
				conflictCase: {
					caseId: null,
					caseTitle: null,
					baseCase: {
						label: [],
						step: []
					},
					programCase: {
						label: [],
						step: []
					}
				}
			}
		}
		case Add_Conflict_Case_List: {
			return {
				...state,
				conflictCaseList: action.conflictCaseList
			}
		}
		case Remove_Conflict_Case_List: {
			const conflictCaseList = Object.assign([], state.conflictCaseList);
			const removeCase = action.removeCase;
			const newConflictCaseList = conflictCaseList.filter(c => c.caseId !== removeCase.caseId);
			return {
				...state,
				conflictCaseList: newConflictCaseList,
				conflictListVisible: newConflictCaseList.length !== 0
			}
		}
		case Update_Conflict_Case: {
			let newConflictCaseResult = Object.assign({}, state.conflictCaseResult);
			const {field, value, stepId, labelId, caseInfo} = action.conflictCaseResult;
			const caseFields = Object.keys(newConflictCaseResult);
			if (caseInfo !== undefined) {
				const conflictCaseResult = Object.assign({}, caseInfo);
				conflictCaseResult.id = null;
				return {
					...state,
					conflictCaseResult
				}
			}
			let newField = {};
			newField[field] = value;
			if (caseFields.indexOf(field) > -1) {
				const result = Object.assign({}, newConflictCaseResult, newField);
				return {
					...state,
					conflictCaseResult: result
				}
			}
			const stepFields = Object.keys(newConflictCaseResult.step[0]);
			if (stepFields.indexOf(field) > -1) {
				newConflictCaseResult.step.map(step => {
					if (step.id === stepId) {
						Object.assign(step, newField);
					}
				});
				return {
					...state,
					conflictCaseResult: newConflictCaseResult
				}
			}
			const labelFields = Object.keys(newConflictCaseResult.label[0]);
			if (labelFields.indexOf(field) > -1) {
				labelFields.step.map(label => {
					if (label.id === labelId) {
						Object.assign(label, newField);
					}
				});
				return {
					...state,
					conflictCaseResult: newConflictCaseResult
				}
			}
			return {
				...state,
				conflictCaseResult: newConflictCaseResult
			}
		}
		case Clear_Conflict_Case_Result: {
			let newConflictCaseResult = Object.assign({}, state.conflictCaseResult);
			const caseFields = Object.keys(newConflictCaseResult);
			caseFields.map(field => {
				if (field === "label" || field === "step") {
					newConflictCaseResult[field] = [];
				} else {
					newConflictCaseResult[field] = null;
				}
			});
			const result = Object.assign({}, newConflictCaseResult)
			return {
				...state,
				conflictCaseResult: result
			}
		}
		case View_Conflict_List: {
			return {
				...state,
				conflictListVisible: action.conflictListVisible
			}
		}
		case View_Conflict_Solve: {
			return {
				...state,
				conflictSolveVisible: action.conflictSolveVisible
			}
		}
		case Update_Program_Case_Info: {
			const programCaseInfo = Object.assign({}, state.programCaseInfo, action.programCaseInfo);
			return {
				...state,
				programCaseInfo
			}
		}
		default: {
			return state;
		}
	}
}
