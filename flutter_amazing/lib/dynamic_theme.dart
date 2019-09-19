

import 'package:flutter/material.dart';

class DynamicThemePage extends StatefulWidget {

  final AbsDynamicTheme absDynamicTheme;

  const DynamicThemePage({Key key, this.absDynamicTheme}) : super(key: key);


  @override
  _DynamicThemePageState createState() => _DynamicThemePageState();
}

class _DynamicThemePageState extends State<DynamicThemePage> {
  bool _lights = false;
  String _switchListTile = '白天';
  @override
  Widget build(BuildContext context) {
    return Scaffold(
      appBar: AppBar(title: Text("flutter 实现动态切换主题"),),
      body: Container(
        alignment: Alignment.center,
        child: Column(
          children: <Widget>[
           Text("Hello World"),
            SwitchListTile(title:Text(_switchListTile),value: _lights, onChanged: (value){
              widget.absDynamicTheme.changeTheme(value);
              setState(() {
                _lights = value;
                if(value == true){
                  _switchListTile = '黑夜';
                }else{
                  _switchListTile = '白天';
                }
              });

            })
          ],
        )
      ),
    );
  }
}


abstract class AbsDynamicTheme{

  changeTheme(bool isNight);
}
