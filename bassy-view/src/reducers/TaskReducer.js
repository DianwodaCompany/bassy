import { TASK_START,GET_ALL_TASK, TASK_UPDATE } from '../actionTypes'

const initTask = [];

export function task(state = initTask, action) {
  switch (action.type) {
    case TASK_START: 
      return action.data
    case TASK_UPDATE:
      return action.data
    default:
      return state
  }
}

export function allTask(state = initTask, action) {
  switch (action.type) {
    case GET_ALL_TASK:
      return action.data.list;
    default:
      return state
  }
}