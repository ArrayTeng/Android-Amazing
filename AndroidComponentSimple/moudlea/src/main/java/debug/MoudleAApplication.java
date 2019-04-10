package debug;

import android.app.Application;
import android.util.Log;

/**
 * @author tengfei
 * date 2019/4/10 12:25 PM
 * email tengfeigo@outlook.com
 * description
 */
public class MoudleAApplication extends Application {

    @Override
    public void onCreate() {
        super.onCreate();
        Log.i("MoudleAApplication","MoudleAApplication");
    }
}
