import 'package:fish_redux/fish_redux.dart';
import 'action.dart';
import 'state.dart';

Effect<OrderState> buildEffect() {
  return combineEffects(<Object, Effect<OrderState>>{
    OrderAction.action: _onAction,
  });
}

void _onAction(Action action, Context<OrderState> ctx) {
}
