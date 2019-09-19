

import 'package:flutter/material.dart';

class WidgetLifecyclePage extends StatefulWidget {



  @override
  _WidgetLifecyclePageState createState() => _WidgetLifecyclePageState();
}

class _WidgetLifecyclePageState extends State<WidgetLifecyclePage> with WidgetsBindingObserver{

  @override
  void initState() {
    WidgetsBinding.instance.addObserver(this);
    super.initState();
  }

  @override
  void didChangeAppLifecycleState(AppLifecycleState state) {
    super.didChangeAppLifecycleState(state);
    if(state == AppLifecycleState.paused){
      //APP 进入后台
    }else if(state == AppLifecycleState.resumed){
      // APP 进入前台与用户处于交互状态
    }else if(state == AppLifecycleState.inactive){
      //应用程序处于非活动状态，并且未接收用户的输入，如 来了个电话
    }else if(state == AppLifecycleState.suspending){
      //应用被挂起，但是在iOS上不会出现
    }
  }

  @override
  Widget build(BuildContext context) {
    return Container();
  }

  @override
  void dispose() {
    WidgetsBinding.instance.removeObserver(this);
    super.dispose();
  }

}
