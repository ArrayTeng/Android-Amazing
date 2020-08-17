import 'package:fish_redux/fish_redux.dart';
import 'package:flutter/material.dart';

import 'action.dart';
import 'state.dart';

Widget buildView(ListState state, Dispatch dispatch, ViewService viewService) {
  return createWidget(state,viewService);
}

Widget createWidget(ListState state, ViewService viewService) {
//  if (state.items != null) {
    return ListView.builder(
      itemBuilder: viewService.buildAdapter().itemBuilder,
      itemCount: viewService.buildAdapter().itemCount,
    );
//  }else{
//    return Container(
//      child: Text('Test'),
//    );
//  }
}
