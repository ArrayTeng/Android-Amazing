import 'package:flutter/material.dart';

import 'launch_app.dart';

void main() => runApp(MyApp());

class MyApp extends StatelessWidget {
  @override
  Widget build(BuildContext context) {
    return MaterialApp(
      title: 'Flutter Demo',
      theme: ThemeData(
        primarySwatch: Colors.blue,
      ),
      home: MyHomePage(),
    );
  }
}

class MyHomePage extends StatefulWidget {
  @override
  _MyHomePageState createState() => _MyHomePageState();
}

class _MyHomePageState extends State<MyHomePage> {
  @override
  Widget build(BuildContext context) {
    return Scaffold(
        appBar: AppBar(
          title: Text("flutter学习手册"),
        ),
        body: Container(
          alignment: Alignment.center,
          child: Column(
            crossAxisAlignment: CrossAxisAlignment.start,
            children: <Widget>[
              _clickItemEvent("打开第三方应用", LaunchAppPage()),
              _clickItemEvent("修改主题", LaunchAppPage())
            ],
          ),
        ));
  }

  Widget _clickItemEvent(String title, Widget page) {
    return GestureDetector(
      onTap: () {
        Navigator.push(context, MaterialPageRoute(builder: (context) => page));
      },
      child: Container(
        padding: EdgeInsets.all(10),
        child: Text(
          title,
          style: TextStyle(color: Colors.blue, fontSize: 14),
        ),
      ),
    );
  }
}
