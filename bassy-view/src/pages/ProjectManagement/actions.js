import {
    Add_Project_Detail,
    Add_Project_Mode,
    Add_Project_Time,
    Add_Project_Head_Component_Data,
    Add_Project_Name_Component_Data,
    Add_Project_Content_Component_Data,
    Project_Node, Add_Project_Id
} from './actionTypes';


export const addProjectDetail = (detail) => ({
    type: Add_Project_Detail,
    projectDetail: detail
});

export const addProjectMode = (mode) => ({
    type: Add_Project_Mode,
    mode: mode
});

export const addProjectTime = (time) => ({
    type: Add_Project_Time,
    projectTime: time
});


export const addProjectHeadComponentData = (data) => ({
    type: Add_Project_Head_Component_Data,
    headData: data
});

export const addProjectNameComponentData = (data) => ({
    type: Add_Project_Name_Component_Data,
    nameData: data
});

export const addProjectContentComponentData = (data) => ({
    type: Add_Project_Content_Component_Data,
    contentData: data
});

export const addProjectNode = (data) => ({
    type: Project_Node,
    projectNode: data
});
export const addProjectId = (data) => ({
    type: Add_Project_Id,
    projectId: data
});

