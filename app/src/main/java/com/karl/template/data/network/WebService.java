/*
 * Copyright (c) AirBolt Pty Ltd - All Rights Reserved.
 */

package com.karl.template.data.network;

import com.Airbolt.TheAirBolt.model.apiModel.mMessage;
import com.Airbolt.TheAirBolt.model.businessModel.mAirbolt;
import com.Airbolt.TheAirBolt.model.businessModel.mFirmware;
import com.Airbolt.TheAirBolt.model.businessModel.mHistory;
import com.Airbolt.TheAirBolt.model.businessModel.mOwnership;
import com.Airbolt.TheAirBolt.model.businessModel.mSharer;
import com.Airbolt.TheAirBolt.model.businessModel.mUser;
import com.google.gson.JsonObject;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.DELETE;
import retrofit2.http.GET;
import retrofit2.http.POST;
import retrofit2.http.PUT;
import retrofit2.http.Path;

/**
 * Created by Karl Gao on 16/09/2016.
 */
public interface WebService {

    /**
     * Auth Collection
     */

    @POST("login")
    Call<mUser> login(@Body JsonObject body);

    @POST("signup")
    Call<mUser> signup(@Body JsonObject body);

    @POST("login/forgot")
    Call<mMessage> forgot_password(@Body JsonObject body);


    /**
     * Airbolt Collection
     */

    @GET("devices/find/{user_id}")
    Call<List<mAirbolt>> get_device_list(@Path("user_id") String user_id);

    @POST("devices/insert")
    Call<mAirbolt> add_device(@Body mAirbolt device);

    @PUT("devices/{device_id}")
    Call<mAirbolt> mark_as_lost(@Path("device_id") String device_id, @Body JsonObject body);

    @PUT("devices/{device_id}")
    Call<mAirbolt> update_device(@Path("device_id") String device_id, @Body JsonObject body);

    @PUT("devices/{device_id}")
    Call<mAirbolt> rekey_device(@Path("device_id") String device_id, @Body JsonObject body);

    @PUT("devices/{device_id}")
    Call<mAirbolt> update_device_location(@Path("device_id") String device_id, @Body JsonObject body);

    @DELETE("devices/{device_id}")
    Call<mMessage> delete_device(@Path("device_id") String device_id);

    @DELETE("share-device/{sharer_id}")
    Call<mMessage> delete_shared_device(@Path("sharer_id") String sharer_id);

    @PUT("devices/crowd-gps/{device_id}")
    Call<mMessage> update_crowdgps(@Path("device_id") String device_id, @Body JsonObject body);

    @GET("devices/check-owner/{user_id}/{device_uuid}")
    Call<mOwnership> check_if_owner(@Path("user_id") String user_id, @Path("device_uuid") String device_uuid);


    /**
     * Device History Collection
     */

    @GET("history/find/device/{device_uuid}")
    Call<List<mHistory>> get_history_list(@Path("device_uuid") String device_uuid);

    @POST("history/insert")
    Call<mHistory> add_history(@Body mHistory history);


    /**
     * Sharer Collection
     */

    @GET("share-device/find/device/{device_uuid}")
    Call<List<mSharer>> get_sharer_list(@Path("device_uuid") String device_uuid);

    @POST("share-device/insert")
    Call<Void> add_sharer(@Body mSharer sharer);

    @PUT("share-device/{sharer_id}")
    Call<mSharer> update_sharer(@Path("sharer_id") String sharer_id, @Body mSharer sharer);

    @DELETE("share-device/{sharer_id}")
    Call<mMessage> delete_sharer(@Path("sharer_id") String sharer_id);


    /**
     * Firmware Collection
     */

    @GET("fw_update")
    Call<List<mFirmware>> get_firmware();
}
