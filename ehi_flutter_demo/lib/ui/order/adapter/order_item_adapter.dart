

import 'package:ehi_flutter_demo/ui/order/item/component.dart';
import 'package:ehi_flutter_demo/ui/order/item/state.dart';
import 'package:ehi_flutter_demo/ui/order/list/state.dart';
import 'package:fish_redux/fish_redux.dart';

class OrderItemAdapter extends SourceFlowAdapter<ListState>{

  static const String item_style = "OrderItemAdapter";

  OrderItemAdapter():super(pool:<String,Component<Object>>{
    item_style:OrderItemComponent(),
  });

}