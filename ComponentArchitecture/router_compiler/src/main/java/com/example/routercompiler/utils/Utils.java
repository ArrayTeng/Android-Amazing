package com.example.routercompiler.utils;

import java.util.Collection;
import java.util.Map;

/**
 * @author tengfei
 * date 2019-11-03 22:58
 * email tengfeigo@outlook.com
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
