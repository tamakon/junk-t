import { IImageEntity } from '../../model';
// import { members } from './mockData';

// let mockMembers = members;

const fetchImages = (): Promise<IImageEntity[]> => {
  return Promise.resolve([
    {
      tag: 'test',
      url: 'https://via.placeholder.com/350x100',
      update_at: "https://avatars.githubusercontent.com/u/1457912?v=3"
    },
  ]);
};


export const imageAPI = {
  fetchImages,
}