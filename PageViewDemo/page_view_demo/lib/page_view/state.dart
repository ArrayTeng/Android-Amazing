import 'package:fish_redux/fish_redux.dart';

class PageViewState implements Cloneable<PageViewState> {

  double height;

  @override
  PageViewState clone() {
    return PageViewState()..height = height;
  }
}

PageViewState initState(Map<String, dynamic> args) {
  return PageViewState()..height = 100;
}
