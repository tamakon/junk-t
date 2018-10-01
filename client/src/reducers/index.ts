import { combineReducers } from 'redux';
import { IImageEntity } from '../model';
import { imagesReducer } from './images';

export interface IState {
  images: IImageEntity[];
};

export const state = combineReducers<IState>({
  images: imagesReducer,
});