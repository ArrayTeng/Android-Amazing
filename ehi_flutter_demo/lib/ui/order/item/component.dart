import 'package:fish_redux/fish_redux.dart';

import 'state.dart';
import 'view.dart';

class OrderItemComponent extends Component<OrderItemState> {
  OrderItemComponent()
      : super(
            view: buildView,
            dependencies: Dependencies<OrderItemState>(
                adapter: null,
                slots: <String, Dependent<OrderItemState>>{
                }),);

}
