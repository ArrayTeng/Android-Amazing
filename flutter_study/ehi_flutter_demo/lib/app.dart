

import 'package:fish_redux/fish_redux.dart';
import 'package:flutter/material.dart' hide Action,Page;

Widget createAPP(){
  final AbstractRoutes routes = PageRoutes(pages: <String, Page<Object, dynamic>>{

  });

  return MaterialApp(
    home: routes.buildPage(path, arguments),
  );
}

