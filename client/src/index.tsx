import * as React from 'react';
import * as ReactDOM from 'react-dom';
import {Provider} from 'react-redux';
import { Redirect, Route } from 'react-router';
import { HashRouter } from "react-router-dom"
import App from './App';
import './index.css';
import registerServiceWorker from './registerServiceWorker';
import store from './store';

ReactDOM.render(
  <Provider store={store}>
    <HashRouter>
      <div>
        <Route exact={true} path="/web" component={App} />
        <Route exact={true} path="/admin" component={App} />
        <Redirect exact={true} from='/' to='/web'/>
      </div>
    </HashRouter>
  </Provider>,
  document.getElementById('root') as HTMLElement
);
registerServiceWorker();
