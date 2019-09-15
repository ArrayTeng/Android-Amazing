import 'package:flutter/cupertino.dart';
import 'package:flutter/material.dart';

class BaseCard extends StatefulWidget {
  @override
  BaseCardState createState() => BaseCardState();
}

class BaseCardState extends State<BaseCard> {
  Color subTitleColor = Colors.grey;
  @override
  Widget build(BuildContext context) {
    return PhysicalModel(
      color: Colors.transparent,
      borderRadius: BorderRadius.circular(5),
      clipBehavior: Clip.antiAlias,
      child: Container(
        decoration: BoxDecoration(color: Colors.white),
        child: Column(
          children: <Widget>[topContent(), bottomContent()],
        ),
      ),
    );
  }

  topContent() {
    return Padding(
        padding: EdgeInsets.only(left: 20, top: 25, bottom: 20),
        child: Column(
          crossAxisAlignment: CrossAxisAlignment.start,
          children: <Widget>[
            Row(
              children: <Widget>[
                topTitle('')
              ],
            ),
            subTitle('')
          ],
        ));
  }

  bottomContent() {
    return Container();
  }

  Widget subTitle(String data) {
    return Padding(
      padding: EdgeInsets.only(top: 5),
      child: Text(
        data,
        style: TextStyle(fontSize: 11, color: subTitleColor),
      ),
    );
  }

  topTitle(String data) {
    return Text(data,style: TextStyle(fontSize: 22));
  }
}
