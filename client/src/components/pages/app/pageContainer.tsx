import { connect } from 'react-redux';
import { IState } from '../../../reducers';
import { fetchImagesAction } from './actions/fetchImages';
import { AppRootPage } from './page';

const mapStateToProps = (state: IState) => ({
  images: state.images,
});

const mapDispatchToProps = (dispatch: any) => ({
  fetchImages: () => dispatch(fetchImagesAction()),
});

export const AppRootPageContainer = connect(
  mapStateToProps,
  mapDispatchToProps,
)(AppRootPage);