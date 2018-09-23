import { actionTypes } from '../common/constants/actionTypes';
import { IImageEntity } from '../model';

export const membersReducer = (state: IImageEntity[] = [], action: any) => {
  switch (action.type) {
    case actionTypes.FETCH_IMAGES_COMPLETED:
      return handleFetchMembersCompleted(state, action.payload);
  }

  return state;
};

const handleFetchMembersCompleted = (state: IImageEntity[], payload: IImageEntity[]) => {
  return payload;
};