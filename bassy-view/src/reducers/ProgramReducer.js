/**
 * 项目模版
 */

import {GET_PROGRAM_TYPE,
    GET_ALL_ENABLE_MODULES,
    GET_PROGRAM_MODULES_BY_TYPE,
    GET_ALL_PROJECT} from '../actionTypes'

const pTypes = {}

export function programTypes(state = pTypes, action) {
    switch (action.type) {
        case GET_PROGRAM_TYPE:
            return Object.assign({}, state, action.data)
        default:
            return state
    }
}

const pModules = {
    list: [],
    currentPage: 1,
    totalCount: 0,
    pageSize: 10,
}

export function programModules(state = pModules, action) {
    switch (action.type) {
        case GET_ALL_ENABLE_MODULES:
            return action.data
        case GET_PROGRAM_MODULES_BY_TYPE:
            return action.data


        default:
            return state
    }

}
