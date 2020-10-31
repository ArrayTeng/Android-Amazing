import 'package:fish_redux/fish_redux.dart';
import 'package:flutter/material.dart';

import 'action.dart';
import 'state.dart';

Widget buildView(PageViewState state, Dispatch dispatch,
    ViewService viewService) {
  return Container(
    child: buildBody(state, dispatch, viewService),
  );
}

Widget buildBody(PageViewState state, Dispatch dispatch,
    ViewService viewService) {
  return Container(

      color: Colors.white,
      child: SingleChildScrollView(
        child: Column(
          children: <Widget>[

            buildPageView(state, dispatch, viewService),

            buildContent('1', viewService.context),
            buildContent('2', viewService.context),
            buildContent('3', viewService.context),
            buildContent('4', viewService.context),
            buildContent('5', viewService.context),
            buildContent('6', viewService.context),
            buildContent('7', viewService.context),
          ],
        ),
      )
  );
}


Widget buildContent(String text, BuildContext context) {
  return Container(
    margin: EdgeInsets.all(5),
    color: Colors.amber,
    height: 150,
    width: MediaQuery
        .of(context)
        .size
        .width,
    child: Text(text, style: TextStyle(fontSize: 30),),
  );
}

Widget buildPageView(PageViewState state, Dispatch dispatch,
    ViewService viewService) {
  return AnimatedContainer(
    duration: Duration(microseconds: 300),
    curve: Curves.ease,
    height: state.height,
    child: Container(
      child: NotificationListener(
          onNotification: (ScrollNotification scrollNotification){
            
          },
          child: null),
    ),);
}
