import 'dart:async';

import 'package:flutter/material.dart';
import 'package:flutter_webview_plugin/flutter_webview_plugin.dart';

class WebView extends StatefulWidget {
  final String url;
  final String statusBarColor;
  final String title;
  final bool hideAppBar;
  final bool backForbid;

  WebView(
      {this.url,
      this.statusBarColor,
      this.title,
      this.hideAppBar,
      this.backForbid});

  @override
  _WebViewState createState() => _WebViewState();
}

class _WebViewState extends State<WebView> {
  final webViewReference = FlutterWebviewPlugin();

  StreamSubscription<String> _onUrlChangedStream;

  StreamSubscription<WebViewStateChanged> _webViewStateChangeStream;

  StreamSubscription<WebViewHttpError> _webViewErrorStream;

  @override
  void initState() {
    super.initState();
    webViewReference.close();
    _onUrlChangedStream = webViewReference.onUrlChanged.listen((String url) {});

    _webViewStateChangeStream = webViewReference.onStateChanged
        .listen((WebViewStateChanged webViewStateChanged) {
      switch (webViewStateChanged.type) {
        case WebViewState.startLoad:
          break;
        default:
          break;
      }
    });

    _webViewErrorStream =
        webViewReference.onHttpError.listen((WebViewHttpError error) {
      ///网络出现异常
    });
  }

  @override
  void dispose() {
    super.dispose();
    _onUrlChangedStream.cancel();
    webViewReference.dispose();
    _webViewStateChangeStream.cancel();
    _webViewErrorStream.cancel();
  }

  @override
  Widget build(BuildContext context) => Scaffold(
        body: Column(
          children: <Widget>[
            _appBar(null, null),
          ],
        ),
      );

  _appBar(Color backgroundColor, Color backButtonColor) {
    if (widget.hideAppBar ?? false) {
      return Container(
        color: backButtonColor,
        height: 30.0,
      );
    }

    return Container(
      ///借助 FractionallySizedBox 将Widget撑满屏幕的宽度 widthFactor :1
      child: FractionallySizedBox(
        widthFactor: 1,
        child: Stack(
          children: <Widget>[
            InkWell(
              child: Container(
                margin: EdgeInsets.only(left: 10),
                child: Icon(
                  Icons.close,
                  color: backButtonColor,
                  size: 26,
                ),
              ),
              onTap: () {},
            ),
            Positioned(
                left: 0,
                right: 0,
                child: Center(
                  child: Text(
                    widget.title ?? "",
                    style: TextStyle(color: backButtonColor,fontSize: 20.0),
                  ),
                ))
          ],
        ),
      ),
    );
  }
}
