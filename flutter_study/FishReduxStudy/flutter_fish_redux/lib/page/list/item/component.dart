import 'package:fish_redux/fish_redux.dart';

import 'effect.dart';
import 'reducer.dart';
import 'state.dart';
import 'view.dart';

class ListItemComponent extends Component<ListItemState> {
  ListItemComponent()
      : super(
            effect: buildEffect(),
            reducer: buildReducer(),
            view: buildView,
            dependencies: Dependencies<ListItemState>(
                adapter: null,
                slots: <String, Dependent<ListItemState>>{
                }),);

}
