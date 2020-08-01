import 'package:fish_redux/fish_redux.dart';

import 'effect.dart';
import 'reducer.dart';
import 'state.dart';
import 'view.dart';

class GridPagePage extends Page<GridPageState, Map<String, dynamic>> {
  GridPagePage()
      : super(
            initState: initState,
            effect: buildEffect(),
            reducer: buildReducer(),
            view: buildView,
            dependencies: Dependencies<GridPageState>(
                adapter: null,
                slots: <String, Dependent<GridPageState>>{
                }),
            middleware: <Middleware<GridPageState>>[
            ],);

}
