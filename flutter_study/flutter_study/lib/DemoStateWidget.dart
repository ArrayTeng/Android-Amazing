import 'package:flutter/material.dart';

class DemoStateWidget extends StatefulWidget{
  @override
  _DemoStateWidgetState createState() => _DemoStateWidgetState("1");
}


class _DemoStateWidgetState extends State<DemoStateWidget>{

  String text;

  _DemoStateWidgetState(this.text);

  @override
  void initState() {
    super.initState();

    new Future.delayed(const Duration(seconds: 1),(){
      setState(() {
        text = "2";
      });
    });
  }

  @override
  void didChangeDependencies() {
    super.didChangeDependencies();
  }

  @override
  Widget build(BuildContext context) {

    return Container(child: Text(text ?? "这是有状态的Demo"),);
  }

  @override
  void dispose() {
    super.dispose();
  }
}