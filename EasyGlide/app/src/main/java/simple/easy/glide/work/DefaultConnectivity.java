package simple.easy.glide.work;

import android.content.Context;
import android.content.IntentFilter;
import android.net.ConnectivityManager;

import simple.easy.glide.binding.inter.LifecycleListener;
import simple.easy.glide.work.net.NetworkStateReceive;


public class DefaultConnectivity implements LifecycleListener {

    private Context mContext;
    private NetworkStateReceive networkStateReceive;

    public DefaultConnectivity(Context mContext) {
        this.mContext = mContext;
    }

    @Override
    public void onStart() {
        //页面可见的时候注册广播监听器监听网络的变化
        networkStateReceive = new NetworkStateReceive();
        IntentFilter intentFilter = new IntentFilter(ConnectivityManager.EXTRA_NO_CONNECTIVITY);
        this.mContext.registerReceiver(networkStateReceive, intentFilter);
    }

    @Override
    public void onStop() {
        //页面不可见的时候注销广播监听器
        if (networkStateReceive != null) {
            this.mContext.unregisterReceiver(networkStateReceive);
        }
    }

    @Override
    public void onDestroy() {

    }
}
