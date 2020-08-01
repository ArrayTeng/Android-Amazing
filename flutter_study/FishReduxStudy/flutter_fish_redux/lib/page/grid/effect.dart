import 'package:fish_redux/fish_redux.dart';
import 'action.dart';
import 'state.dart';

Effect<GridPageState> buildEffect() {
  return combineEffects(<Object, Effect<GridPageState>>{

    GridPageAction.action: _onAction,
    ///页面初始化的发送action
    Lifecycle.initState:_onLoadData
  });
}

void _onAction(Action action, Context<GridPageState> ctx) {
}

void _onLoadData(Action action, Context<GridPageState> ctx){
  ctx.dispatch(GridPageActionCreator.onLoadData());
}
