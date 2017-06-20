package com.karl.template.util;

import android.util.Log;

import com.karl.mvvmtemplate.AppConfig;

/**
 * Created by macmini6 on 14/09/2016.
 */
public class Logs {

    public static void d(String TAG, String msg) {
        if (AppConfig.ENABLE_LOG) {
            largeLog(TAG, msg);
        }
    }

    public static void d(String msg) {
        if (AppConfig.ENABLE_LOG) {
            largeLog(AppConfig.GENERAL_TAG, msg);
        }
    }

    public static void d(String TAG, String msg, Throwable e) {
        if (AppConfig.ENABLE_LOG) {
            Log.d(TAG, msg, e);
        }
    }

    public static void largeLog(String tag, String msg) {
        if (msg.length() > 4000) {
            Log.d(tag, msg.substring(0, 4000));
            largeLog(tag, msg.substring(4000));
        } else {
            Log.d(tag, msg);
        }
    }
}

