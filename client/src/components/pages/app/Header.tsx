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
    const imgTag = this.renderHeroImg(this.props.image);
    return (
        <header>
          {imgTag}
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

  private renderHeroImg(image: IImageEntity | undefined) {
    if (image) {
      const src = `${image.url}?update_at=${image.update_at}`;
      return <img src={src} />
    } else {
      return null;
    }
  }
}

export default Header;
