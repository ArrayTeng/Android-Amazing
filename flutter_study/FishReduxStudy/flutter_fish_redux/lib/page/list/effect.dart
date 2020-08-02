import 'package:fish_redux/fish_redux.dart';
import 'package:flutter_fish_redux/page/list/item/state.dart';
import 'action.dart';
import 'state.dart';

Effect<ListPageState> buildEffect() {
  return combineEffects(<Object, Effect<ListPageState>>{
    ListPageAction.action: _onAction,
    Lifecycle.initState: _onInitList,
  });
}

void _onAction(Action action, Context<ListPageState> ctx) {}

void _onInitList(Action action, Context<ListPageState> ctx) {
  List<ListItemState> mockData = [
    ListItemState(type: 1, title: '1', content: '123456'),
    ListItemState(type: 0, title: '2', content: '123456'),
    ListItemState(type: 1, title: '3', content: '123456'),
    ListItemState(type: 0, title: '4', content: '123456'),
    ListItemState(type: 0, title: '5', content: '123456'),
    ListItemState(type: 0, title: '6', content: '123456'),
    ListItemState(type: 1, title: '7', content: '123456'),
    ListItemState(type: 1, title: '8', content: '123456'),
  ];
  ctx.dispatch(ListPageActionCreator.onInitList(mockData));
}
