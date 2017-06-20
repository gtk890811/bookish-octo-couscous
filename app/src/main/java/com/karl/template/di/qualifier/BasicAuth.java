/*
 * Copyright (c) AirBolt Pty Ltd - All Rights Reserved.
 */

package com.karl.template.di.qualifier;

import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;

import javax.inject.Qualifier;

/**
 * Created by Karl Gao on 8/11/16.
 */

@Qualifier
@Retention(RetentionPolicy.RUNTIME)
public @interface BasicAuth {
}
