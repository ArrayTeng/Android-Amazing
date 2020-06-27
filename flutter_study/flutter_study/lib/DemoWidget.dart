import 'package:flutter/material.dart';
import 'package:flutterstudy/widget.dart';

class DemoWidget extends StatelessWidget {
  final String text;

  DemoWidget(this.text);

  @override
  Widget build(BuildContext context) {
    return Container(
      child: Card(
        child: FlatButton(
            onPressed: () {
              print("click");
            },
            child: Padding(
              padding: EdgeInsets.all(10.0),
              child: Column(
                mainAxisSize: MainAxisSize.min,
                children: <Widget>[
                  Container(
                    child: Text("这是一点描述",
                        style: TextStyle(color: Colors.grey, fontSize: 14.0),
                        maxLines: 3,
                        overflow: TextOverflow.ellipsis),
                    margin: EdgeInsets.all(2.0),
                  ),
                  Row(
                    crossAxisAlignment: CrossAxisAlignment.start,
                    children: <Widget>[
                      getBottomItem(Icons.star,"1000"),
                      getBottomItem(Icons.ac_unit,"2000"),
                      getBottomItem(Icons.access_time,"3000")
                    ],
                  )
                ],
              ),
            )),
      ),
    );
  }
}
