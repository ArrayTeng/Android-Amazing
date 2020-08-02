import 'package:fish_redux/fish_redux.dart';
import 'package:flutter_fish_redux/page/list/item/state.dart';

import 'action.dart';
import 'state.dart';

Reducer<ListPageState> buildReducer() {
  return asReducer(
    <Object, Reducer<ListPageState>>{
      ListPageAction.action: _onAction,
      ListPageAction.initList:_initList
    },
  );
}

ListPageState _onAction(ListPageState state, Action action) {
  final ListPageState newState = state.clone();
  return newState;
}

ListPageState _initList(ListPageState state, Action action) {
  final List<ListItemState> items = action.payload ?? <ListItemState>[];
  final ListPageState newState = state.clone();
  newState.items = items;
  return newState;
}
