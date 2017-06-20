/*
 * Copyright (c) AirBolt Pty Ltd - All Rights Reserved.
 */

package com.karl.template.data.network;

import com.Airbolt.TheAirBolt.model.apiModel.mError;
import com.google.gson.Gson;

import java.io.IOException;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

/**
 * Created by Karl Gao on 10/11/16.
 *
 * This is business logic layer callback for webservice
 * All business logic regarding to general webservice callback should be here
 * e.g. if webservice fails, store call info in the database
 *
 * This class also redirect the call to #UiCallback
 */

public abstract class RetrofitCallback<T> implements Callback<T> {

    private RetrofitUICallback uiCallback;

    public RetrofitCallback(RetrofitUICallback uiCallback) {
        this.uiCallback = uiCallback;
    }

    //handle 404, 500 error/ separate class to handle 404 500
    @Override
    public void onResponse(Call<T> call, Response<T> response) {
        if (!response.isSuccessful()) {
            onError(call, response);
        } else {
            onSuccess(call, response);
            if(uiCallback != null) {
                uiCallback.onSuccess();
            }
        }
    }

    public abstract void onSuccess(Call<T> call, Response<T> response);

    public void onError(Call<T> call, Response<T> response) {
        String error_msg = null;
        try {
            mError r = new Gson().fromJson(response.errorBody().string(), mError.class);
            error_msg = r.getMessage();
        } catch (IOException e) {
            e.printStackTrace();
        }
        if(uiCallback != null) {
            uiCallback.onError(error_msg);
        }
    }

    @Override
    public void onFailure(Call<T> call, Throwable t) {
        t.printStackTrace();
        if(uiCallback != null) {
            uiCallback.onFailure(t.getMessage());
        }
    }

}
