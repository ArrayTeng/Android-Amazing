import 'package:ehi_flutter_demo/config/config_route.dart';
import 'package:ehi_flutter_demo/ui/home/page.dart';
import 'package:ehi_flutter_demo/ui/login/page.dart';
import 'package:ehi_flutter_demo/ui/main/page.dart';
import 'package:fish_redux/fish_redux.dart';
import 'package:flutter/cupertino.dart' hide Action, Page;
import 'package:flutter/material.dart' hide Action, Page;

void main() {
  runApp(createAPP());
}

Widget createAPP() {
  final AbstractRoutes routes =
      PageRoutes(pages: <String, Page<Object, dynamic>>{
    RouteConfigs.route_name_login: LoginPage(),
    RouteConfigs.route_name_main: MainPage(),
    RouteConfigs.route_name_home: HomePage()
  });

  return MaterialApp(
    home: routes.buildPage(RouteConfigs.route_name_main, null),
    onGenerateRoute: (RouteSettings settings) {
      return CupertinoPageRoute(builder: (context) {
        return routes.buildPage(settings.name, settings.arguments);
      });
    },
  );
}
