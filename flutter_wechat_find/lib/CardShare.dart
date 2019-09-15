import 'package:flutter/widgets.dart';
import 'package:flutter_wechat_find/BaseCard.dart';

class CardShare extends BaseCard {
  @override
  BaseCardState createState() => _CardShareState();
}

class _CardShareState extends BaseCardState {
  @override
  topTitle(String data) {
    return super.topTitle("分享得联名卡");
  }

  @override
  topTitle2() {
    return Padding(
      padding: EdgeInsets.only(left: 10, bottom: 4),
      child: Text("/第19期", style: TextStyle(fontSize: 10)),
    );
  }

  @override
  Widget subTitle(String data) {
    return super.subTitle("分享给朋友圈最多可获得12天无限卡");
  }

  @override
  bottomContent() {
    return Expanded(
        child: Container(
      margin: EdgeInsets.only(top: 10),
      decoration: BoxDecoration(color: Color(0xfff6f7f9)),
      child: Column(
        children: <Widget>[
          Expanded(child: Padding(padding: EdgeInsets.only(top: 20,left: 15,bottom: 20),
          child: Image.network("http://www.devio.org/io/flutter_beauty/card_list.png"),)),

          Padding(padding: EdgeInsets.only(bottom: 20),child: bottomTitle('1264736人 · 参与活动') ,)

        ],
      ),
    ));
  }
}
