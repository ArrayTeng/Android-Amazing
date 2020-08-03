import 'package:fish_redux/fish_redux.dart';

class HomePageState implements Cloneable<HomePageState> {

  @override
  HomePageState clone() {
    return HomePageState();
  }
}

HomePageState initState(Map<String, dynamic> args) {
  return HomePageState();
}
