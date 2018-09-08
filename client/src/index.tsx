import * as React from 'react';
import * as ReactDOM from 'react-dom';
import {Provider} from 'react-redux';
import { Redirect, Route } from 'react-router';
import { HashRouter } from "react-router-dom"
import App from './App';
import Contact from './Contact';
import './index.css';
import Profile from './Profile';
import registerServiceWorker from './registerServiceWorker';
import store from './store';

ReactDOM.render(
  <Provider store={store}>
    <HashRouter>
      <div>
        <Route exact={true} path="/home" component={App} />
        <Route exact={true} path="/contact" component={Contact} />
        <Route exact={true} path="/profile" component={Profile} />
        <Redirect exact={true} from='/' to='/home'/>
      </div>
    </HashRouter>
  </Provider>,
  document.getElementById('root') as HTMLElement
);
registerServiceWorker();
