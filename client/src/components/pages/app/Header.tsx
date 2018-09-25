import * as React from 'react';
import { Nav, Navbar, NavItem } from 'react-bootstrap';
import { LinkContainer } from 'react-router-bootstrap';
import { Link } from 'react-router-dom';
import { IImageEntity } from '../../../model';

interface IProps {
  image: IImageEntity | undefined;
}

class Header extends React.Component<IProps, any> {
  public render() {
    const image: IImageEntity = this.props.image ? this.props.image : { tag: "", url: "", update_at: "" };
    const src = `${image.url}?update_at=${image.update_at}`;
    return (
        <header>
          <img src={src} />
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
