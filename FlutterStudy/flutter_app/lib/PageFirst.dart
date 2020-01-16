

import 'package:flutter/material.dart';

class PageFirst extends StatelessWidget{
  @override
  Widget build(BuildContext context) {

    return pageFirstWidget(context);
  }

  Widget pageFirstWidget(BuildContext context){
    return Scaffold(
      appBar: AppBar(title: Text('first page'),),
      body: Container(
        child: RaisedButton(onPressed:(){
          Navigator.push(context,MaterialPageRoute(builder: (context)=> PageSecond()));
        } ,child: Text('skip to second page')),
      ),
    );
  }


}


class PageSecond extends StatelessWidget{
  @override
  Widget build(BuildContext context) {

    return Scaffold(
      appBar: AppBar(title: Text('PageSecond'),),
      body: Container(
        child: RaisedButton(onPressed: ()=>{
          Navigator.pop(context)
        },child: Text('skip to first page'),),
      ),
    );
  }

}

