import 'package:flutter/material.dart';

import 'TabNavigator.dart';

void main() => runApp(WeChatFind());

class WeChatFind extends StatelessWidget {
  @override
  Widget build(BuildContext context) {
    return MaterialApp(
        title: '微信读书发现界面',
        theme: ThemeData(
          primarySwatch: Colors.blue,
        ),
        home: TabNavigator()
    );
  }
}
