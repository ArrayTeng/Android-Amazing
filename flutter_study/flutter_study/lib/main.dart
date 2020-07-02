import 'package:flutter/material.dart';
import 'package:flutterstudy/key/BubbleDemoPage.dart';

void main() => runApp(DemoApp());

class DemoApp extends StatelessWidget {
  @override
  Widget build(BuildContext context) {
    return MaterialApp(
      title: 'Dio网络请求',
      home: Scaffold(
        appBar: AppBar(title: Text("Dio网络请求"),),
        body: BubbleDemoPage()
      ),
    );
  }
}

