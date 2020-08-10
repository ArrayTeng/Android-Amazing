import 'package:fish_redux/fish_redux.dart';

import 'action.dart';
import 'state.dart';

Reducer<HomeBannerState> buildReducer() {
  return asReducer(
    <Object, Reducer<HomeBannerState>>{
      HomeBannerAction.action: _onAction,
    },
  );
}

HomeBannerState _onAction(HomeBannerState state, Action action) {
  final HomeBannerState newState = state.clone();
  return newState;
}
