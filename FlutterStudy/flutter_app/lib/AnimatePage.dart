import 'package:flutter/material.dart';

class AnimatePage extends StatefulWidget {
  @override
  State<StatefulWidget> createState() {
    return _AnimateAppState();
  }
}

class _AnimateAppState extends State with SingleTickerProviderStateMixin {
  Animation<double> animation;

  AnimationController animationController;

  @override
  void initState() {
    super.initState();
    animationController =
        AnimationController(vsync: this, duration: const Duration(seconds: 5));

    animation = Tween(begin: 50.0, end: 300.0).animate(animationController);
    animation.addListener(() {
      setState(() {});
    });

    animationController.forward();
  }

  @override
  void dispose() {
    animationController.dispose();
    super.dispose();
  }

  @override
  Widget build(BuildContext context) {
    // TODO: implement build
    return buildWidget();
  }



  Widget buildWidget() {
    return Scaffold(
        appBar: AppBar(
          title: Text('Flutter 动画'),
        ),
        body: Center(
          child: Container(
            width: animation.value,
            height: animation.value,
            child: FlutterLogo(),
          ),
        ));
  }
}


class MyAnimationWidget extends AnimatedWidget{

  MyAnimationWidget({Key key,Animation<double> animation}):super(key:key,listenable:animation);

  @override
  Widget build(BuildContext context) {
    Animation<double> animation = listenable;
    return Center(
      child: Container(
        width: animation.value,
        height: animation.value,
        child: FlutterLogo(),
      ),
    );
  }

}
