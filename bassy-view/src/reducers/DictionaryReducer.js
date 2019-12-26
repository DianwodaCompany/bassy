import {
    GET_ALL_DICTIONARY
} from '../actionTypes'

const initDictionary = []

export function allDict(state = initDictionary, action) {
    switch (action.type) {
        case GET_ALL_DICTIONARY:
          return Object.assign([], state, action.data)
        default:
            return state
    }
}