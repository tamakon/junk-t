import * as _ from 'lodash';
import * as React from 'react';
import { Redirect, Route, Switch } from 'react-router';
import { HashRouter } from "react-router-dom";
import Contact from '../../../Contact';
import Footer from '../../../Footer';
import Home from '../../../Home';
import { IImageEntity } from '../../../model';
import Profile from '../../../Profile';
import Header from './Header';

interface IProps {
  images: IImageEntity[];
  fetchImages(): void;
}

export class AppRootPage extends React.Component<IProps, {}> {
  public componentWillMount() {
    this.props.fetchImages();
  }

  public render() {
    const headerImage = _.find(this.props.images, (image: IImageEntity) => image.tag === 'top');
    return (
      <HashRouter>
      <div className="container container-fluid">
        <Header image={headerImage} />
        <Switch>
          <Route exact={true} path="/home" component={Home} />
          <Route exact={true} path="/contact" component={Contact} />
          <Route exact={true} path="/profile" component={Profile} />
          <Redirect to='/home'/>
        </Switch>
        <Footer />
      </div>
    </HashRouter>
    );
  }
}
