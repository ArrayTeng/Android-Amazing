import 'package:flutter/material.dart';

class MyHomePage extends StatelessWidget {

  final String title;

  MyHomePage({Key key, @required this.title}) :super(key: key);

  @override
  Widget build(BuildContext context) {

    return Scaffold(
      appBar: AppBar(title: Text(title),),
      body: Center(
        child: Container(
          
        ),
      ),
    );
  }

}