import 'package:fish_redux/fish_redux.dart';

//TODO replace with your own action
enum ListAction { action ,upDateListItem}

class ListActionCreator {
  static Action onAction() {
    return const Action(ListAction.action);
  }

  static Action onUpDateListItem(var list){
    return Action(ListAction.upDateListItem,payload: list);
  }
}
