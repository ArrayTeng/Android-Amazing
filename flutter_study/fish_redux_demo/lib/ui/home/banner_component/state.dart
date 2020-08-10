import 'package:fish_redux/fish_redux.dart';

class HomeBannerState implements Cloneable<HomeBannerState> {

  @override
  HomeBannerState clone() {
    return HomeBannerState();
  }
}

HomeBannerState initState(Map<String, dynamic> args) {
  return HomeBannerState();
}
