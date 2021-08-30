package simple.easy.glide.work.net;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.net.ConnectivityManager;
import android.os.Build;
import android.widget.Toast;

import androidx.annotation.RequiresApi;

import java.util.Objects;

import simple.easy.glide.util.Util;

public class NetworkStateReceive extends BroadcastReceiver {

    @RequiresApi(api = Build.VERSION_CODES.KITKAT)
    @Override
    public void onReceive(Context context, Intent intent) {
        if (Objects.equals(intent.getAction(), ConnectivityManager.EXTRA_NO_CONNECTIVITY)) {
                if (!Util.isWifiConnected()){
                    Toast.makeText(context, "网络不给力...", Toast.LENGTH_SHORT).show();

                }
        }
    }
}
