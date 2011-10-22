package com.veegee.controllers;

import org.apache.http.HttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.DefaultHttpClient;

public class SmsMessage {

    private String name;
    private String mobile;
    private String location;
    private String type;
    private String timestamp;


    public SmsMessage(String text, String mobile, String timestamp) {
        String[] smsValues = text.split(Constants.SEPARATOR);
        this.timestamp = timestamp;
        this.mobile = mobile;

        try {
            type = smsValues[0];
            location = smsValues[1];
            name = smsValues[2];
        } catch (Exception e) {
            Util.doGetWithParseJsonResponse(Constants.KANNEL_SEND_URL + mobile + "&text=" + Constants.USER_ERROR_MESSAGE, mobile);
        }

    }

    public String getUrl() {
        return Constants.CREATE_URL + "?mobile=" + mobile + "&location=" + location + "&name=" + name + "&type=" + type;
    }


}

