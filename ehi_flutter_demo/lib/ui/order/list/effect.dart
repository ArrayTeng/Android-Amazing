import 'package:ehi_flutter_demo/ui/order/been/date.dart';
import 'package:ehi_flutter_demo/ui/order/item/state.dart';
import 'package:fish_redux/fish_redux.dart';
import 'action.dart';
import 'state.dart';

Effect<ListState> buildEffect() {
  return combineEffects(<Object, Effect<ListState>>{
    ListAction.action: _onAction,
    ///在页面初始化的时候执行这个函数
    Lifecycle.initState: _onUpdateListItem,
  });
}

void _onUpdateListItem(Action action, Context<ListState> ctx) {
  List<Date> dates = new List();
  dates.add(Date());
  dates.add(Date());
  dates.add(Date());
  dates.add(Date());
  dates.add(Date());
  dates.add(Date());

  List<OrderItemState> items = List.generate(dates.length, (index){
    return OrderItemState(date: dates[index]);
  });

  ctx.dispatch(ListActionCreator.onUpDateListItem(items));
}

void _onAction(Action action, Context<ListState> ctx) {}
