import 'dart:async';
import 'dart:ui';

import 'package:flutter/material.dart';
import 'package:flutter/services.dart';

void main() => runApp(MyApp(
      initParams: window.defaultRouteName,
    ));

class MyApp extends StatelessWidget {
  final String initParams;

  const MyApp({Key key, this.initParams}) : super(key: key);

  @override
  Widget build(BuildContext context) {
    return MaterialApp(
      title: 'flutter 混合开发',
      theme: ThemeData(
        primarySwatch: Colors.blue,
      ),
      home: MyHomePage(title:initParams),
    );
  }
}

class MyHomePage extends StatefulWidget {
  MyHomePage({Key key, this.title}) : super(key: key);

  final String title;

  @override
  _MyHomePageState createState() => _MyHomePageState();
}

class _MyHomePageState extends State<MyHomePage> {
  static const EventChannel _eventChannel = EventChannel("EventChannelPlugin");

  static const MethodChannel _methodChannel =
      const MethodChannel("EventChannelPlugin");

  static const BasicMessageChannel<String> _basicMessageChannel =
      const BasicMessageChannel("BasicMessageChannelPlugin", StringCodec());

  String showMessage = "";

  StreamSubscription _streamSubscription;

  bool _isMethodChannelPlugin = false;

  @override
  void initState() {
    _streamSubscription = _eventChannel
        .receiveBroadcastStream('123')
        .listen(_onToDart, onError: _onToDartError);
    // 使用 BasicMessageChannel 接收 Native的消息并向Native回复
    c
        .setMessageHandler((String message) => Future<String>(() {
              setState(() {
                showMessage = "BasicMessageChannel" + message;
              });

              ///给Native回复消息
              return "收到Native的消息" + message;
            }));
    super.initState();
  }

  @override
  void dispose() {
    _streamSubscription.cancel();
    super.dispose();
  }

  void _onToDart(message) {
    setState(() {
      this.showMessage = message;
    });
  }

  void _onToDartError(error) {
    print(error);
  }

  @override
  Widget build(BuildContext context) {
    return Scaffold(
      appBar: AppBar(
        title: Text(widget.title),
      ),
      body: Container(
        alignment: Alignment.topCenter,
        decoration: BoxDecoration(color: Colors.lightBlueAccent),
        margin: EdgeInsets.only(top: 70.0),
        child: Column(
          children: <Widget>[
            ///用于切换使用哪一种方式通信
            SwitchListTile(
              value: _isMethodChannelPlugin,
              onChanged: _onChannelChange,
              title: Text(_isMethodChannelPlugin
                  ? "MethodChannel"
                  : "BasicMessageChannel"),
            ),
            TextField(
              ///当数据发生改变的时候向Native发送数据
              onChanged: _onTextChanged,
            ),
            Text("收到初始化参数"+window.defaultRouteName),
            Text("Native传递过来的消息  " + showMessage)
          ],
        ),
      ),
    );
  }

  void _onChannelChange(bool value) {
    setState(() {
      _isMethodChannelPlugin = value;
    });
  }

  void _onTextChanged(String value) async {
    String response;
    try {
      if (_isMethodChannelPlugin) {
        response = await _methodChannel.invokeMethod("send", value);
      } else {
        response = await _basicMessageChannel.send(value);
      }
    } on PlatformException catch (e) {
      print(e);
    }
  }
}
