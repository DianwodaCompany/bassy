import {menu} from './MenuReducer'
import {allTask, task} from './TaskReducer'
import {testPlan} from './ProjectReducer'

import {programModules, programTypes} from './ProgramReducer'
import {processModules} from "./ProcessReducer";
import {allDict} from "./DictionaryReducer";

import {reducer as configManagementReducer} from "../pages/ConfigManagement";
import {reducer as projectReducer} from "../pages/ProjectManagement";
import {reducer as commonReducer} from "../pages/Common";
import {reducer as linkReducer} from "../pages/UsefulLink";
import {combineReducers} from 'redux'
import {reducer as testCase} from "../pages/case"

export default combineReducers({
    menu,
    task,
    programModules,
    programTypes,
    processModules,
    testPlan,
    allTask,
    allDict,
    common: commonReducer,
    configManagement: configManagementReducer,
    project: projectReducer,
    link: linkReducer,
	testCase
})