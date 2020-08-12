import 'dart:ui';

import 'package:fish_redux/fish_redux.dart';
import 'package:flutter/material.dart';

import 'action.dart';
import 'state.dart';

Widget buildView(HomeState state, Dispatch dispatch, ViewService viewService) {
  return Scaffold(
      backgroundColor: Color(0xFF0299FF),
      appBar: PreferredSize(
        preferredSize:
            Size.fromHeight(MediaQueryData.fromWindow(window).padding.top),
        child: SafeArea(
          top: true,
          child: Offstage(),
        ),
      ),
      body: Container(
          child: Column(
        children: <Widget>[
          Padding(
            padding: EdgeInsets.only(left: 30),
            child: Row(
              children: <Widget>[
                Expanded(child: buildHomeHeaderWidget('营业额', '28763')),
                Expanded(child: buildHomeHeaderWidget('增值服务', '30')),
                Expanded(child: buildHomeHeaderWidget('增值服务(员工)', '375')),
              ],
            ),
          ),
          Padding(
            padding: EdgeInsets.only(top: 24, left: 30),
            child: Row(
              children: <Widget>[
                Expanded(child: buildHomeHeaderWidget('车辆总数', '27')),
                Expanded(child: buildHomeHeaderWidget('可用车辆数', '400')),
                Expanded(child: buildHomeHeaderWidget('出租率', '90%')),
              ],
            ),
          ),
          Row(
            children: <Widget>[
              Padding(
                padding: EdgeInsets.only(top: 24, left: 30),
                child: Expanded(child: buildHomeHeaderWidget('订单数', '93')),
              )
            ],
          ),
          Expanded(
              child: Container(
                width: MediaQuery.of(viewService.context).size.width,
            decoration: BoxDecoration(
                color: Colors.white,
                borderRadius: BorderRadius.only(
                    topLeft: Radius.circular(15.0),
                    topRight: Radius.circular(15.0))),
            margin: EdgeInsets.only(top: 24),
            child: Column(
              crossAxisAlignment: CrossAxisAlignment.start,
              children: <Widget>[
                Padding(padding: EdgeInsets.only(left: 30,top: 20,bottom: 12.5),child: Text('订单代办',style: TextStyle(fontSize: 15,color: Color(0xFF333333),fontWeight: FontWeight.bold),),),
                Row(
                  children: <Widget>[
                    Expanded(child: buildOrderAgentWidget('2','待取车','images/main_dash_car.png')),
                    Expanded(child: buildOrderAgentWidget('5','待还车','images/main_dash_car.png')),
                    Expanded(child: buildOrderAgentWidget('5','分配车牌','images/main_dash_no_carno_order.png'))
                  ],
                ),
                Row(
                  children: <Widget>[
                    Expanded(child: buildOrderAgentWidget('3','预授权转消费','images/main_dash_preauth_order.png')),
                    Expanded(child: buildOrderAgentWidget('2','嗨车体检','images/main_dash_preauth_order.png')),
                    Expanded(child: Container()),
                  ],
                ),

                Padding(padding: EdgeInsets.only(left: 30,top: 20,bottom: 12.5),child: Text('订单操作',style: TextStyle(fontSize: 15,color: Color(0xFF333333),fontWeight: FontWeight.bold),),),

                Row(
                  children: <Widget>[
                    Expanded(child: buildOrderAgentWidget('2','待取车','images/main_dash_car.png')),
                    Expanded(child: buildOrderAgentWidget('5','待还车','images/main_dash_car.png')),
                    Expanded(child: buildOrderAgentWidget('5','分配车牌','images/main_dash_no_carno_order.png'))
                  ],
                ),
                Row(
                  children: <Widget>[
                    Expanded(child: buildOrderAgentWidget('3','预授权转消费','images/main_dash_preauth_order.png')),
                    Expanded(child: buildOrderAgentWidget('2','嗨车体检','images/main_dash_preauth_order.png')),
                    Expanded(child: Container()),
                  ],
                ),
              ],
            ),
          )),
        ],
      )));
}

///构建首页头部服务数据的控件
Widget buildHomeHeaderWidget(String title, String data) {
  return Container(
    child: Column(
      crossAxisAlignment: CrossAxisAlignment.start,
      children: <Widget>[
        Text(
          title,
          style: TextStyle(fontSize: 12.0, color: Color(0xFFFFFFFF)),
        ),
        Container(
          margin: EdgeInsets.only(top: 4),
          child: Text(data,
              style: TextStyle(fontSize: 21.0, color: Color(0xFFFFFFFF))),
        )
      ],
    ),
  );
}

///构建订单代办
Widget buildOrderAgentWidget(String text, String status,String imagePath) {
  return Card(
      elevation: 15.0,
      shape: const RoundedRectangleBorder(
          borderRadius: BorderRadius.all(Radius.circular(14))),
      child: Container(
        child: Column(
          crossAxisAlignment: CrossAxisAlignment.start,
          children: <Widget>[
            Row(
              children: <Widget>[
                Padding(padding: EdgeInsets.only(left: 16.5,top: 19.5),child: Text(text,style: TextStyle(fontSize: 20,color: Color(0xFF333333),fontWeight: FontWeight.bold),),),
                Padding(padding: EdgeInsets.only(left: 53,top: 19.5),child: ImageIcon(AssetImage(imagePath)),)
              ],
            ),
            Padding(padding: EdgeInsets.only(left: 16.5,top: 10,bottom: 16.5),child: Text(status,style: TextStyle(fontSize: 12,color: Color(0xFF666666)),),)
          ],
        ),
        ),
      );
}
