import {
  GET_ALL_ENABLE_PROCESS_MODULES
} from '../actionTypes'

export function processModules(state = [], action) {
  switch (action.type) {
    case GET_ALL_ENABLE_PROCESS_MODULES:
      return action.data
    default:
      return state
  }
}
