import 'package:fish_redux/fish_redux.dart';
import 'package:flutter/material.dart';

import 'action.dart';
import 'state.dart';

Widget buildView(
    GridPageState state, Dispatch dispatch, ViewService viewService) {
  return Scaffold(
    appBar: AppBar(
      title: Text('Grid'),
    ),
    body: GridView.count(
        crossAxisCount: 2,
        crossAxisSpacing: 20.0,
        mainAxisSpacing: 20.0,
        childAspectRatio: 1 / 1,
        padding: EdgeInsets.all(20),
        children: List.generate(state.gridModels.length, (index) {
          return Container(
            child: Card(
              color: Colors.lightBlueAccent,
              child: InkWell(
                onTap: () {},
                child: Container(
                  child: Center(
                    child: Text(state.gridModels[index].name),
                  ),
                ),
              ),
            ),
          );
        })),
  );
}
