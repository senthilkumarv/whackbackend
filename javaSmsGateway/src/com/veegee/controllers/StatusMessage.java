package com.veegee.controllers;

public class StatusMessage {

    private String mobile;
    private String complaint_id;
    private String type;


    public StatusMessage(String text, String mobile, String timestamp) {
        String[] smsValues = text.split(Constants.SEPARATOR);
        this.mobile = mobile;

        try {
            type = smsValues[0];
            complaint_id = smsValues[1];
        } catch (Exception e) {
            Util.doGetWithParseJsonResponse(Constants.KANNEL_SEND_URL + mobile + "&text=" + Constants.USER_ERROR_MESSAGE, mobile);
        }

    }

    public String getUrl() {
        return Constants.STATUS_URL + "?mobile=" + mobile + "&reference_id=" + complaint_id;
    }


}

