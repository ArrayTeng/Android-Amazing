
import 'package:fish_redux/fish_redux.dart';
import 'package:flutter/material.dart';

import 'action.dart';
import 'state.dart';

Widget buildView(LoginState state, Dispatch dispatch, ViewService viewService) {
  List list = List();
  for(int i = 0;i<10;i++){
    list.add('${i}');
  }
  return Scaffold(
    appBar: AppBar(title: Text('AppBar'),),
    body: Container(
      child: Column(
        children: <Widget>[
          RaisedButton(onPressed: (){
            showModalBottomSheet(context: viewService.context, builder: (context){
              return Container(
                child: ListView.builder(itemCount:list.length,itemBuilder:(BuildContext context, int index){
                  return Text(list[index]);
                } ,),
              );
            });
          })
        ],
      ),
    ),
  );
}
