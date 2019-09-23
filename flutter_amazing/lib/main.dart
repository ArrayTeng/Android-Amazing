import 'package:flutter/material.dart';
import 'package:flutter_amazing/image_page.dart';
import 'package:flutter_amazing/widget_lifecycle_page.dart';


import 'launch_app.dart';

void main() => runApp(MyApp());

class MyApp extends StatefulWidget  {

  @override
  State<StatefulWidget> createState() => MyAppState();
}

class MyAppState extends State<MyApp>{

 Brightness _brightness = Brightness.light;

  @override
  Widget build(BuildContext context) {
    return MaterialApp(

      title: 'Flutter Demo',
      theme: ThemeData(
        //fontFamily: 'Schyler',
        primarySwatch: Colors.blue,
       brightness: _brightness
      ),
      home: MyImagePage()
    );
  }

  changeTheme(bool isNight) {
    setState(() {
      isNight?_brightness = Brightness.dark:_brightness=Brightness.light;
    });
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
              _clickItemEvent("Flutter生命周期", WidgetLifecyclePage()),
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
