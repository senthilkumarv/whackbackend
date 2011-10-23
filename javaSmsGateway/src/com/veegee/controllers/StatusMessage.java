package com.veegee.controllers;

import java.net.URLEncoder;

public class StatusMessage implements Message {

    private String mobile;
    private String complaint_id;
    private String type;


    public StatusMessage(String text, String mobile, String timestamp) {
        String[] smsValues = text.split(Constants.SEPARATOR);
        this.mobile = mobile;

        try {
            type = smsValues[0];
            complaint_id = smsValues.length >1 ? smsValues[1]:null;
        } catch (Exception e) {
            Util.doGetWithParseJsonResponse(Constants.KANNEL_SEND_URL + mobile + "&text=" + URLEncoder.encode(Constants.USER_STATUS_ERROR_MESSAGE), mobile);
        }

    }

    public String getUrl() {
        String referenceIdString = complaint_id!=null ?"&reference_id=" + complaint_id:"";
        return Constants.STATUS_URL + "?mobile=" + mobile + referenceIdString;
    }

    public boolean sendToKannel(JsonObject jsonObject) {

        String smsStatusString;
        if (jsonObject.getReference_id().equals("NA"))
            smsStatusString = "Sorry, request not processed. Invalid reference Id";
        else
            smsStatusString = String.format(Constants.SMS_STATUS_RESPONSE, jsonObject.getReference_id(), jsonObject.getStatus().toUpperCase());

        return Util.doGet(Constants.KANNEL_SEND_URL + mobile + "&text=" + URLEncoder.encode(smsStatusString), mobile) != null;

    }


}

