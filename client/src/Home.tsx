import * as React from 'react';
import { Panel, Table } from 'react-bootstrap';
import './App.css';

class Home extends React.Component {
  public render() {
    return (
      <div className="App">
          <h1>JUNK-TION!!</h1>
          <Panel>
            <Panel.Body>
              2007年に小田和正、オフコースのトリビュートバンドとして活動を開始する。鎮目（しずめ）のハイトーンボイスが話題となり、各メディア、イベントに出演。2011年に入り、オリジナル曲の作成も開始、現在はトリビュートにメロディアスなオリジナル曲を取り混ぜたステージを提供している。
            </Panel.Body>
          </Panel>
          <div className="text-left">
            <h3>バンド経歴</h3>
            <Table>
              <tbody>
                <tr>
                  <td rowSpan={3}>２００７年</td>
                  <td>小田和正、オフコースのトリビュートバンドとして活動を開始</td>
                </tr>
                <tr>
                  <td>ＮＨＫ 熱血おやじバトル</td>
                </tr>
                <tr>
                  <td>フジテレビ ものまね紅白歌合戦　（シズメのみ）</td>
                </tr>
                <tr>
                  <td>２００８年</td>
                  <td>日本テレビ 口コミジョニー（シズメのみ）</td>
                </tr>
                <tr>
                  <td>２００９年</td>
                  <td>ＮＨＫＢＳ２ 全国バンド自慢</td>
                </tr>
                <tr>
                  <td>２０１０年</td>
                  <td>テレビ東京 日経大人のバンド大賞　（オリジナル曲）</td>
                </tr>
                <tr>
                  <td rowSpan={3}>２０１１年</td>
                  <td>ＴＢＳ おもろげ動画ショー　（シズメのみ）</td>
                </tr>
                <tr>
                  <td>フジテレビ クイズヘキサゴン　（シズメのみ）</td>
                </tr>
                <tr>
                  <td>日本テレビ スッキリ</td>
                </tr>
                <tr>
                  <td rowSpan={2}>２０１２年</td>
                  <td>テレビ朝日 ＭｕｓｉｃるＴＶ（オリジナル曲）</td>
                </tr>
                <tr>
                  <td>日本テレビ 世界一のショータイム</td>
                </tr>
                <tr>
                  <td>２０１３年</td>
                  <td>日本テレビ 世界一のショータイム</td>
                </tr>
                <tr>
                  <td />
                  <td>その他、都内、神奈川、千葉などのイベントに出演。</td>
                </tr>
              </tbody>
            </Table>
            <p className="text-left">２０１１年〜２０１２年、東京ＦＭ系列のミュージックバードのＣＦＭ向けラジオ番組のパーソナリティを務める。同年にラジオ日本の番組もワンクール担当。同年２枚のオリジナルマキシシングルを発売。</p>
          </div>
          <iframe
            src="https://www.facebook.com/plugins/page.php?href=https%3A%2F%2Fwww.facebook.com%2Fjunktion2007%2F&tabs=timeline&width=340&height=500&small_header=false&adapt_container_width=true&hide_cover=false&show_facepile=true&appId"
            width="340"
            height="500"
            style={{border:'none', overflow:'hidden', backgroundColor: 'transparent'}}
            scrolling="no"
            frameBorder="0" />
      </div>
    );
  }
}

export default Home;
