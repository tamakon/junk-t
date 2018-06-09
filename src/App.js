import React from 'react'
import { Router, Link } from 'react-static'
import { hot } from 'react-hot-loader'
//
import Routes from 'react-static-routes'
import logoImg from './logo.png'

import './app.css'

const App = () => (
  <Router>
    <div>
    <img src={logoImg} alt="" />
      <nav>
        <Link exact to="/">HOME</Link>
        <Link to="/profile">PROFILE</Link>
        <Link to="/contact">CONTACT</Link>
      </nav>
      <div className="content">
        <Routes />
      </div>
    </div>
  </Router>
)

export default hot(module)(App)
