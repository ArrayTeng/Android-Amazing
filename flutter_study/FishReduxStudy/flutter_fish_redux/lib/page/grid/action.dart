import 'package:fish_redux/fish_redux.dart';

enum GridPageAction { action, loadData }

class GridPageActionCreator {
  static Action onAction() {
    return const Action(GridPageAction.action);
  }

  static Action onLoadData() {
    return const Action(GridPageAction.loadData);
  }
}
