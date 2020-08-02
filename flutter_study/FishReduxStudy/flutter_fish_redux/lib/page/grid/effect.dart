import 'package:fish_redux/fish_redux.dart';
import 'package:flutter/material.dart' hide Action;
import 'action.dart';
import 'state.dart';

Effect<GridPageState> buildEffect() {
  return combineEffects(<Object, Effect<GridPageState>>{
    GridPageAction.action: _onAction,

    ///页面初始化的发送action
    Lifecycle.initState: _onLoadData,
    GridPageAction.openList: _onOpenList
  });
}

void _onAction(Action action, Context<GridPageState> ctx) {}

void _onLoadData(Action action, Context<GridPageState> ctx) {
  ctx.dispatch(GridPageActionCreator.onLoadData());
}

void _onOpenList(Action action, Context<GridPageState> ctx) {
  Navigator.of(ctx.context).pushNamed('list_page', arguments: null);
}
