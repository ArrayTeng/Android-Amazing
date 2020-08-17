import 'package:ehi_flutter_demo/ui/order/adapter/order_item_adapter.dart';
import 'package:ehi_flutter_demo/ui/order/item/state.dart';
import 'package:fish_redux/fish_redux.dart';

class ListState extends MutableSource implements Cloneable<ListState> {

  List<OrderItemState> items;

  @override
  ListState clone() {
    return ListState()..items = items;
  }

  @override
  Object getItemData(int index) {
    return items[index];
  }

  @override
  String getItemType(int index) {
    return OrderItemAdapter.item_style;
  }

  @override
  int get itemCount => items.length;

  @override
  void setItemData(int index, Object data) {
    items[index] = data;
  }
}

ListState initState(Map<String, dynamic> args) {
  return ListState();
}
