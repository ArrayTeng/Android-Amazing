import 'package:fish_redux/fish_redux.dart';

//TODO replace with your own action
enum HomeBannerAction { action }

class HomeBannerActionCreator {
  static Action onAction() {
    return const Action(HomeBannerAction.action);
  }
}
