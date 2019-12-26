import {Add_Project_Template_Detail, Add_Project_Template_Mode,
    Add_Template_Head_Component_Data,Add_Template_Content_Component_Data,Add_Task_Abnormal_Selected_Team_Code} from './actionTypes';


export const addProjectTemplateDetail = (detail) => ({
    type: Add_Project_Template_Detail,
    projectTemplateDetail: detail
});


export const addProjectTemplateMode = (mode) => ({
    type: Add_Project_Template_Mode,
    mode: mode
});

export const addTemplateHeadComponentData = (data) => ({
    type: Add_Template_Head_Component_Data,
    headData: data
});

export const addTemplateContentComponentData = (data) => ({
    type: Add_Template_Content_Component_Data,
    contentData: data
});

export const addTaskAbnormalSelectedTeamCode = (data) => ({
    type: Add_Task_Abnormal_Selected_Team_Code,
    selectedTeamCode: data
});


