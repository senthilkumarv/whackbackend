package com.veegee.controllers;

import java.net.URLEncoder;

public class SmsMessage implements Message{

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
            Util.doGet(Constants.KANNEL_SEND_URL + mobile + "&text=" + URLEncoder.encode(Constants.USER_COMPLAINT_ERROR_MESSAGE), mobile);
        }

    }

    public String getUrl() {
        return Constants.CREATE_URL + "?mobile=" + mobile + "&location=" + location + "&name=" + name + "&type=" + type;
    }

    public boolean sendToKannel(JsonObject jsonObject) {
        String smsComplaintString = String.format(Constants.SMS_COMPLAINT_RESPONSE,jsonObject.getReference_id());
        return Util.doGet(Constants.KANNEL_SEND_URL + mobile + "&text=" + URLEncoder.encode(smsComplaintString), mobile)!=null;

    }


}

