import 'package:fish_redux/fish_redux.dart';
import 'package:flutter_fish_redux/page/model/grid/GridModel.dart';

class GridPageState implements Cloneable<GridPageState> {

  List<GridModel> gridModels;

  @override
  GridPageState clone() {
    return GridPageState()
    ..gridModels = gridModels;
  }
}

GridPageState initState(Map<String, dynamic> args) {
  return GridPageState();
}
