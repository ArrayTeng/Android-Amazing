import 'package:fish_redux/fish_redux.dart';

import 'effect.dart';
import 'reducer.dart';
import 'state.dart';
import 'view.dart';

class HomeHeaderComponent extends Component<HomeHeaderState> {
  HomeHeaderComponent()
      : super(
            effect: buildEffect(),
            reducer: buildReducer(),
            view: buildView,
            dependencies: Dependencies<HomeHeaderState>(
                adapter: null,
                slots: <String, Dependent<HomeHeaderState>>{
                }),);

}
