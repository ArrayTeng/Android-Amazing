import 'package:flutter/material.dart';
import 'package:flutterstudy/dio/page/ListPage.dart';

import 'dio/page/ExpansionTilePage.dart';

void main() => runApp(DemoApp());

class DemoApp extends StatelessWidget {
  @override
  Widget build(BuildContext context) {
    return MaterialApp(
      title: 'Flutter学习',
      home: ExpansionTilePage()
    );
  }
}

