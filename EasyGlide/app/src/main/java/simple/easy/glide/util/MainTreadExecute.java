package simple.easy.glide.util;

import android.os.Handler;
import android.os.Looper;

import java.util.concurrent.Executor;

public class MainTreadExecute implements Executor {

    private Handler handler = new Handler(Looper.getMainLooper());

    @Override
    public void execute(Runnable command) {
        handler.post(command);
    }
}
