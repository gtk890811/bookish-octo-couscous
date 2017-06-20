/*
 * Copyright (c) AirBolt Pty Ltd - All Rights Reserved.
 */

package com.karl.template.data.network;

import android.content.Context;
import android.support.v7.app.AlertDialog;

import com.Airbolt.TheAirBolt.AppConfig;
import com.Airbolt.TheAirBolt.R;
import com.Airbolt.TheAirBolt.view.activity.BaseActivity;


/**
 * Created by Karl Gao on 11/11/16.
 * <p>
 * This is UI layer callback for webservice
 * All view action regarding to general webservice callback should be here
 * e.g. if webservice fails, pop up an alert dialog to display general error message
 */

public abstract class RetrofitUICallback {

    private BaseActivity activity;
    private Context context;

    public RetrofitUICallback(BaseActivity activity) {
        this.context = activity;
        this.activity = activity;
    }

    public abstract void onSuccess();

    public void onError(String extra) {
        if (context != null) {
            activity.dismissPD();
            if (extra == null) {
                extra = context.getString(R.string.network_error_message);
            }
            try {
                if(AppConfig.ENABLE_LOG) {
                    new AlertDialog.Builder(context)
                            .setTitle(R.string.network_error_title)
                            .setMessage(extra)
                            .setPositiveButton(R.string.ok, null)
                            .show();
                } else {
                    new AlertDialog.Builder(context)
                            .setTitle(R.string.network_error_title)
                            .setMessage(R.string.network_error_message_prod)
                            .setPositiveButton(R.string.ok, null)
                            .show();
                }
            } catch (Exception e) {
                // in case the activity has been destroyed
                e.printStackTrace();
            }
        }
    }

    public void onFailure(String extra) {
        if (context != null) {
            activity.dismissPD();
            String error_msg = context.getString(R.string.network_failure_message) + "\n" + extra;
            try {
                if(AppConfig.ENABLE_LOG) {
                    new AlertDialog.Builder(context)
                            .setTitle(R.string.network_failure_title)
                            .setMessage(error_msg)
                            .setPositiveButton(R.string.ok, null)
                            .show();
                } else {
                    new AlertDialog.Builder(context)
                            .setTitle(R.string.network_failure_title)
                            .setMessage(R.string.network_failure_message_prod)
                            .setPositiveButton(R.string.ok, null)
                            .show();
                }
            } catch (Exception e) {
                // in case the activity has been destroyed
                e.printStackTrace();
            }
        }
    }
}
