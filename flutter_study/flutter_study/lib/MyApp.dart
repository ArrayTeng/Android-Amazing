import 'package:flutter/material.dart';

import 'MyHomePage.dart';

class MyApp extends StatelessWidget{
  @override
  Widget build(BuildContext context) {
    final String appName = '自定义主题';
    return MaterialApp(
      title: appName,

      theme: ThemeData(
        brightness: Brightness.light,
        primaryColor: Colors.lightGreen,
        accentColor: Colors.orange
      ),

      home: MyHomePage(title:appName),
    );
  }

}