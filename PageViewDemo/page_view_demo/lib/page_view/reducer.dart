import 'package:fish_redux/fish_redux.dart';

import 'action.dart';
import 'state.dart';

Reducer<PageViewState> buildReducer() {
  return asReducer(
    <Object, Reducer<PageViewState>>{
      PageViewAction.action: _onAction,
    },
  );
}

PageViewState _onAction(PageViewState state, Action action) {
  final PageViewState newState = state.clone();
  return newState;
}
