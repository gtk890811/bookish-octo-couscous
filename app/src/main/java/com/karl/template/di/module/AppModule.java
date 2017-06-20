/*
 * Copyright (c) AirBolt Pty Ltd - All Rights Reserved.
 */

package com.karl.template.di.module;

import android.app.Application;
import android.content.Context;

import com.Airbolt.TheAirBolt.App;
import com.Airbolt.TheAirBolt.dagger.qualifier.ApplicationContext;

import dagger.Module;
import dagger.Provides;

/**
 * Created by macmini6 on 8/09/2016.
 */
@Module
public class AppModule {

    private final App mApp;

    public AppModule(App application) {
        mApp = application;
    }

    @Provides
    Application provideApplication() {
        return mApp;
    }

    @Provides
    @ApplicationContext
    Context provideContext() {
        return mApp.getApplicationContext();
    }

}
