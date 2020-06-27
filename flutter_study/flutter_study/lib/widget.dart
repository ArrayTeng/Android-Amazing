
import 'package:flutter/material.dart';

getBottomItem(IconData icon,String text){
  return new Expanded(
    flex: 1,
    child: new Center(
      child: Row(
        mainAxisAlignment: MainAxisAlignment.center,//主轴居中，对于Row而言它的主轴是横向副轴是竖向
        crossAxisAlignment: CrossAxisAlignment.center,//副轴居中
        mainAxisSize: MainAxisSize.max,
        children: <Widget>[
          Icon(icon,size: 16,color: Colors.grey,),
          Padding(padding: EdgeInsets.only(left: 5.0)),
          Text(text,style: TextStyle(color: Colors.grey, fontSize: 14.0),
          overflow: TextOverflow.ellipsis,
          maxLines: 1,)
        ],
      ),
    ),
  );
}