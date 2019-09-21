import 'dart:io';

import 'package:flutter/material.dart';
import 'package:image_picker/image_picker.dart';

class PhotoAppPage extends StatefulWidget {
  @override
  _PhotoAppPageState createState() => _PhotoAppPageState();
}

class _PhotoAppPageState extends State<PhotoAppPage> {
  List<File> images = [];

  Future getImage(bool isTakePhoto) async {
    Navigator.pop(context);
    var image = await ImagePicker.pickImage(
        source: isTakePhoto ? ImageSource.camera : ImageSource.gallery);

    setState(() {
      images.add(image);
    });
  }

  @override
  Widget build(BuildContext context) {
    return Scaffold(
      appBar: AppBar(
        title: Text('Image Picker Example'),
      ),
      body: Center(
          child: Wrap(
        children: _genImages(),
      )),
      floatingActionButton: FloatingActionButton(
        onPressed: _showChooseImage,
        tooltip: 'Pick Image',
        child: Icon(Icons.add_a_photo),
      ),
    );
  }

  void _showChooseImage() {
    showModalBottomSheet(
        context: context,
        builder: (context) {
          return Container(
              height: 160,
              child: Column(
                children: <Widget>[
                  _itemImage(true, '拍照'),
                  _itemImage(false, '相册'),
                ],
              ));
        });
  }

  Widget _itemImage(bool isTakePhoto, String title) {
    return GestureDetector(
        child: ListTile(
      title: Text(title),
      leading: Icon(isTakePhoto ? Icons.camera_alt : Icons.photo_library),
      onTap: () {
        isTakePhoto ? getImage(true) : getImage(false);
      },
    ));
  }

  List<Widget> _genImages() {
    return images.map((file) {
      return Stack(
        children: <Widget>[
          ClipRRect(
            borderRadius: BorderRadius.circular(5),
            child: Image.file(file, width: 120, height: 90, fit: BoxFit.fill),
          ),
          Positioned(right: 5, top: 5, child: GestureDetector(
            child: ClipOval(
              child: Container(
                padding: EdgeInsets.all(3),
                decoration: BoxDecoration(color: Colors.black54),
                child: Icon(Icons.close,size: 18,color: Colors.white),
              ),
            ),
            onTap: (){
              setState(() {
                images.remove(file);
              });
            },
          ))
        ],
      );
    }).toList();
  }
}
