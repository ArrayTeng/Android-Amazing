import 'package:fish_redux/fish_redux.dart';
import 'package:flutter_fish_redux/api.dart';

import 'action.dart';
import 'state.dart';

Reducer<GridPageState> buildReducer() {
  return asReducer(
    <Object, Reducer<GridPageState>>{
      GridPageAction.action: _onAction,
      GridPageAction.loadData:_onLoadData
    },
  );
}

GridPageState _onAction(GridPageState state, Action action) {
  final GridPageState newState = state.clone();
  return newState;
}

GridPageState _onLoadData(GridPageState state, Action action) {
  final GridPageState newState = state.clone()..gridModels = Api().getGridData();
  return newState;
}

