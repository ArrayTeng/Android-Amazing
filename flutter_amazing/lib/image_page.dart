import 'dart:io';

import 'package:flutter/material.dart';
import 'package:path_provider/path_provider.dart';
import 'package:transparent_image/transparent_image.dart';
import 'package:cached_network_image/cached_network_image.dart';



class MyImagePage extends StatefulWidget {
  @override
  _MyImagePageState createState() => _MyImagePageState();
}

class _MyImagePageState extends State<MyImagePage> {
  @override
  Widget build(BuildContext context) {
    return Scaffold(
      appBar: AppBar(
        title: Text('fasdfsd'),
      ),
      body: Container(
        child: Column(
          children: <Widget>[
            //用到了 transparent_image 中的 kTransparentImage
//            Stack(
//              children: <Widget>[
//                Center(child: CircularProgressIndicator(),),
//                Center(child: FadeInImage.memoryNetwork(placeholder: kTransparentImage, image: 'http://www.devio.org/img/avatar.png'),)
//              ],
//            ),
//            FadeInImage.assetNetwork(placeholder: 'images/readimage.jpeg', image: 'http://www.devio.org/img/avatar.png')
            CachedNetworkImage(
              imageUrl: "http://via.placeholder.com/350x150",
              placeholder: (context, url) => new CircularProgressIndicator(),
              errorWidget: (context, url, error) => new Icon(Icons.error),
            ),
          ],
        ),
      ),
    );
  }

//获取SDCard的路径：
  Future<File> _getLocalFile(String filename) async {
    String dir = (await getExternalStorageDirectory()).path;
    print(dir);
    File f = new File('$dir/$filename');
    return f;
  }
}
