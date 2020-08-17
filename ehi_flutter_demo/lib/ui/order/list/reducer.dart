import 'package:fish_redux/fish_redux.dart';

import 'action.dart';
import 'state.dart';

Reducer<ListState> buildReducer() {
  return asReducer(
    <Object, Reducer<ListState>>{
      ListAction.upDateListItem:_onUpdateItem,
    },
  );
}

ListState _onUpdateItem(ListState state, Action action){
  return state.clone()..items = action.payload;
}

ListState _onAction(ListState state, Action action) {
  final ListState newState = state.clone();
  return newState;
}
