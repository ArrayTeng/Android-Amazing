import 'package:ehi_flutter_demo/ui/home/page.dart';
import 'package:ehi_flutter_demo/ui/order/page.dart';
import 'package:fish_redux/fish_redux.dart';
import 'package:flutter/material.dart';

import 'action.dart';
import 'state.dart';

Widget buildView(MainState state, Dispatch dispatch, ViewService viewService) {
  return _TabNavigator();
}

class _TabNavigator extends StatefulWidget {
  @override
  _TabNavigatorState createState() => _TabNavigatorState();
}

class _TabNavigatorState extends State<_TabNavigator> {
  int _currentIndex = 0;

  final _defaultColor = Color(0xFF333333);
  final _activeColor = Color(0xff0299ff);

  var _controller = PageController(initialPage: 0);

  @override
  void dispose() {
    super.dispose();
    _controller.dispose();
  }

  @override
  Widget build(BuildContext context) {
    return Scaffold(
      body: PageView(
        controller: _controller,
        physics: NeverScrollableScrollPhysics(),
        children: <Widget>[
          HomePage().buildPage(null),
          OrderPage().buildPage(null),
        ],
      ),
      bottomNavigationBar: BottomNavigationBar(
          currentIndex: _currentIndex,
          type: BottomNavigationBarType.fixed,
          onTap: (index) {
            _controller.jumpToPage(index);
            setState(() {
              _currentIndex = index;
            });
          },
          items: [
            buildBottomNavigationBar(AssetImage('images/main_bottom_nv_main_ic.png'),AssetImage('images/main_bottom_nv_main_s_ic.png'), '首页', 0),
            buildBottomNavigationBar(AssetImage('images/main_bottom_nv_order_ic.png'),AssetImage('images/main_bottom_nv_order_s_ic.png'), '订单', 1)
          ]),
    );
  }

  BottomNavigationBarItem buildBottomNavigationBar(ImageProvider defaultImage,
      ImageProvider activeImage, String title, int index) {
    return BottomNavigationBarItem(
        icon: ImageIcon(defaultImage),
        activeIcon: ImageIcon(activeImage),
        title: Text(
          title,
          style: TextStyle(
              color: _currentIndex != index ? _defaultColor : _activeColor),
        ));
  }
}
