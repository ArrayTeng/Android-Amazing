import 'dart:math';

import 'package:flutter/material.dart';

class Page5 extends StatefulWidget {
  @override
  State<StatefulWidget> createState() {
    return MyState();
  }
}

class MyState extends State {
  @override
  Widget build(BuildContext context) {
    return Scaffold(
      appBar: AppBar(
        title: Text('下载更新'),
      ),
      body: Column(

        children: <Widget>[
          buildTopRow(),
          buildBottomText()
        ],
      ),
    );
  }
}

Widget buildTopRow() {
  return Row(
    children: <Widget>[
      Padding(
          padding: EdgeInsets.only(left: 10,top: 0,right: 10,bottom: 0),
          child: ClipRRect(
              borderRadius: BorderRadius.circular(8.0),
              child: Image.asset(
                'images/imagetest.jpeg',
                width: 80,
                height: 80,
              ))),
      Expanded(
          child: Column(
        mainAxisAlignment: MainAxisAlignment.center,
        crossAxisAlignment: CrossAxisAlignment.start,
        children: <Widget>[Text('Google Map'), Text('Google Map')],
      )),
      Padding(
        padding: EdgeInsets.fromLTRB(0, 0, 10, 0),
        child: FlatButton(onPressed: _pressed, child: Text('OPEN')),
      )
    ],
  );
}

Widget buildBottomText() {
  return Padding(
    padding: EdgeInsets.only(left: 10, top:0,right: 10,bottom: 0),
    child: Column(
      crossAxisAlignment: CrossAxisAlignment.start,
      children: <Widget>[
        Text(
            '与上半部分类似，这两个文本与父容器之间存在些间距，因此在 Column 的最外层还需要用 Padding 控件给包装起来，设置父容器间距'),
        Padding(padding: EdgeInsets.only(top: 10),child: Text('升级项 UI 的下半部分比较简单'),)
      ],
    ),
  );
}

void _pressed() {

}


class WheelPainter extends CustomPainter{

  @override
  void paint(Canvas canvas, Size size) {

    double wheelSize = min(size.width,size.height)/2;
    double nbElem = 6;//分成6份
    double radious = (2*pi)/nbElem;
  }

  @override
  bool shouldRepaint(CustomPainter oldDelegate) {
    // TODO: implement shouldRepaint
    return null;
  }


}
