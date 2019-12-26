import { GET_TEST_PLAN } from '../actionTypes'

const initPlan = []

export function testPlan(state = initPlan, action) {
  switch (action.type) {
    case GET_TEST_PLAN:
      return action.data
    default:
      return state
  }
}