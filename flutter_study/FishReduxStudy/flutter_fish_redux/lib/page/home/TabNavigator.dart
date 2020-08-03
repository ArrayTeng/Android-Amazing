import 'package:flutter/material.dart';
import 'package:flutter_fish_redux/page/grid/page.dart';
import 'package:flutter_fish_redux/page/list/page.dart';

class TabNavigator extends StatefulWidget {
  @override
  _TabNavigatorState createState() => _TabNavigatorState();
}

class _TabNavigatorState extends State<TabNavigator>
    with SingleTickerProviderStateMixin {
  final _defaultColor = Colors.grey;
  final _activeColor = Colors.blue;

  int _currentIndex = 0;

  var _controller = PageController(initialPage: 0);

  @override
  void dispose() {
    super.dispose();
    _controller.dispose();
  }

  @override
  Widget build(BuildContext context) => Scaffold(
        body: PageView(
          controller: _controller,
          children: <Widget>[
            ListPagePage().buildPage(null),
            Container(),
            Container(),
            Container(),

          ],
          physics: NeverScrollableScrollPhysics(),
        ),
        bottomNavigationBar: _bottomNavigationBar(),
      );

  BottomNavigationBar _bottomNavigationBar() => BottomNavigationBar(
        currentIndex: _currentIndex,
        onTap: (index) {
          _controller.jumpToPage(index);
          setState(() {
            _currentIndex = index;
          });

        },
        type: BottomNavigationBarType.fixed,
        items: [
          buildBottomNavigationBarItem(Icons.home,'Home',0),
          buildBottomNavigationBarItem(Icons.search,'Search',1),
          buildBottomNavigationBarItem(Icons.camera_alt,'Camera',2),
          buildBottomNavigationBarItem(Icons.account_circle,'Account',3),
        ],
      );

  BottomNavigationBarItem buildBottomNavigationBarItem(IconData icon,String title,int index) => BottomNavigationBarItem(
    icon:Icon(icon,color: _defaultColor),
    activeIcon:Icon(icon,color: _activeColor,),
    title: Text(title,style: TextStyle(color: _currentIndex!=index?_defaultColor:_activeColor),)
  );
}
