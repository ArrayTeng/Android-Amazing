
import 'package:fish_redux/fish_redux.dart';
import 'package:flutter_fish_redux/page/list/item/component.dart';
import 'package:flutter_fish_redux/page/list/item/state.dart';

import '../state.dart';

class ListAdapterAdapter extends DynamicFlowAdapter<ListPageState> {
  ListAdapterAdapter()
      : super(
          pool: <String, Component<Object>>{'MyItem': ListItemComponent()},
          connector: _ListAdapterConnector(),
        );
}

class _ListAdapterConnector extends ConnOp<ListPageState, List<ItemBean>> {
  @override
  List<ItemBean> get(ListPageState state) {
    if (state.items?.isNotEmpty == true) {
      return state.items.map<ItemBean>(
          (ListItemState data) => ItemBean('MyItem', data)).toList(growable: true);
    } else {
      return <ItemBean>[];
    }
  }

  @override
  void set(ListPageState state, List<ItemBean> items) {
    if (items?.isNotEmpty == true) {
      state.items = List<ListItemState>.from(items
          .map<ListItemState>((ItemBean itemBean) => itemBean.data)
          .toList());
    } else {
      state.items = <ListItemState>[];
    }
  }

  @override
  subReducer(reducer) {
    return super.subReducer(reducer);
  }
}
