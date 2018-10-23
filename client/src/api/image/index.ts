import { IImageEntity } from '../../model';

let cache: IImageEntity[];

const fetchMembersAsync = (): Promise<IImageEntity[]> => {
  const membersURL = `http://localhost:8000/srv/api/images/`;

  return fetch(membersURL)
    .then((response) => response.json())
    .then((data: IImageEntity[]) => {
      cache = data;
      return data;
    });
};

const fetchImages = (): Promise<IImageEntity[]> => {
  if (cache) {
    return Promise.resolve(cache);
  } else {
    return fetchMembersAsync();
  }
};

export const imageAPI = {
  fetchImages,
}