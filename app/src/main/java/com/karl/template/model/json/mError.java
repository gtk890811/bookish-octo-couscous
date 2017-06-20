/*
 * Copyright (c) AirBolt Pty Ltd - All Rights Reserved.
 */

package com.karl.template.model.json;

/**
 * Created by macmini6 on 13/04/2016.
 */
public class mError {

    int statusCode;
    String error;
    String message;

    public int getStatusCode() {
        return statusCode;
    }

    public void setStatusCode(int statusCode) {
        this.statusCode = statusCode;
    }

    public String getError() {
        return error;
    }

    public void setError(String error) {
        this.error = error;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }
}
