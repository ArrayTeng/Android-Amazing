import 'dart:core';

import 'package:flutter/material.dart';

void main() => runApp(TextWidget());

class TextWidget extends StatelessWidget {
  @override
  Widget build(BuildContext context) {
    return MaterialApp(
      title: '文本控件',
      theme: ThemeData(primarySwatch: Colors.blue),
      home: TestApp(),
    );
  }
}

class TestApp extends StatefulWidget {
  @override
  State<StatefulWidget> createState() => _TestAppState();
}

class _TestAppState extends State {
  String btnInfo = '';

  TextStyle blackStyle =
      TextStyle(color: Colors.black, fontWeight: FontWeight.bold, fontSize: 20);

  TextStyle redStyle =
      TextStyle(color: Colors.red, fontWeight: FontWeight.normal, fontSize: 15);

  @override
  Widget build(BuildContext context) {
    return Scaffold(
        appBar: AppBar(title: Text('flutter 文本控件')),
        body: Container(
          child: ListView.separated(
            itemCount: 100,
            separatorBuilder: (BuildContext context, int index)=>index%2==0?Divider(color: Colors.red):Divider(color: Colors.blue),
            itemBuilder: (BuildContext context, int index) => ListTile(title: Text('title $index'),subtitle: Text('$index'),),
          ),
        ));
  }
}






