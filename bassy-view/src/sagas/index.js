import { call, put, takeEvery, takeLatest, take, fork, select, cancel } from 'redux-saga/effects'
import { message } from 'antd'
import { menu, temp, logout, getProgramType, getAllEnableModules, getTestPlan, getTasks,getAllDict} from '../apis'
import { getAllEnableProcess } from "../apis/index";
import { getProgramDefaultModuleByType } from "../apis/index";
import {
  MENU_FETCH_SUCCEEDED,
  MENU_FETCH_REQUESTED,
  TASK_START,
  LOG_OUT,
  GET_PROGRAM_TYPE,
  ASYNC_GET_PROGRAM_TYPE,
  GET_ALL_ENABLE_MODULES,
  ASYNC_GET_ALL_ENABLE_MODULES,
  GET_ALL_ENABLE_PROCESS_MODULES,
  ASYNC_GET_ALL_ENABLE_PROCESS_MODULES,
  GET_PROGRAM_MODULES_BY_TYPE,
  ASYNC_GET_PROGRAM_MODULES_BY_TYPE,
  GET_ALL_TASK,
  ASYNC_GET_ALL_TASK,
  GET_ALL_DICTIONARY,
  ASYNC_GET_ALL_DICTIONARY,
  GET_TEST_PLAN,
  ASYNC_GET_TEST_PLAN
} from '../actionTypes'

function* fetchMenu() {
  try {
    const menuData = yield call(menu)
    yield put({
      type: MENU_FETCH_SUCCEEDED,
      data: menuData
    })
  } catch (error) {
    return error.toString()
  }
}

function* fetchTask() {
  try {
    const taskData = yield call(temp)
    return taskData
  } catch (error) {
    message.error(error.toString())
  }
}

function* doLogout() {
  try {
    const ret = yield call(logout)
    return ret
  } catch (error) {
    message.error(error.toString())
  }
}

function* doGetProgramTypes() {
  try {
    const ret = yield call(getProgramType)
    yield put({
      type: GET_PROGRAM_TYPE,
      data: ret.data
    })
  } catch (error) {
    message.error(error.toString())
  }
}

function* doGetAllEnableModules(params) {
  try {
    const ret = yield call(getAllEnableModules, params.payload)
    yield put({
      type: GET_ALL_ENABLE_MODULES,
      data: ret.data
    })
  } catch (error) {
    message.error(error.toString())
  }
}

function* doGetProgramModulesByType() {
  try {
    const ret = yield call(getProgramDefaultModuleByType)
    yield put({
      type: GET_PROGRAM_MODULES_BY_TYPE,
      data: ret.data
    })
  } catch (error) {
    message.error(error.toString())
  }
}

function* doGetAllEnableProcessModules(params) {
  try {
    const ret = yield call(getAllEnableProcess, params.payload)
    yield put({
      type: GET_ALL_ENABLE_PROCESS_MODULES,
      data: ret.data
    })
  } catch (error) {
    message.error(error.toString())
  }
}

function* doGetAllTask(params) {
    try {
        const ret = yield call(getTasks,params.payload)
        yield put({
            type: GET_ALL_TASK,
            data: ret.data
        })
    } catch (error) {
        message.error(error.toString())
    }
}

function* doGetAllDict() {
    try {
        const ret = yield call(getAllDict)
        yield put({
            type: GET_ALL_DICTIONARY,
            data: ret.data
        })
    } catch (error) {
        message.error(error.toString())
    }
}


function* doGetTestPlan (params) {
    try {
        const ret = yield call(getTestPlan, params.payload)
        yield put({
            type: GET_TEST_PLAN,
            data: ret.data
        })
    } catch (error) {
        message.error(error.toString())
    }
}
export default function* mySaga() {
  yield takeLatest(MENU_FETCH_REQUESTED, fetchMenu)
  yield takeLatest(TASK_START, fetchTask)
  yield takeLatest(LOG_OUT, doLogout)

  yield takeLatest(ASYNC_GET_PROGRAM_TYPE, doGetProgramTypes)
  yield takeLatest(ASYNC_GET_ALL_ENABLE_MODULES, doGetAllEnableModules)
  yield takeLatest(ASYNC_GET_ALL_ENABLE_PROCESS_MODULES, doGetAllEnableProcessModules)
  yield takeLatest(ASYNC_GET_PROGRAM_MODULES_BY_TYPE, doGetProgramModulesByType)
  yield takeLatest(ASYNC_GET_ALL_TASK, doGetAllTask)
  yield takeLatest(ASYNC_GET_ALL_DICTIONARY, doGetAllDict)
  yield takeLatest(ASYNC_GET_TEST_PLAN,doGetTestPlan)
}
