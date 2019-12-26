import {Add_All_Enable_Templates,
    Add_Project_Big_Types,
    Add_All_Process_Task,
    Add_Current_View,
    Add_All_Project,
    Add_Abnormal_Reason_Team,
    Add_Abnormal_Reason_Type,
    Add_Authenticated_Resource_codes,
    Add_Staff_Info} from './actionTypes';

export const addAllEnableProjectTemplates = (data) => ({
    type: Add_All_Enable_Templates,
    allEnableProjectTemplates: data
});

export const addProjectBigTypes = (data) => ({
    type: Add_Project_Big_Types,
    projectBigTypes: data
});

export const addAllProcessTasks = (data) => ({
    type: Add_All_Process_Task,
    allTasksWithProcess: data
});

export const addAllProjects = (data) => ({
    type: Add_All_Project,
    allProjects: data
});

export const addCurrentView = (view) => ({
    type: Add_Current_View,
    currentView: view
});

export const addStaffInfo = (staffInfo) => ({
    type: Add_Staff_Info,
    staffInfo: staffInfo
});

export const addAuthResourceCodeList = (authResourceCodeList) => ({
    type: Add_Authenticated_Resource_codes,
    authResourceCodeList: authResourceCodeList
});

export const AddAbnormalReasonTeam = (abnormalReasonTeam) => ({
    type: Add_Abnormal_Reason_Team,
    abnormalReasonTeam: abnormalReasonTeam
});

export const AddAbnormalReasonType = (abnormalReasonType) => ({
    type: Add_Abnormal_Reason_Type,
    abnormalReasonType: abnormalReasonType
});

