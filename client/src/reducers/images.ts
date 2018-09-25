import { actionTypes } from '../common/constants/actionTypes';
import { IImageEntity } from '../model';

export const imagesReducer = (state: IImageEntity[] = [], action: any) => {
  switch (action.type) {
    case actionTypes.FETCH_IMAGES_COMPLETED:
      return handleFetchImagesCompleted(state, action.payload);
  }

  return state;
};

const handleFetchImagesCompleted = (state: IImageEntity[], payload: IImageEntity[]) => {
  return payload;
};