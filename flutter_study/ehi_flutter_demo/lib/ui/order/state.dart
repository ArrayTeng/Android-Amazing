import 'package:fish_redux/fish_redux.dart';

class OrderState implements Cloneable<OrderState> {

  @override
  OrderState clone() {
    return OrderState();
  }
}

OrderState initState(Map<String, dynamic> args) {
  return OrderState();
}
