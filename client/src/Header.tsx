import * as React from 'react';
import { Nav, Navbar, NavItem } from 'react-bootstrap';
import { LinkContainer } from 'react-router-bootstrap';
import { Link } from 'react-router-dom';

class Header extends React.Component {
  public render() {
    return (
        <header>
          <Navbar>
            <Navbar.Header>
              <Navbar.Brand>
              <Link to="/home">JUNK-TION</Link>
              </Navbar.Brand>
            </Navbar.Header>
            <Nav>
              <LinkContainer to="/profile">
                <NavItem eventKey={1}>PROFILE</NavItem>
              </LinkContainer>
              <LinkContainer to="/contact">
                <NavItem eventKey={2}>CONTACT</NavItem>
              </LinkContainer>
            </Nav>
          </Navbar>
        </header>
    );
  }
}

export default Header;
