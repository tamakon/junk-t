import {Action} from 'redux'

enum ActionNames {
  INC = 'counter/increment',
  DEC = 'counter/decrement',
}

interface IncrementAction extends Action {
  type: ActionNames.INC
  plusAmount: number
}
export const incrementAmount = (amount: number): IncrementAction => ({
  plusAmount: amount,
  type: ActionNames.INC
})

interface IDecrementAction extends Action {
  minusAmount: number,
  type: ActionNames.DEC
}

export const decrementAmount = (amount: number): IDecrementAction => ({
  minusAmount: amount,
  type: ActionNames.DEC
})

export interface ICounterState {
  num: number
}
  
  export type CounterActions = IncrementAction | IDecrementAction

  const initialState:ICounterState = {num: 0};

  export default function reducer(state: ICounterState = initialState, action: CounterActions): ICounterState {
    switch (action.type) {
      case ActionNames.INC:
        return {num: state.num + action.plusAmount}
      case ActionNames.DEC:
        return {num: state.num - action.minusAmount}
      default:
        return state
    }
  }