import 'package:fish_redux/fish_redux.dart';
import 'action.dart';
import 'state.dart';

Effect<HomeBannerState> buildEffect() {
  return combineEffects(<Object, Effect<HomeBannerState>>{
    HomeBannerAction.action: _onAction,
  });
}

void _onAction(Action action, Context<HomeBannerState> ctx) {
}
