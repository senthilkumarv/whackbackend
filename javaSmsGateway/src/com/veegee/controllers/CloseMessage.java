package com.veegee.controllers;

import java.net.URLEncoder;

public class CloseMessage implements Message {

    private String mobile;
    private String complaint_id;
    private String type;


    public CloseMessage(String text, String mobile, String timestamp) {
        String[] smsValues = text.split(Constants.SEPARATOR);
        this.mobile = mobile;

        try {
            type = smsValues[0];
            complaint_id = smsValues[1];
        } catch (Exception e) {
            Util.doGet(Constants.KANNEL_SEND_URL + mobile + "&text=" + URLEncoder.encode(Constants.USER_CLOSE_ERROR_MESSAGE), mobile);
        }

    }

    public String getUrl() {
        return Constants.CLOSE_URL;
    }


    public boolean sendToKannel(JsonObject jsonObject) {
        String smsCloseString;
        if (jsonObject.getReference_id().equals("NA"))
            smsCloseString = "Sorry, request not processed. Invalid reference Id";
        else
            smsCloseString = URLEncoder.encode(Constants.SMS_CLOSE_RESPONSE);

        return Util.doGet(Constants.KANNEL_SEND_URL + mobile + "&text=" + smsCloseString, mobile) != null;

    }

	public String data() {
		return "{reference_id:" + complaint_id + ",mobile:" + mobile + "}";
	}


}

