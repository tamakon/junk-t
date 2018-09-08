import * as React from 'react';
import { Link } from 'react-router-dom';

class Header extends React.Component {
  public render() {
    return (
        <header className="App-header">
          <div>
            <Link to="/home">HOME</Link>
          </div>
          <div>
            <Link to="/profile">PROFILE</Link>
          </div>
          <div>
            <Link to="/contact">CONTACT</Link>
          </div>
        </header>
    );
  }
}

export default Header;
