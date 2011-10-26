package com.veegee.controllers;

import java.net.URLEncoder;

public class InformationMessage implements Message {

    private String mobile;
    private String timestamp;
    private String complaint_id;
    private String type;


    public InformationMessage(String mobile, String timestamp) {
        this.mobile = mobile;

        this.timestamp = timestamp;
    }

    public String getUrl() {
        return null;
    }

    public boolean sendToKannel(JsonObject jsonObject) {
        String encode = URLEncoder.encode(Constants.SMS_INFORMATION_RESPONSE);
        return Util.doGet(Constants.KANNEL_SEND_URL + mobile + "&text=" + encode, mobile) != null;

    }

	public String data() {
		return "";
	}


}

