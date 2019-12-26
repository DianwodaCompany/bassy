import {Add_All_Enable_Templates,
    Add_All_Process_Task,
    Add_Staff_Info,
    Add_Authenticated_Resource_codes,
    Add_Abnormal_Reason_Team,
    Add_Abnormal_Reason_Type,
    Add_Current_View,
    Add_All_Project,
    Add_Project_Big_Types} from './actionTypes.js';

const commonInit = {
    allStages: [
        {name: '可行性评审', code: 'FEASIBILITY_REVIEW'},
        {name: '需求评审', code: 'REQUIREMENT_REVIEW'},
        {name: '技术评审', code: 'TECHNICAL_REVIEW'},
        {name: '系统开发', code: 'SYSTEM_DEVELOPMENT'},
        {name: '系统联调', code: 'SYSTEM_COUPLET'},
        {name: '测试用例评审', code: 'TESTCASE_REVIEW'},
        {name: '分支测试', code: 'BRANCH_TEST'},
        {name: '主干测试', code: 'MASTER_TEST',},
        {name: '灰发测试', code: 'GREY_TEST',},
        {name: '线上回归', code: 'ONLINE_TEST'}
    ],
    projectStatus: [
        {name: '待排期', code: 'init'},
        {name: '已排期', code: 'scheduled',},
        {name: '进行中', code: 'processing',},
        {name: '已关闭', code: 'close',},
        {name: '已暂停', code: 'pause'},
        {name: '已结束', code: 'end'}
    ],
    taskStatus:[
        {name: '未开始', code: 'init'},
        {name: '进行中', code: 'processing'},
        {name: '已暂停', code: 'pause'},
        {name: '已关闭', code: 'close'},
        {name: '已完成', code: 'end'}
    ],
    abnormalReasonLevel:["P1", "P2", "P3"],
    menu: [],
    allProjects:[],
    abnormalReasonTeam:[],
    authResourceCodeList:[],
    staffInfo:{},
};

export default (state = commonInit, action) => {
    switch (action.type) {
        case Add_All_Enable_Templates: {
            return {
                ...state,
                allEnableProjectTemplates: action.allEnableProjectTemplates
            }
        }
        case Add_Project_Big_Types: {
            return {
                ...state,
                projectBigTypes: action.projectBigTypes
            }
        }
        case Add_All_Process_Task: {
            return {
                ...state,
                allTasksWithProcess: action.allTasksWithProcess
            }
        }
        case Add_All_Project: {
            return {
                ...state,
                allProjects: action.allProjects
            }
        }
        case Add_Current_View: {
            return {
                ...state,
                currentView: action.currentView
            }
        }
        case Add_Staff_Info: {
            return {
                ...state,
                staffInfo: action.staffInfo
            }
        }
        case Add_Authenticated_Resource_codes: {
            return {
                ...state,
                authResourceCodeList: action.authResourceCodeList
            }
        }
        case Add_Abnormal_Reason_Team: {
            return {
                ...state,
                abnormalReasonTeam: action.abnormalReasonTeam
            }
        }
        case Add_Abnormal_Reason_Type: {
            return {
                ...state,
                abnormalReasonType: action.abnormalReasonType
            }
        }
        default: {
            return state;
        }
    }
}
