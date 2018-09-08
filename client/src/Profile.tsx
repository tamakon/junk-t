import * as React from 'react';

class Profile extends React.Component {
  public render() {
    return (
      <div className="App">
        <header className="App-header" />
          <h1>PROFILE</h1>
          <div>
            <p>メンバー</p>
            <blockquote><p>Vo.              <a href="http://junk-tion.jp/wp-content/uploads/2016/05/shizumemasahiro.pdf" target="_blank">鎮目政宏（シズメマサヒロ）</a></p></blockquote>
            <blockquote><p>Gt.              <a href="http://junk-tion.jp/wp-content/uploads/2016/05/uekihisashi.pdf" target="_blank">植木栄（ウエキヒサシ）</a></p></blockquote>
            <blockquote><p>Dr.              西基容貴（ニシキヨタカ）</p></blockquote>
            <blockquote><p>Ba.              永松英樹（ナガマツヒデキ）</p></blockquote>
            <blockquote><p>Key.             田中治美（タナカキヨミ）</p></blockquote>
            <blockquote><p>Sax.             福島哲平（フクシマテッペイ）</p></blockquote>
            <p>マネージャー</p>
            <blockquote><p> ACT !!      <a href="http://junk-tion.jp/wp-content/uploads/2016/05/takahashikenji_ACT.pdf" target="_blank">高橋健司（タカハシケンジ）</a></p></blockquote>
          </div>
      </div>
    );
  }
}

export default Profile;
