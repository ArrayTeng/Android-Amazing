import 'package:fish_redux/fish_redux.dart';
import 'action.dart';
import 'state.dart';

Effect<ListItemState> buildEffect() {
  return combineEffects(<Object, Effect<ListItemState>>{
    ListItemAction.action: _onAction,
  });
}

void _onAction(Action action, Context<ListItemState> ctx) {
}
