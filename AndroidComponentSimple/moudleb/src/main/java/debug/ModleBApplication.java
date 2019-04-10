package debug;

import android.app.Application;
import android.util.Log;

/**
 * @author tengfei
 * date 2019/4/10 12:23 PM
 * email tengfeigo@outlook.com
 * description
 */
public class ModleBApplication extends Application {

    @Override
    public void onCreate() {
        super.onCreate();
        Log.i("ModleBApplication","ModleBApplication");
    }
}
