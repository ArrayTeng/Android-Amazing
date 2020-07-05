import 'package:flutter/material.dart';

class ListPage extends StatefulWidget {
  @override
  ListPageState createState() => ListPageState();
}

class ListPageState extends State<ListPage> {
  List<String> _list = List();

  @override
  void initState() {
    super.initState();
    _list.add("ExpansionTile实现可展开的列表");
  }

  @override
  Widget build(BuildContext context) {
    return _buildScaffold();
  }

  _buildScaffold() => Scaffold(
        appBar: AppBar(
          title: Text("Flutter学习"),
        ),
        body:Column(
          children: <Widget>[
            _buildButton(_list,0)
          ],
        )
      );

  Widget _buildButton(List<String> list,int index) => MaterialButton(
        height: 30,
        child: Center(
          child: Text(list[index]),
        ),
        onPressed: (){
          print("fasdfasfasdf");
        },
      );
}
