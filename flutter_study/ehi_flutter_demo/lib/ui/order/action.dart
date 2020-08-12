import 'package:fish_redux/fish_redux.dart';

//TODO replace with your own action
enum OrderAction { action }

class OrderActionCreator {
  static Action onAction() {
    return const Action(OrderAction.action);
  }
}
