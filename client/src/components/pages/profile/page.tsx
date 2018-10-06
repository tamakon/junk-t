import * as React from 'react';
import { ListGroup, ListGroupItem } from 'react-bootstrap';

export class ProfilePage extends React.Component {
  public render() {
    return (
      <div className="App">
        <h1>PROFILE</h1>
        <div className="text-left">
          <h3>メンバー</h3>
          <ListGroup>
            <ListGroupItem><p>Vo.              <a href="http://junk-tion.jp/wp-content/uploads/2016/05/shizumemasahiro.pdf" target="_blank">鎮目政宏（シズメマサヒロ）</a></p></ListGroupItem>
            <ListGroupItem><p>Gt.              <a href="http://junk-tion.jp/wp-content/uploads/2016/05/uekihisashi.pdf" target="_blank">植木栄（ウエキヒサシ）</a></p></ListGroupItem>
            <ListGroupItem><p>Dr.              西基容貴（ニシキヨタカ）</p></ListGroupItem>
            <ListGroupItem><p>Ba.              永松英樹（ナガマツヒデキ）</p></ListGroupItem>
            <ListGroupItem><p>Key.             田中治美（タナカキヨミ）</p></ListGroupItem>
            <ListGroupItem><p>Sax.             福島哲平（フクシマテッペイ）</p></ListGroupItem>
          </ListGroup>
          <h3>マネージャー</h3>
          <ListGroup>
            <ListGroupItem><p> ACT !!      <a href="http://junk-tion.jp/wp-content/uploads/2016/05/takahashikenji_ACT.pdf" target="_blank">高橋健司（タカハシケンジ）</a></p></ListGroupItem>
          </ListGroup>
        </div>
      </div>
    );
  }
}
