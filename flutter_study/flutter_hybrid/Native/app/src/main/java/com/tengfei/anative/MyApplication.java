package com.tengfei.anative;

import android.app.Application;

import io.flutter.embedding.engine.FlutterEngine;
import io.flutter.embedding.engine.FlutterEngineCache;
import io.flutter.embedding.engine.dart.DartExecutor;

/**
 * @author 滕飞
 * date 2020/7/28 10:27 AM
 * email arrayadapter.cn@outlook.com
 * description  代码在MyApplication中初始化了一个id为CACHED_ENGINE_ID 的FlutterEngine，
 * 然后我们在需要打开Flutter模块时告诉Flutter我们缓存的id即可
 */
public class MyApplication extends Application {

    public static final String CACHED_ENGINE_ID = "MY_CACHED_ENGINE_ID";

    @Override
    public void onCreate() {
        super.onCreate();
        //在MyApplication中预先初始化Flutter引擎以提升Flutter页面打开速度
        //FlutterEngine flutterEngine = new FlutterEngine(this);

        // Start executing Dart code to pre-warm the FlutterEngine.
        //flutterEngine.getDartExecutor().executeDartEntrypoint(DartExecutor.DartEntrypoint.createDefault());
        // Cache the FlutterEngine to be used by FlutterActivity.
        //FlutterEngineCache.getInstance().put(CACHED_ENGINE_ID, flutterEngine);


    }
}
