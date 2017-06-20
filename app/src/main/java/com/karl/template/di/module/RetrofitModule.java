/*
 * Copyright (c) AirBolt Pty Ltd - All Rights Reserved.
 */

package com.karl.template.di.module;

import com.Airbolt.TheAirBolt.AppConfig;
import com.Airbolt.TheAirBolt.domain.PrefsKeysPrefs;
import com.google.gson.Gson;

import java.util.concurrent.TimeUnit;

import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;
import okhttp3.Interceptor;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.logging.HttpLoggingInterceptor;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

/**
 * Created by Karl Gao on 8/09/2016.
 */
@Module
public class RetrofitModule {

    private Retrofit basic_manager;
    private Retrofit jwt_manager;

    @Provides
    @Singleton
    @BasicAuth
    WebService provideBasicAuth() {
        if (basic_manager == null) {
            // Define the interceptor, add authentication headers
            Interceptor interceptor = chain -> {
                Request newRequest = chain.request().newBuilder().addHeader("content-type", "application/json").build();
                return chain.proceed(newRequest);
            };

            //Add logging interceptor
            HttpLoggingInterceptor logging = new HttpLoggingInterceptor();
            logging.setLevel(HttpLoggingInterceptor.Level.BODY);

            // Add the interceptor to OkHttpClient
            OkHttpClient.Builder builder = new OkHttpClient.Builder();
            builder.interceptors().add(interceptor);
            builder.interceptors().add(logging);
            OkHttpClient client = builder
                    .readTimeout(15, TimeUnit.SECONDS)
                    .connectTimeout(15, TimeUnit.SECONDS)
                    .build();

            // Set the custom client when building adapter
            basic_manager = new Retrofit.Builder()
                    .baseUrl(AppConfig.BASE_URL)
                    .addConverterFactory(GsonConverterFactory.create(DefaultConverter()))
                    .client(client)
                    .build();
        }
        return basic_manager.create(WebService.class);
    }

    @Provides
    @Singleton
    @TokenAuth
    WebService provideTokenAuth(PrefsKeysPrefs prefs) {
        if (jwt_manager == null) {
            // Define the interceptor, add authentication headers
            Interceptor interceptor = chain -> {
                Request newRequest = chain.request().newBuilder()
                        .addHeader("content-type", "application/json")
                        .addHeader("authorization", "Bearer " + prefs.getAccessToken())
                        .build();
                return chain.proceed(newRequest);
            };

            //Add logging interceptor
            HttpLoggingInterceptor logging = new HttpLoggingInterceptor();
            logging.setLevel(HttpLoggingInterceptor.Level.BODY);

            // Add the interceptor to OkHttpClient
            OkHttpClient.Builder builder = new OkHttpClient.Builder();
            builder.interceptors().add(interceptor);
            builder.interceptors().add(logging);
            OkHttpClient client = builder
                    .readTimeout(15, TimeUnit.SECONDS)
                    .connectTimeout(15, TimeUnit.SECONDS)
                    .build();

            // Set the custom client when building adapter
            jwt_manager = new Retrofit.Builder()
                    .baseUrl(AppConfig.BASE_URL)
                    .addConverterFactory(GsonConverterFactory.create(DefaultConverter()))
                    .client(client)
                    .build();
        }
        return jwt_manager.create(WebService.class);
    }

    //Realm converter
//    public static Gson RealmConverter() {
//        Type intToken = new TypeToken<RealmList<RealmInt>>() {
//        }.getType();
//        Type strToken = new TypeToken<RealmList<RealmStr>>() {
//        }.getType();
//        return new GsonBuilder()
//                .setExclusionStrategies(new ExclusionStrategy() {
//                    @Override
//                    public boolean shouldSkipField(FieldAttributes f) {
//                        return f.getDeclaringClass().equals(RealmObject.class) || f.getAnnotation(Exclude.class) != null;
//                    }
//
//                    @Override
//                    public boolean shouldSkipClass(Class<?> clazz) {
//                        return false;
//                    }
//                })
//                .registerTypeAdapter(intToken, new TypeAdapter<RealmList<RealmInt>>() {
//
//                    @Override
//                    public void write(JsonWriter out, RealmList<RealmInt> value) throws IOException {
//                        if (value != null) {
//                            out.beginArray();
//                            for (int i = 0; i < value.size(); i++) {
//                                out.value(value.get(i).getVal());
//                            }
//                            out.endArray();
//                        } else {
//                            out.nullValue();
//                        }
//                    }
//
//                    @Override
//                    public RealmList<RealmInt> read(JsonReader in) throws IOException {
//                        RealmList<RealmInt> list = new RealmList<>();
//                        in.beginArray();
//                        while (in.hasNext()) {
//                            list.add(new RealmInt(in.nextInt()));
//                        }
//                        in.endArray();
//                        return list;
//                    }
//                })
//                .registerTypeAdapter(strToken, new TypeAdapter<RealmList<RealmStr>>() {
//
//                    @Override
//                    public void write(JsonWriter out, RealmList<RealmStr> value) throws IOException {
//                        if (value != null) {
//                            out.beginArray();
//                            for (int i = 0; i < value.size(); i++) {
//                                out.value(value.get(i).getStr());
//                            }
//                            out.endArray();
//                        } else {
//                            out.nullValue();
//                        }
//                    }
//
//                    @Override
//                    public RealmList<RealmStr> read(JsonReader in) throws IOException {
//                        RealmList<RealmStr> list = new RealmList<>();
//                        in.beginArray();
//                        while (in.hasNext()) {
//                            list.add(new RealmStr(in.nextString()));
//                        }
//                        in.endArray();
//                        return list;
//                    }
//                })
//                .create();
//    }

    public static Gson DefaultConverter() {
        return new Gson();
    }
}
