import 'package:fish_redux/fish_redux.dart';

//TODO replace with your own action
enum ListItemAction { action }

class ListItemActionCreator {
  static Action onAction() {
    return const Action(ListItemAction.action);
  }
}
