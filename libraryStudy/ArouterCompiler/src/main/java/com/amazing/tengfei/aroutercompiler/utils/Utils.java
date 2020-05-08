package com.amazing.tengfei.aroutercompiler.utils;

import java.util.Collection;
import java.util.Map;

/**
 * @author 飞一般的感觉
 * date 2020/5/8 11:29 AM
 * email arrayadapter.cn@gmail.com
 * description
 */
public class Utils {
    public static boolean isEmpty(CharSequence cs) {
        return cs == null || cs.length() == 0;
    }

    public static boolean isEmpty(Collection<?> coll) {
        return coll == null || coll.isEmpty();
    }

    public static boolean isEmpty(final Map<?, ?> map) {
        return map == null || map.isEmpty();
    }
}
