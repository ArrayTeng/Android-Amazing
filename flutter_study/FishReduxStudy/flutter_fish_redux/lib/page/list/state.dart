
import 'package:fish_redux/fish_redux.dart';
import 'package:flutter_fish_redux/page/list/item/state.dart';

class ListPageState implements Cloneable<ListPageState> {

  List<ListItemState> items;

  @override
  ListPageState clone() {
    return ListPageState()
      ..items = items;
  }
}

ListPageState initState(Map<String, dynamic> args) {
  return ListPageState();
}
