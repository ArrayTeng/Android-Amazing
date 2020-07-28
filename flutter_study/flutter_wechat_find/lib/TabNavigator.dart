import 'package:flutter/material.dart';
import 'package:flutter_wechat_find/ContentPage.dart';

class TabNavigator extends StatefulWidget {
  @override
  State<StatefulWidget> createState() {
    return _TabNavigatorState();
  }
}

class _TabNavigatorState extends State<StatefulWidget> {
  final _defaultColor = Colors.grey;
  final _activeColor = Colors.blue;

  final ContentPageController _contentPageController = ContentPageController();

  int currentIndex = 0;

  @override
  Widget build(BuildContext context) {
    return Scaffold(

      body: Container(
          decoration: BoxDecoration(
            gradient: LinearGradient(
                colors: [Color(0xffedeef0), Color(0xffe6e7e9)],
                begin: Alignment.topCenter,
                end: Alignment.bottomCenter),
          ),
          child: ContentPage(
            valueChanged: (index) {
              setState(() {
                currentIndex = index;
              });
            },
            contentPageController: _contentPageController,
          )),
      bottomNavigationBar: BottomNavigationBar(
          currentIndex: currentIndex,
          onTap: (index) {
            setState(() {
              currentIndex = index;
              _contentPageController.jumpToPage(index);
            });
          },
          type: BottomNavigationBarType.fixed,
          items: [
            _createBottomNavigationBarItem(Icons.folder, "本周"),
            _createBottomNavigationBarItem(Icons.explore, "分享"),
            _createBottomNavigationBarItem(Icons.donut_small, "免费"),
            _createBottomNavigationBarItem(Icons.person, "长安")
          ]),
    );
  }

  _createBottomNavigationBarItem(IconData iconData, String title) {
    return BottomNavigationBarItem(
        icon: Icon(iconData, color: _defaultColor),
        activeIcon: Icon(iconData, color: _activeColor),
        title: Text(title));
  }
}
