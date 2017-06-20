package com.karl.template;

import android.app.Application;

import com.karl.mvvmtemplate.dagger.component.AppComponent;
import com.karl.mvvmtemplate.dagger.component.DaggerAppComponent;
import com.karl.mvvmtemplate.dagger.module.AppModule;

/**
 * Created by macmini6 on 8/09/2016.
 */
public class App extends Application {

    private static App app;
    private AppComponent appComponent;

    @Override
    public void onCreate() {
        super.onCreate();

        app = this;

//        Realm.init(this);
//        RealmConfiguration realmConfiguration = new RealmConfiguration.Builder()
//                .name("courierpal.realm")
//                .schemaVersion(1)
//                .deleteRealmIfMigrationNeeded()
//                .build();
//        Realm.setDefaultConfiguration(realmConfiguration);
    }

    public static App get() {
        return app;
    }

    public AppComponent getComponent() {
        if (appComponent == null) {
            appComponent = DaggerAppComponent.builder()
                    .appModule(new AppModule(this))
                    .build();
        }
        return appComponent;
    }

}
