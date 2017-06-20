/*
 * Copyright (c) AirBolt Pty Ltd - All Rights Reserved.
 */

package com.karl.template.di.module;

import android.content.Context;

import com.Airbolt.TheAirBolt.dagger.qualifier.ApplicationContext;

import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;

/**
 * Created by Karl Gao on 8/11/16.
 */
@Module
public class PreferenceModule {

    @Provides
    @Singleton
    PrefsKeysPrefs providePrefs(@ApplicationContext Context context){
        return PrefsKeysPrefs.get(context);
    }

}
