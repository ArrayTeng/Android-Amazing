import 'package:flutter/material.dart';
import 'package:flutter/services.dart';
import 'package:flutter_wechat_find/CustomAppBar.dart';
import 'package:flutter_wechat_find/RecommendCard.dart';

class ContentPage extends StatefulWidget {
  final ValueChanged<int> valueChanged;
  final ContentPageController contentPageController;

  const ContentPage({Key key, this.valueChanged, this.contentPageController})
      : super(key: key);

  @override
  _ContentPageState createState() {
    return _ContentPageState();
  }
}

class _ContentPageState extends State<ContentPage> {
  List<Color> _colors = [Colors.blue, Colors.grey, Colors.red, Colors.black];
  PageController _pageController = PageController(viewportFraction: 0.8);

  @override
  void initState() {
    super.initState();
    _statusBar();
    if (widget.contentPageController != null) {
      widget.contentPageController.pageController = _pageController;
    }
  }

  @override
  Widget build(BuildContext context) {
    return Column(
      children: <Widget>[
        CustomAppBar(),
        //Expanded用来撑开布局
        Expanded(
            child: PageView(
          onPageChanged: widget.valueChanged,
          controller: _pageController,
          children: <Widget>[_wrapItem(RecommendCard()), _wrapItem(RecommendCard()), _wrapItem(RecommendCard()),_wrapItem(RecommendCard())],
        ))
      ],
    );
  }

  _wrapItem(Widget widget) {
    return Padding(padding: EdgeInsets.all(10), child: widget);
  }

  //实现沉浸式状态栏
  _statusBar() {
    SystemUiOverlayStyle systemUiOverlayStyle = SystemUiOverlayStyle(
      systemNavigationBarColor: Color(0xFF000000),
      systemNavigationBarDividerColor: null,
      statusBarColor: Colors.transparent,
      systemNavigationBarIconBrightness: Brightness.light,
      statusBarIconBrightness: Brightness.dark,
      statusBarBrightness: Brightness.light,
    );

    SystemChrome.setSystemUIOverlayStyle(systemUiOverlayStyle);
  }
}

class ContentPageController {
  PageController pageController;

  void jumpToPage(int indexPage) {
    pageController?.jumpToPage(indexPage);
  }
}
