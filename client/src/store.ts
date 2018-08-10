import {Action, combineReducers, createStore} from 'redux'
import counter, {CounterActions, ICounterState} from './counter/module'

export default createStore(
  combineReducers({
    counter
  })
)

export interface IReduxState {
  counter : ICounterState
}

export type ReduxAction = CounterActions | Action