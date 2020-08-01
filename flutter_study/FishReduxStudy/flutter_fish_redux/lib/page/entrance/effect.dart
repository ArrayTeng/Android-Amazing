import 'package:fish_redux/fish_redux.dart';
import 'package:flutter/material.dart' hide Action;
import 'action.dart';
import 'state.dart';

Effect<EntranceState> buildEffect() {
  return combineEffects(<Object, Effect<EntranceState>>{
    EntranceAction.action: _onAction,
    EntranceAction.openGrid:_openGrid
  });
}

void _onAction(Action action, Context<EntranceState> ctx) {

}

void _openGrid(Action action, Context<EntranceState> ctx){
  Navigator.of(ctx.context).pushNamed('grid_page',arguments: null);
}
