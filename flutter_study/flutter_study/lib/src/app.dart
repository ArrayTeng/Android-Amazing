
import 'package:flutter/material.dart';

class APP extends StatelessWidget{
  @override
  Widget build(BuildContext context) {

    return MaterialApp(
      theme: ThemeData.dark(),
      home: Scaffold(
        appBar: AppBar(title: Text("BloC"),),
        body: MovieList(),
      ),
    );
  }

  MovieList() {

  }
}