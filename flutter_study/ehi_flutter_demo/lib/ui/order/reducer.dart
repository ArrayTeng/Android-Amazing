import 'package:fish_redux/fish_redux.dart';

import 'action.dart';
import 'state.dart';

Reducer<OrderState> buildReducer() {
  return asReducer(
    <Object, Reducer<OrderState>>{
      OrderAction.action: _onAction,
    },
  );
}

OrderState _onAction(OrderState state, Action action) {
  final OrderState newState = state.clone();
  return newState;
}
