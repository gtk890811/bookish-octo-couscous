/*
 * Copyright (c) AirBolt Pty Ltd - All Rights Reserved.
 */

package com.karl.template.data.preference;

import org.jraf.android.prefs.DefaultBoolean;
import org.jraf.android.prefs.DefaultString;
import org.jraf.android.prefs.Prefs;

/**
 * Created by Karl Gao on 8/11/16.
 */

@Prefs
public class PrefsKeys {

    @DefaultString("")
    String accessToken;


    /**
     * App Behavior Control
     */

    @DefaultBoolean(false)
    Boolean isLoggedIn;

    @DefaultBoolean(true)
    Boolean askPermission;

    @DefaultBoolean(true)
    Boolean showDeviceIntro;


    /**
     * User Info
     */

    @DefaultString("")
    String userId;

    @DefaultString("")
    String userName;

    @DefaultString("")
    String userEmail;

    @DefaultString("")
    String lastUserEmail;

    /**
     * Global App Setting
     */

    @DefaultBoolean(true)
    Boolean crowdLocationTracking;

    @DefaultBoolean(false)
    Boolean highSecurityLevel;

    @DefaultBoolean(false)
    Boolean useFingerprint;

    @DefaultBoolean(true)
    Boolean phoneAlert;

    @DefaultBoolean(true)
    Boolean airboltAlert;

}
