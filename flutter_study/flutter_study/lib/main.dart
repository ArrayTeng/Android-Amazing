import 'package:flutter/material.dart';
import 'package:flutterstudy/DemoWidget.dart';
import 'package:flutterstudy/WeatherPage.dart';

void main() => runApp(DemoApp());

class DemoApp extends StatelessWidget {
  @override
  Widget build(BuildContext context) {
    return MaterialApp(
      home: WeatherPage(),
    );
  }
}

class DemoPage extends StatefulWidget {
  @override
  _DemoPageState createState() => _DemoPageState();
}

class _DemoPageState extends State<DemoPage> {
  @override
  Widget build(BuildContext context) {
    return Scaffold(
      backgroundColor: Colors.blue,
      appBar: AppBar(
        title: Text("Title"),
      ),
      body: ListView.builder(
        itemBuilder: (context, index) {
          return DemoWidget();
        },
        itemCount: 20,
      ),
    );
  }
}
