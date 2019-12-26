import {
    Add_Project_Detail,
    Add_Project_Mode,
    Add_Project_Time,
    Add_Project_Head_Component_Data,
    Add_Project_Name_Component_Data,
    Add_Project_Content_Component_Data,
    Project_Node, Add_Project_Id
} from './actionTypes';

const data={
    projectNode:[]
}
export default (state = data, action) => {
    switch (action.type) {
        case Add_Project_Detail: {
            return {
                ...state,
                projectDetail: action.projectDetail
            }
        }
        case Add_Project_Mode: {
            return {
                ...state,
                mode: action.mode
            }
        }
        case Add_Project_Time: {
            return {
                ...state,
                projectTime: action.projectTime
            }
        }
        case Add_Project_Head_Component_Data: {
            return {
                ...state,
                headData: action.headData
            }
        }
        case Add_Project_Name_Component_Data: {
            return {
                ...state,
                nameData: action.nameData
            }
        }
        case Add_Project_Content_Component_Data: {
            return {
                ...state,
                contentData: action.contentData
            }
        }
        case Project_Node: {
            return {
                ...state,
                projectNode: action.projectNode
            }
        }
        case Add_Project_Id: {
            return {
                ...state,
                projectId: action.projectId
            }
        }
        default: {
            return state;
        }
    }
}
