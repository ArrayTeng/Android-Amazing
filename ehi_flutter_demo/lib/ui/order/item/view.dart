import 'package:ehi_flutter_demo/widget/horizontal_line.dart';
import 'package:fish_redux/fish_redux.dart';
import 'package:flutter/material.dart';

import 'state.dart';

Widget buildView(
    OrderItemState state, Dispatch dispatch, ViewService viewService) {
  return Container(
    margin: EdgeInsets.all(10.0),
    decoration: BoxDecoration(
        color: Color(0xFFFFFFFF),
        borderRadius: BorderRadius.all(Radius.circular(4.0))),
    child: buildItem(),
  );
}

Widget buildItem() {
  return Padding(
    padding: EdgeInsets.only(left: 14, top: 10, right: 14),
    child: Column(
      crossAxisAlignment: CrossAxisAlignment.start,
      children: <Widget>[
        Row(
          children: <Widget>[
            Text(
              '订单号 1677933932',
              style: TextStyle(fontSize: 12, color: Color(0xFFAAAAAA)),
            ),
            Padding(
              padding: EdgeInsets.only(left: 12),
              child: Image.asset('images/icon_sesame.png',
                  width: 11.5, height: 13.5),
            ),
            Padding(
              padding: EdgeInsets.only(left: 12),
              child: Image.asset('images/icon_accident.png',
                  width: 15, height: 14),
            ),
            Expanded(
                child: Row(
              mainAxisAlignment: MainAxisAlignment.end,
              children: <Widget>[
                Text(
                  '预约中（已分配）',
                  style: TextStyle(fontSize: 13, color: Color(0xFF333333)),
                )
              ],
            )),
          ],
        ),
        Padding(
          padding: EdgeInsets.only(top: 15, bottom: 10),
          child: HorizontalLine(
            dashedHeight: 0.5,
            color: Color(0xFFEEEEEE),
          ),
        ),
        Row(
          crossAxisAlignment: CrossAxisAlignment.end,
          children: <Widget>[
            Text(
              '王海兵',
              style: TextStyle(fontSize: 16, color: Color(0xFF333333)),
            ),
            Padding(
              padding: EdgeInsets.only(left: 10),
              child: Text('13669018573',
                  style: TextStyle(fontSize: 12, color: Color(0xFF333333))),
            ),
            Expanded(
                child: Row(
              mainAxisAlignment: MainAxisAlignment.end,
              children: <Widget>[
                Image.asset(
                  'images/order_list_user_phone_ic.png',
                  width: 14.5,
                  height: 14.5,
                ),

                ///竖直分割线
                Padding(
                  padding: EdgeInsets.only(left: 10, right: 10),
                  child: SizedBox(
                    width: 0.5,
                    height: 16,
                    child: DecoratedBox(
                      decoration: BoxDecoration(color: Color(0xFF0299FF)),
                    ),
                  ),
                ),
                Text(
                  '备注',
                  style: TextStyle(fontSize: 14, color: Color(0xFF0299FF)),
                ),
              ],
            )),
          ],
        ),
        Padding(
          padding: EdgeInsets.only(top: 10),
          child: Text(
            '上汽大通G10（7座商务车)',
            style: TextStyle(fontSize: 12),
          ),
        ),
        Padding(
            padding: EdgeInsets.only(top: 10),
            child: Text(
              '02月22日  12:00 - 02月26日  12:00',
              style: TextStyle(fontSize: 12, color: Color(0xFF999999)),
            )),
        Padding(
            padding: EdgeInsets.only(top: 10),
            child: Text(
              '虹桥机场T1送车点',
              style: TextStyle(fontSize: 12, color: Color(0xFF999999)),
            )),
        Row(
          children: <Widget>[
            buildBottomText('普卡', Color(0xFFFFF6E7), Color(0xFFFF9509)),
            buildBottomText('账户冻结: 1级', Color(0xFFFFF6E7), Color(0xFFFF9509)),
            buildBottomShapeText('支付宝双免', Color(0xFFF64C36), Color(0xFFFF9B8C)),
            buildBottomShapeText('绑卡免押', Color(0xFFF64C36), Color(0xFFFF9B8C)),
            buildBottomShapeText('预付', Color(0xFF459DED), Color(0xFF459DED)),
            buildBottomShapeText('携程金卡', Color(0xFFBE7A34), Color(0xFFBE7A34)),
//            Expanded(child: buildExpansionTile(),)
          ],
        )
      ],
    ),
  );
}

Widget buildBottomText(String text, Color backgroundColor, Color textColor) {
  return Container(
      height: 16,
      margin: EdgeInsets.all(2),
      padding: EdgeInsets.only(left: 2, right: 2),
      decoration: BoxDecoration(
          color: backgroundColor,
          borderRadius: BorderRadius.all(Radius.circular(2.0))),
      child: Center(
        child: Text(
          text,
          style: TextStyle(color: textColor, fontSize: 10),
        ),
      ));
}

Widget buildBottomShapeText(String text, Color textColor, Color borderColor) {
  return Container(
      height: 16,
      margin: EdgeInsets.all(2),
      padding: EdgeInsets.only(left: 2, right: 2),
      decoration: BoxDecoration(
          borderRadius: BorderRadius.all(Radius.circular(2.0)),
          border: Border.all(
              width: 0.5, color: borderColor, style: BorderStyle.solid)),
      child: Center(
        child: Text(
          text,
          style: TextStyle(color: textColor, fontSize: 10),
        ),
      ));
}

//Widget buildExpansionTile() {
//  return ExpansionTile(
//    title: Text('nnn'),
//    leading: Image.asset(
//      'images/order_detail_fold_arrow_down.png',
//
//    ),
//    children: <Widget>[
//      ListTile(
//        title: Text('List title'),
//        subtitle: Text('Subtitle'),
//      )
//    ],
//  );
//}
