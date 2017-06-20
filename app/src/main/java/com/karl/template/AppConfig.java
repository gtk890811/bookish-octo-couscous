package com.karl.template;

/**
 * Created by macmini6 on 8/09/2016.
 */
public class AppConfig {

    //Base URL
    public static final String BASE_URL = BuildConfig.baseUrl;

    //App Behavior Control
    public static final boolean ENABLE_LOG = BuildConfig.debug;
    public static final boolean FILE_STORAGE = BuildConfig.debug; // opposite of External storage

    public static final boolean AUTO_LOGIN = false;
    public static final boolean ENABLE_RECYCLER_STAGGERED_LOADING = true;

    public static final boolean IS_EMULATOR = false;


    //Log Tag
    public static final String GENERAL_TAG = "AndroidMVVM";


    //Image
    public static final String IMAGE_DIR_NAME = "AirBolt";
    public static final int IMAGE_SIZE = 200;
    public static final int IMAGE_COMPRESS_QUALITY = 80;


    //JWT Signing Key
    public static final String JWT_KEY = "secret";


    //s3 credentials
    public static final String ACCESS_KEY_ID = "";
    public static final String SECRET_KEY = "";
    public static final String IMAGE_BUCKET = "";
    public static final String S3_DOWNLOAD_URL = "";


    //list pagination
    public static final int DEFAULT_PER_PAGE = 10;
    public static final int VISIBLE_THRESHOLD = 0;
}
