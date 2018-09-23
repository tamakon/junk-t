import { actionTypes } from '../../../common/constants/actionTypes';
import { IImageEntity } from '../../../model';

import { imageAPI } from '../../../api/image';

export const fetchMembersAction = () => (dispatch: any) => {
  imageAPI.fetchImages()
    .then((members) => {
      dispatch(fetchMembersCompleted(members));
    });
};

const fetchMembersCompleted = (members: IImageEntity[]) => ({
  payload: members,
  type: actionTypes.FETCH_IMAGES_COMPLETED,
});