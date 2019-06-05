package com.example.butterknife;

import android.app.Activity;
import android.view.View;


/**
 * @author tengfei
 * date 2019/5/14 10:08 PM
 * email tengfeigo@outlook.com
 * description
 */
public class Utils {

    public static <T extends View> T findViewById(Activity activity, int viewId) {
        return activity.findViewById(viewId);
    }
}
