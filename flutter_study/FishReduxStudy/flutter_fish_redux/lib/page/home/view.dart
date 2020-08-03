import 'package:fish_redux/fish_redux.dart';
import 'package:flutter/material.dart';
import 'package:flutter_fish_redux/page/home/TabNavigator.dart';

import 'action.dart';
import 'state.dart';

Widget buildView(HomePageState state, Dispatch dispatch, ViewService viewService) {
  return TabNavigator();
}
