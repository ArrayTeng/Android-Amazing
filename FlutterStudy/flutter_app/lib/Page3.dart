import 'package:flutter/material.dart';

class Page3 extends StatefulWidget {
  @override
  State<StatefulWidget> createState() => MyAppSate();
}

class Page3State extends State {
  @override
  Widget build(BuildContext context) {
    return Scaffold(
      appBar: AppBar(title: Text('嵌套滑动')),
      body: Container(
        child: CustomScrollView(
          slivers: <Widget>[
            SliverAppBar(
              title: Text('Title'),
              floating: true,
              flexibleSpace:
                  Image.network('images/imagetest.jpeg', fit: BoxFit.fitHeight),
              expandedHeight: 300,
            ),
            SliverList(
              delegate: SliverChildBuilderDelegate(
                  (BuildContext context, int index) => ListTile(
                        title: Text('title $index'),
                      ),
                  childCount: 100),
            )
          ],
        ),
      ),
    );
  }
}

class MyAppSate extends State {
  bool isTop = false;

  ScrollController _controller;


  @override
  // ignore: must_call_super
  void initState() {
    _controller = ScrollController();
    _controller.addListener((){
      if (_controller.offset > 1000) {
          setState(() {
            isTop = true;
            print(' isTop = true;');
          });
        }
      else if (_controller.offset < 300) {
          setState(() {
            isTop = false;
            print(' isTop = false;');

          });
        }
    });
    super.initState();
  }

  @override
  Widget build(BuildContext context) {
    return Scaffold(
        appBar: AppBar(title: Text('嵌套滑动')),
        body: Column(
          children: <Widget>[
            Container(
                child: RaisedButton(
              onPressed: (isTop?(){_controller.animateTo(.0, duration: Duration(microseconds: 200), curve: Curves.ease);}:null),
              child: Text('click'),
            )),
            Expanded(
                child: ListView.builder(
                  controller: _controller,
                    itemBuilder: (context, index) => ListTile(title:Text('$index'))))
          ],
        ));
  }

  @override
  void dispose() {
    _controller.dispose();
    super.dispose();

  }
}
