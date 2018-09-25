import { actionTypes } from '../../../../common/constants/actionTypes';
import { IImageEntity } from '../../../../model';

import { imageAPI } from '../../../../api/image';

export const fetchImagesAction = () => (dispatch: any) => {
  imageAPI.fetchImages()
    .then((images) => {
      dispatch(fetchImagesCompleted(images));
    });
};

const fetchImagesCompleted = (images: IImageEntity[]) => ({
  payload: images,
  type: actionTypes.FETCH_IMAGES_COMPLETED,
});