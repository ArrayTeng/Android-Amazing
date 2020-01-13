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

  TextStyle blackStyle = TextStyle(color: Colors.black,fontWeight: FontWeight.bold,fontSize: 20);

  TextStyle redStyle = TextStyle(color: Colors.red,fontWeight: FontWeight.normal,fontSize: 15);

  @override
  Widget build(BuildContext context) {
    return Scaffold(
      appBar: AppBar(title: Text('flutter 文本控件')),
      body: Container(
        child: Text.rich(TextSpan(children: <TextSpan>[
          TextSpan(text:'文本是视图系统中常见的控件，它用来显示一段特定样式的字符串，类似'),
          TextSpan(text: 'Android',style: redStyle),
          TextSpan(text: '中的',style: blackStyle)
        ]),textAlign: TextAlign.center,),
      ),
    );
  }
}
