import 'package:flutter/cupertino.dart';
import 'package:flutter_wechat_find/BaseCard.dart';

class RecommendCard extends BaseCard {
  @override
  BaseCardState createState() {
    return RecommendState();
  }
}

class RecommendState extends BaseCardState {
  @override
  void initState() {
    subTitleColor = Color(0xffb99444);
    super.initState();
  }

  @override
  topTitle(String data) {
    return super.topTitle("本周推荐");
  }

  @override
  Widget subTitle(String data) {
    return super.subTitle("送你一天无限卡，全场书籍免费读 >");
  }

  @override
  bottomContent() {
    // TODO: implement bottomContent
    return Expanded(child: Container(
      margin: EdgeInsets.only(top: 10),
      child: Image.network("http://www.devio.org/io/flutter_beauty/card_1.jpg",fit: BoxFit.cover),
    ));
  }
}
