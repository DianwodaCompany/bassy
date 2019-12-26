import {Add_Project_Template_Detail, Add_Project_Template_Mode,
    Add_Template_Head_Component_Data,Add_Template_Content_Component_Data,Add_Task_Abnormal_Selected_Team_Code} from './actionTypes.js';

export default (state = {}, action) => {
    switch (action.type) {
        case Add_Project_Template_Detail: {
            return {
                ...state,
                projectTemplateDetail: action.projectTemplateDetail
            }
        }
        case Add_Project_Template_Mode: {
            return {
                ...state,
                mode: action.mode
            }
        }
        case Add_Template_Head_Component_Data: {
            return {
                ...state,
                headData: action.headData
            }
        }
        case Add_Template_Content_Component_Data: {
            return {
                ...state,
                contentData: action.contentData
            }
        }
        case Add_Task_Abnormal_Selected_Team_Code: {
            return {
                ...state,
                selectedTeamCode: action.selectedTeamCode
            }
        }
        default: {
            return state;
        }
    }
}
