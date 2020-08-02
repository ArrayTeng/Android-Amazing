import 'package:fish_redux/fish_redux.dart';

class ListItemState implements Cloneable<ListItemState> {

  int type;
  String title;
  String content;


  ListItemState({this.type, this.title, this.content});

  @override
  ListItemState clone() {
    return ListItemState()
    ..type = type
    ..title = title
    ..content = content;
  }
}

ListItemState initState(Map<String, dynamic> args) {
  return ListItemState();
}
