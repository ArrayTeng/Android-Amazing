import 'package:flutter/material.dart';

///key可以控制控件如何替代渲染树中的另一个控件
class BubbleDemoPage extends StatelessWidget {
  final GlobalKey globalKey = GlobalKey();

  /// 获取 key 所在的 x 轴位置
  getX(GlobalKey key) {
    RenderBox renderBox = key.currentContext.findRenderObject();
    double dx = renderBox.localToGlobal(Offset.zero).dx;
    return dx;
  }

  ///获取 Key 所在的 y 轴位置
  getY(GlobalKey key) {
    RenderBox renderBox = key.currentContext.findRenderObject();
    double dy = renderBox.localToGlobal(Offset.zero).dy;
    return dy;
  }

  ///获取key所在的widget的宽度
  getWidth(GlobalKey key) {
    RenderBox renderBox = key.currentContext.findRenderObject();
    return renderBox.size.width;
  }

  ///获取key所在的widget的高度
  getHeight(GlobalKey key) {
    RenderBox renderBox = key.currentContext.findRenderObject();
    return renderBox.size.height;
  }

  getCurrentY(GlobalKey key) {
    return getHeight(key) -
        MediaQueryData.fromWindow(WidgetsBinding.instance.window).padding.top;
  }

  @override
  Widget build(BuildContext context) {
    return _buildContainerWidget(context);
  }

  _buildContainerWidget(BuildContext context) => Container(
        width: MediaQuery.of(context).size.width,
        height: MediaQuery.of(context).size.height,
        margin: EdgeInsets.all(15),
        child: _buildStackWidget(),
      );

  _buildStackWidget() => Stack(
        children: <Widget>[
          MaterialButton(
            key: globalKey,
            onPressed: () {
              print("currentX:${getX(globalKey)+getWidth(globalKey)/2} currentY:${getCurrentY(globalKey)}");
            },
            child: Text("click me"),
            color: Colors.blue,
          )
        ],
      );
}
