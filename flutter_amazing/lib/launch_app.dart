import 'package:flutter/material.dart';
import 'package:url_launcher/url_launcher.dart';

class LaunchAppPage extends StatefulWidget {
  @override
  _LaunchAppPageState createState() => _LaunchAppPageState();
}

class _LaunchAppPageState extends State<LaunchAppPage> {
  static const url = 'https://www.baidu.com';
  static const mapUrl = 'geo:52.32,4.917';

  @override
  Widget build(BuildContext context) {
    return Scaffold(
      appBar: AppBar(
        title: Text("在Flutter中打开第三方应用"),
      ),
      body: Container(
        child: Column(
          crossAxisAlignment: CrossAxisAlignment.start,
          children: <Widget>[
            _raiseButton(mapUrl, "打开地图"),
            _raiseButton(url, "通过浏览器打开链接"),
          ],
        ),
      ),
    );
  }

  Widget _raiseButton(String url, String title) {
    return RaisedButton(
        child: Text(title),
        onPressed: () {
          _launchURL(url);
        });
  }

  //通过手机浏览器打开一个网页
  _launchURL(String url) async {
    if (await canLaunch(url)) {
      await launch(url);
    } else {
      throw 'Could not launch $url';
    }
  }
}
