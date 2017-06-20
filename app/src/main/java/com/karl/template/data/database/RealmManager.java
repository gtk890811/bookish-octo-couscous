/*
 * Copyright (c) AirBolt Pty Ltd - All Rights Reserved.
 */

package com.karl.template.data.database;

import com.Airbolt.TheAirBolt.model.businessModel.mAirbolt;
import com.Airbolt.TheAirBolt.model.businessModel.mHistory;

import java.util.List;

import javax.inject.Inject;
import javax.inject.Singleton;

import io.realm.Realm;
import io.realm.RealmChangeListener;
import io.realm.RealmModel;
import io.realm.RealmResults;

/**
 * Created by Karl Gao on 22/2/17.
 */

@Singleton
public class RealmManager {

    Realm realm;

    @Inject
    public RealmManager(Realm realm) {
        this.realm = realm;
    }

    public <E extends RealmModel> List<E> getUnmanagedCopy(Iterable<E> realmObjects){
        return realm.copyFromRealm(realmObjects);
    }

    public void clearRealm(){
        realm.executeTransaction(realm1 -> realm1.deleteAll());
    }

    /**
     * Airbolt
     */

    public void createOrUpdateAirboltList(List<mAirbolt> models) {
        realm.executeTransactionAsync(realm1 -> realm1.copyToRealmOrUpdate(models));
    }

    public void createOrUpdateAirbolt(mAirbolt model) {
        realm.executeTransactionAsync(realm1 -> realm1.copyToRealmOrUpdate(model));
    }

    public RealmResults<mAirbolt> getAirboltList() {
        return realm.where(mAirbolt.class).findAllAsync();
    }

    public RealmResults<mAirbolt> getOfflineUpdatedAirbolts() {
        return realm.where(mAirbolt.class)
                .equalTo("offline_status", 1)
                .or()
                .equalTo("offline_status", 2)
                .or()
                .equalTo("offline_status", 3)
                .findAllAsync();
    }

    public void removeAirbolt(mAirbolt model) {
        final RealmResults<mAirbolt> results = realm.where(mAirbolt.class).equalTo("deviceUUID", model.getDeviceUUID()).findAllAsync();

        results.addChangeListener(new RealmChangeListener<RealmResults<mAirbolt>>() {
            @Override
            public void onChange(final RealmResults<mAirbolt> element) {
                realm.executeTransaction(realm1 -> element.deleteAllFromRealm());
                element.removeChangeListener(this);
            }
        });
        results.load();
    }


    /**
     * History
     */

    public void addHistory(mHistory model) {
        realm.executeTransactionAsync(realm1 -> realm1.copyToRealm(model));
    }

    public void addHistoryList(List<mHistory> models) {
        realm.executeTransactionAsync(realm1 -> realm1.copyToRealm(models));
    }

    public RealmResults<mHistory> getHistoryList(String deviceUUID) {
        return realm.where(mHistory.class).equalTo("deviceUUID", deviceUUID).findAllAsync();
    }

    public RealmResults<mHistory> getOfflineUpdatedHistory() {
        return realm.where(mHistory.class).equalTo("offline_updated", true).findAllAsync();
    }

    public void removeHistoryForDevice(String deviceUUID) {
        final RealmResults<mHistory> results = realm.where(mHistory.class).equalTo("deviceUUID", deviceUUID).findAllAsync();

        results.addChangeListener(new RealmChangeListener<RealmResults<mHistory>>() {
            @Override
            public void onChange(final RealmResults<mHistory> element) {
                realm.executeTransaction(realm1 -> element.deleteAllFromRealm());
                element.removeChangeListener(this);
            }
        });
        results.load();
    }

    public void removeHistory(mHistory history) {
        //find the exact same record
        final RealmResults<mHistory> results = realm.where(mHistory.class)
                .equalTo("deviceUUID", history.getDeviceUUID())
                .equalTo("latitude", history.getLatitude())
                .equalTo("longitude", history.getLongitude())
                .equalTo("unlockType", history.getUnlockType())
                .equalTo("timeCreated", history.getTimeCreated())
                .findAllAsync();

        results.addChangeListener(new RealmChangeListener<RealmResults<mHistory>>() {
            @Override
            public void onChange(final RealmResults<mHistory> element) {
                realm.executeTransaction(realm1 -> element.deleteAllFromRealm());
                element.removeChangeListener(this);
            }
        });
        results.load();
    }
}
