import 'package:fish_redux/fish_redux.dart';

import 'effect.dart';
import 'reducer.dart';
import 'state.dart';
import 'view.dart';

class PageViewPage extends Page<PageViewState, Map<String, dynamic>> {
  PageViewPage()
      : super(
            initState: initState,
            effect: buildEffect(),
            reducer: buildReducer(),
            view: buildView,
            dependencies: Dependencies<PageViewState>(
                adapter: null,
                slots: <String, Dependent<PageViewState>>{
                }),
            middleware: <Middleware<PageViewState>>[
            ],);

}
