import 'package:fish_redux/fish_redux.dart';
import 'package:fish_redux_demo/route.dart';
import 'package:fish_redux_demo/ui/splash/page.dart';
import 'package:flutter/cupertino.dart' hide Action,Page;
import 'package:flutter/material.dart' hide Action,Page;

void main() {
  runApp(createApp());
}

Widget createApp(){
  final AbstractRoutes routes = PageRoutes(pages: <String, Page<Object, dynamic>>{
    RouteConfigs.route_name_splash_page:SplashPage(),
  });

  return MaterialApp(
    title: 'fish_redux',
    home: routes.buildPage(RouteConfigs.route_name_splash_page, null),
    onGenerateRoute:(settings){
      return CupertinoPageRoute(builder: (context){
        return routes.buildPage(settings.name, settings.arguments);
      });
    } ,
  );
}

