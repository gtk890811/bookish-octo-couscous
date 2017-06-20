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

import javax.inject.Inject;
import javax.inject.Singleton;

import retrofit2.Call;

/**
 * Created by Karl Gao on 4/11/16.
 */
@Singleton
public class WebServiceManager {

    private WebService basicAuth;
    private WebService tokenAuth;

    @Inject
    public WebServiceManager(@BasicAuth WebService basicAuth, @TokenAuth WebService tokenAuth) {
        this.basicAuth = basicAuth;
        this.tokenAuth = tokenAuth;
    }


    /**
     * Auth Collection
     */

    public Call<mUser> login(String username, String password) {
        JsonObject j = new JsonObject();

        j.addProperty("username", username);
        j.addProperty("password", password);

        return basicAuth.login(j);
    }

    public Call<mUser> signup(String email, String password, String name) {
        JsonObject j = new JsonObject();

        j.addProperty("email", email);
        j.addProperty("password", password);
        j.addProperty("name", name);

        return basicAuth.signup(j);
    }

    public Call<mMessage> forgot_password(String email) {
        JsonObject j = new JsonObject();

        j.addProperty("email", email);

        return basicAuth.forgot_password(j);
    }


    /**
     * Airbolt Collection
     */

    public Call<List<mAirbolt>> get_device_list(String user_id) {
        return basicAuth.get_device_list(user_id);
    }

    public Call<mAirbolt> add_device(mAirbolt device) {
        return basicAuth.add_device(device);
    }

    public Call<mAirbolt> mark_as_lost(mAirbolt device) {
        JsonObject j = new JsonObject();

        j.addProperty("markedByUsername", device.getMarkedByUsername());
        j.addProperty("markedByEmail", device.getMarkedByEmail());
        j.addProperty("markAsLost", device.getMarkAsLost());

        return basicAuth.mark_as_lost(device.getId(), j);
    }

    public Call<mAirbolt> update_device(String name, String pic, int alert, int tone, mAirbolt device) {
        JsonObject j = new JsonObject();

        j.addProperty("name", name == null ? device.getName() : name);
        j.addProperty("devicePicture", pic == null ? device.getDevicePicture() : pic);
        j.addProperty("alertLevel", alert == -1 ? device.getAlertLevel() : alert);
        j.addProperty("tone", tone == -1 ? device.getTone() : tone);

        return basicAuth.update_device(device.getId(), j);
    }

    public Call<mAirbolt> rekey_device(mAirbolt device) {
        JsonObject j = new JsonObject();

        j.addProperty("passcode", device.getPasscode());

        return basicAuth.rekey_device(device.getId(), j);
    }

    public Call<mAirbolt> update_device_location(mAirbolt device) {
        JsonObject j = new JsonObject();

        j.addProperty("latitude", device.getLatitude());
        j.addProperty("longitude", device.getLongitude());
        j.addProperty("lastSeenTime", device.getLastSeenTime());

        return basicAuth.update_device_location(device.getId(), j);
    }

    public Call<mMessage> delete_device(String device_id) {
        return basicAuth.delete_device(device_id);
    }

    public Call<mMessage> delete_shared_device(String share_id) {
        return basicAuth.delete_shared_device(share_id);
    }

    public Call<mMessage> update_crowdgps(String device_id, double lat, double lng, String lastSeenTime) {
        JsonObject j = new JsonObject();

        j.addProperty("latitude", lat);
        j.addProperty("longitude", lng);
        j.addProperty("lastSeenTime", lastSeenTime);

        return basicAuth.update_crowdgps(device_id, j);
    }

    public Call<mOwnership> check_if_owner(String user_id, String device_uuid) {
        return basicAuth.check_if_owner(user_id, device_uuid);
    }


    /**
     * Device History Collection
     */

    public Call<List<mHistory>> get_history_list(String device_uuid) {
        return basicAuth.get_history_list(device_uuid);
    }

    public Call<mHistory> add_history(mHistory history) {
        return basicAuth.add_history(history);
    }


    /**
     * Sharer Collection
     */

    public Call<List<mSharer>> get_sharer_list(String device_uuid) {
        return basicAuth.get_sharer_list(device_uuid);
    }

    public Call<Void> add_sharer(mSharer sharer) {
        return basicAuth.add_sharer(sharer);
    }

    public Call<mSharer> update_sharer(mSharer sharer) {
        return basicAuth.update_sharer(sharer.getId(), sharer);
    }

    public Call<mMessage> delete_sharer(String sharer_id) {
        return basicAuth.delete_sharer(sharer_id);
    }


    /**
     * Firmware Collection
     */

    public Call<List<mFirmware>> get_firmware() {
        return basicAuth.get_firmware();
    }

}
