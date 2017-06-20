/*
 * Copyright (c) AirBolt Pty Ltd - All Rights Reserved.
 */

package com.karl.template.di.component;

import com.Airbolt.TheAirBolt.ble.BTHelper;
import com.Airbolt.TheAirBolt.dagger.module.AppModule;
import com.Airbolt.TheAirBolt.domain.PreferenceModule;
import com.Airbolt.TheAirBolt.domain.PrefsKeysPrefs;
import com.Airbolt.TheAirBolt.domain.RealmManager;
import com.Airbolt.TheAirBolt.domain.RealmModule;
import com.Airbolt.TheAirBolt.network.RetrofitModule;
import com.Airbolt.TheAirBolt.network.WebServiceManager;

import javax.inject.Singleton;

import dagger.Component;

/**
 * Created by Karl Gao.
 */

@Singleton
@Component(modules = {AppModule.class, PreferenceModule.class, RetrofitModule.class, RealmModule.class})
public interface AppComponent {

    WebServiceManager provideWebServiceManager();
    PrefsKeysPrefs providePrefs();
    RealmManager provideRealm();
    BTHelper provideBTHelper();
}
