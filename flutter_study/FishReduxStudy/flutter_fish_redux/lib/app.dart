import 'package:fish_redux/fish_redux.dart';
import 'package:flutter/material.dart' hide Action, Page;
import 'package:flutter_fish_redux/page/entrance/page.dart';
import 'package:flutter_fish_redux/page/grid/page.dart';

Widget createAPP() {
  final AbstractRoutes routes = PageRoutes(
      pages: <String, Page<Object, dynamic>>{
        'entrance_page': EntrancePage(),
        'grid_page': GridPagePage()
      });

  return MaterialApp(
    title: 'first_fish_redux',
    home: routes.buildPage('entrance_page', null),
    onGenerateRoute: (RouteSettings settings) {
      return MaterialPageRoute<Object>(builder: (BuildContext context) {
        return routes.buildPage(settings.name, settings.arguments);
      });
    },
  );
}
