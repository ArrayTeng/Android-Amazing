import 'package:ehi_flutter_demo/ui/order/adapter/order_item_adapter.dart';
import 'package:ehi_flutter_demo/ui/order/been/date.dart';
import 'package:fish_redux/fish_redux.dart';

class OrderItemState  implements Cloneable<OrderItemState> {

  Date date;


  OrderItemState({this.date});

  @override
  OrderItemState clone() {
    return OrderItemState()..date = date;
  }

}

OrderItemState initState(Map<String, dynamic> args) {
  return OrderItemState();
}
