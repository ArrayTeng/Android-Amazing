import 'package:fish_redux/fish_redux.dart';

import 'item/state.dart';

enum ListPageAction { action,initList }

class ListPageActionCreator {
  static Action onAction() {
    return const Action(ListPageAction.action);
  }

  static Action onInitList(List<ListItemState> items) {
    return Action(ListPageAction.initList,payload: items);
  }
}
