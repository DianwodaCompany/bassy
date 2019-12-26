import { MENU_FETCH_SUCCEEDED } from '../actionTypes'

const initMenu = {}

export function menu(state = initMenu, action) {
  switch (action.type) {
    case MENU_FETCH_SUCCEEDED:
      return Object.assign({}, state, action.data)
    default:
      return state
  }
}