/*
 * Copyright (c) AirBolt Pty Ltd - All Rights Reserved.
 */

package com.karl.template.di.module;

import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;
import io.realm.Realm;

/**
 * Created by Karl Gao on 8/11/16.
 */
@Module
public class RealmModule {

    @Provides
    @Singleton
    Realm provideRealm(){
        return Realm.getDefaultInstance();
    }

}
