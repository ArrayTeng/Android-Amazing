package com.tengfei.anative.plugin;

import android.app.Activity;
import android.util.Log;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import io.flutter.plugin.common.BasicMessageChannel;
import io.flutter.plugin.common.BinaryMessenger;
import io.flutter.plugin.common.StringCodec;

/**
 * @author 滕飞
 * date 2020/7/29 8:29 PM
 * email arrayadapter.cn@outlook.com
 * description
 */
public class BasicMessageChannelPlugin implements BasicMessageChannel.MessageHandler<String>, BasicMessageChannel.Reply<String> {

    private static final String TAG = "BasicMessageChannelPlugin_TAG";

    private final BasicMessageChannel<String> basicMessageChannel;

    private Activity activity;

    public static BasicMessageChannelPlugin registerWith(BinaryMessenger binaryMessenger, Activity activity) {
        return new BasicMessageChannelPlugin(binaryMessenger, activity);
    }

    private BasicMessageChannelPlugin(BinaryMessenger binaryMessenger, Activity activity) {
        this.activity = activity;
        this.basicMessageChannel = new BasicMessageChannel<>(binaryMessenger, "BasicMessageChannelPlugin", StringCodec.INSTANCE);
        //如果想要接收来自dart的消息，调用 setMessageHandler 来设置一个消息处理器
        basicMessageChannel.setMessageHandler(this);
    }

    /**
     * 用于接收消息
     * @param message 传递过来的消息
     * @param reply 回复此消息的回调函数
     */
    @Override
    public void onMessage(@Nullable String message, @NonNull BasicMessageChannel.Reply<String> reply) {
        //收到消息后可以通过reply回复
        reply.reply("BasicMessageChannelPlugin收到消息"+message);

        Log.e(TAG, "onMessage: dart传递过来的信息"+message);
    }

    public void sendMessage(String message,@NonNull BasicMessageChannel.Reply<String> reply){
        basicMessageChannel.send(message,reply);
    }

    @Override
    public void reply(@Nullable String reply) {

    }
}
