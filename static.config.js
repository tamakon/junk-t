import axios from 'axios'

export default {
  getSiteData: () => ({
    title: 'React Static',
  }),
  getRoutes: async () => {
    const { data: posts } = await axios.get('https://jsonplaceholder.typicode.com/posts')
    return [
      {
        path: '/',
        component: 'src/containers/Home',
      },
      {
        path: '/contact',
        component: 'src/containers/Contact',
      },
      {
        path: '/profile',
        component: 'src/containers/ProfileList',
      },
      {
        is404: true,
        component: 'src/containers/404',
      },
    ]
  },
}
