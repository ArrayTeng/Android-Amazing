
import 'package:flutter/material.dart';

///列表的展开与收起
class ExpansionTilePage extends StatefulWidget {
  @override
  ExpansionTilePageState createState() => ExpansionTilePageState();
}

class ExpansionTilePageState extends State<ExpansionTilePage> {
  static const CITY_NAMES = {
    '北京': ['东城区', '西城区', '朝阳区', '丰台区', '石景山区', '海淀区'],
    '上海': ['黄浦区', '徐汇区', '长宁区', '静安区', '普陀区', '虹口区'],
    '广州': ['荔湾区', '越秀区', '海珠区', '天河区', '白云区', '黄埔区'],
    '深圳': ['罗湖区', '福田区', '南山区', '宝安区', '龙岗区', '盐田区']
  };

  @override
  Widget build(BuildContext context) {
    return Scaffold(
      appBar: AppBar(
        title: Text("列表的展开与收起"),
      ),
      body: _buildContainer(),
    );
  }

  _buildContainer() => Container(
        child: ListView(
          children: _buildList(),
        ),
      );

  List<Widget> _buildList() {
    List<Widget> widgets = [];
    CITY_NAMES.keys.forEach((key) {
      widgets.add(_buildItem(key, CITY_NAMES[key]));
    });
    return widgets;
  }

  Widget _buildItem(String city, List<String> stubCities) {
    return ExpansionTile(
      title: Text(
        city,
        style: TextStyle(color: Colors.black38, fontSize: 20),
      ),
      children: stubCities.map((subCity) => _buildStub(subCity)).toList(),
    );
  }

  Widget _buildStub(String stubCity) => FractionallySizedBox(
        widthFactor: 1,
        child: Container(
          height: 50,
          margin: EdgeInsets.only(bottom: 5),
          decoration: BoxDecoration(color: Colors.lightBlueAccent),
          child: Text(stubCity),
        ),
      );
}
