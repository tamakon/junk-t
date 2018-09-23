import { combineReducers } from 'redux';
import { IImageEntity } from '../model';
import { membersReducer } from './images';

export interface IState {
  members: IImageEntity[];
};

export const state = combineReducers<IState>({
  members: membersReducer,
});