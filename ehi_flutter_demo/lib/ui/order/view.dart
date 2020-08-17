import 'package:ehi_flutter_demo/ui/order/list/page.dart';
import 'package:ehi_flutter_demo/widget/tab_indicator.dart';
import 'package:fish_redux/fish_redux.dart';
import 'package:flutter/material.dart';

import 'action.dart';
import 'state.dart';

Widget buildView(OrderState state, Dispatch dispatch, ViewService viewService) {
  List<Tab> _tabs = [
    Tab(text: '待取车'),
    Tab(text: '待还车'),
    Tab(text: '当天待取车'),
    Tab(text: '当天待还车'),
  ];
  return DefaultTabController(
    length: _tabs.length,
    child: Scaffold(
      appBar: AppBar(
        bottom: TabBar(
          ///设置选中时的字体样式
          labelStyle: TextStyle(fontSize: 16,color: Color(0xFFFFFFFF)),
          unselectedLabelStyle: TextStyle(fontSize: 14,color: Color(0xFFFFFFFF)),
          isScrollable: true,
          tabs: _tabs,
          ///选中下滑线的颜色
          indicatorColor: Color(0xFFFFFFFF),
          indicatorWeight: 3,
          indicator: TabIndicator(),
        ),
      ),
      body: TabBarView(children: [
        ListPage().buildPage(null),
        Container(),
        Container(),
        Container(),
      ]),
    ),
  );
}
