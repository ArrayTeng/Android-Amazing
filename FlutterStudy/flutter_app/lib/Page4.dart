
import 'package:flutter/material.dart';

class Page4 extends StatelessWidget{
  @override
  Widget build(BuildContext context) {

    return Scaffold(
      appBar: AppBar(title: Text('flutter 单容器布局'),),
      body: Container(
        child: Row(mainAxisAlignment:MainAxisAlignment.center,crossAxisAlignment: CrossAxisAlignment.start,children: <Widget>[
          Container(color: Colors.yellow,width:70,height: 100,),
          Container(color: Colors.blue,width: 100,height: 100),
          Container(color: Colors.red,width: 70,height: 130,),
          Container(color: Colors.black,width:70,height: 100,)
        ],),
      ),
    );
  }
}