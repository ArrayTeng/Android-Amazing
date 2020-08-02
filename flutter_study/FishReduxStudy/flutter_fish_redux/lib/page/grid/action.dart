import 'package:fish_redux/fish_redux.dart';

enum GridPageAction { action, loadData,openList }

class GridPageActionCreator {
  static Action onAction() {
    return const Action(GridPageAction.action);
  }

  static Action onLoadData() {
    return const Action(GridPageAction.loadData);
  }

  static Action onOpenList(){
    return const Action(GridPageAction.openList);
  }
}
