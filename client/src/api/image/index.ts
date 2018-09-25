import { IImageEntity } from '../../model';

let cache: IImageEntity[] = [];

const fetchImages = (): Promise<IImageEntity[]> => {
  // tslint:disable-next-line
  cache = [
    {
      tag: 'top',
      url: 'https://via.placeholder.com/350x100',
      update_at: "2018-09-16T19:48:20.928111+09:00"
    },
  ];
  return Promise.resolve(cache);
};


export const imageAPI = {
  fetchImages,
}