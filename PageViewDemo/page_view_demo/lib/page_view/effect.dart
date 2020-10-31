import 'package:fish_redux/fish_redux.dart';
import 'action.dart';
import 'state.dart';

Effect<PageViewState> buildEffect() {
  return combineEffects(<Object, Effect<PageViewState>>{
    PageViewAction.action: _onAction,
  });
}

void _onAction(Action action, Context<PageViewState> ctx) {
}
