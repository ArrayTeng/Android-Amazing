import 'package:fish_redux/fish_redux.dart';

import 'action.dart';
import 'state.dart';

Reducer<ListItemState> buildReducer() {
  return asReducer(
    <Object, Reducer<ListItemState>>{
      ListItemAction.action: _onAction,
    },
  );
}

ListItemState _onAction(ListItemState state, Action action) {
  final ListItemState newState = state.clone();
  return newState;
}
