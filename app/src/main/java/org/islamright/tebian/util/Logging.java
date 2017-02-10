package org.islamright.tebian.util;

import android.util.Log;

/**
 * Created by AlaaAlShaikh on 04/03/15.
 */
public class Logging {
    /**
     * class for log
     */


    public static void e(Class aClass, String log) {
        Log.e(aClass.getSimpleName(), log);
    }

    public static void e(Class aClass, Exception e) {
        Log.e(aClass.getSimpleName(), e.toString());
    }

    public static void i(Class aClass, String log) {
        Log.i(aClass.getSimpleName() , log);
    }

    public static void d(Class aClass, String log) {
        Log.d(aClass.getSimpleName() , log);
    }
}
