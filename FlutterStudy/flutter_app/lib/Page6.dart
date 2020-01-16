
import 'package:flutter/material.dart';

class Page6 extends StatefulWidget {
  @override
  State<StatefulWidget> createState() {
    return CustomState();
  }
}

class MyState extends State {
  int count = 0;

  void _incrementCounter() =>
      setState(() {
        count++;
      });

  @override
  Widget build(BuildContext context) {
    return CountContainer(
      myState: this,
      incrementCounter: _incrementCounter,
      child: Counter(),
    );
  }
}

class Counter extends StatelessWidget {
  @override
  Widget build(BuildContext context) {
    CountContainer container = CountContainer.of(context);

    return Scaffold(
      appBar: AppBar(
        title: Text('Widget数据传递'),
      ),
      body: Container(
        child: Text(
            'You have pushed the button this many times:${container.myState
                .count}'),
      ),
      floatingActionButton: FloatingActionButton(
        onPressed: container.incrementCounter,
        child: Text('click'),
      ),
    );
  }
}

class CountContainer extends InheritedWidget {


  MyState myState;
  Function incrementCounter;

  static CountContainer of(BuildContext context) =>
      context.inheritFromWidgetOfExactType(CountContainer);

  CountContainer({Key key,
    @required Widget child,
    @required this.incrementCounter,
    @required this.myState})
      : super(
    key: key,
    child: child,
  );

  @override
  bool updateShouldNotify(CountContainer oldWidget) {
    return myState != oldWidget.myState;
  }


}

class CustomState extends State {

  String _msg = '通知';
  @override
  Widget build(BuildContext context) {


    return Scaffold(
        appBar: AppBar(title: Text('子控件向父控件传递消息'),),
        // ignore: missing_return
        body: NotificationListener<CustomNotification>(
            // ignore: missing_return
            onNotification: (notification) {
              setState(() {
                // ignore: missing_return
                _msg += notification.message + ',';
                print('收到子Widget通知，更新msg'+_msg);

              });
            }, child: Column(
          children: <Widget>[
            Text(_msg),
            CustomChild()
          ],
        ))
    );
  }

}


class CustomChild extends StatelessWidget {
  @override
  Widget build(BuildContext context) {
    return Container(
      child: FlatButton(onPressed: ()=>{
        print('flatButton'),
        CustomNotification('Hi').dispatch(context)}, child: Text('click me')),
    );
  }

}


class CustomNotification extends Notification {

  final String message;

  CustomNotification(this.message);

}