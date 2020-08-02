import 'package:fish_redux/fish_redux.dart';

import 'adapter/adapter.dart';
import 'effect.dart';
import 'reducer.dart';
import 'state.dart';
import 'view.dart';

class ListPagePage extends Page<ListPageState, Map<String, dynamic>> {
  ListPagePage()
      : super(
            initState: initState,
            effect: buildEffect(),
            reducer: buildReducer(),
            view: buildView,
            dependencies: Dependencies<ListPageState>(
                adapter: NoneConn<ListPageState>()+ListAdapterAdapter(),
                slots: <String, Dependent<ListPageState>>{
                }),
            middleware: <Middleware<ListPageState>>[
            ],);

}
