import * as React from 'react';
import * as ReactDOM from 'react-dom';
import {Provider} from 'react-redux';
import { AppRootPageContainer } from './components/pages';
import './index.css';
import registerServiceWorker from './registerServiceWorker';
import { store } from './store';

ReactDOM.render(
  <Provider store={store}>
    <AppRootPageContainer />
  </Provider>,
  document.getElementById('root') as HTMLElement
);
registerServiceWorker();
