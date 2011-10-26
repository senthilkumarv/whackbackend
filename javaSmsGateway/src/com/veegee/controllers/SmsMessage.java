package com.veegee.controllers;

import java.net.URLEncoder;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

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
        return Constants.CREATE_URL;
    }

    public boolean sendToKannel(JsonObject jsonObject) {
        String smsComplaintString = String.format(Constants.SMS_COMPLAINT_RESPONSE,jsonObject.getReference_id());
        return Util.doGet(Constants.KANNEL_SEND_URL + mobile + "&text=" + URLEncoder.encode(smsComplaintString), mobile)!=null;

    }

	public String data() {
		return "{complaint:{location:" + location + ",mobile:" + mobile + 
				",complaint_type:" + type +",name:" + name + "}}";
	}

	public static void main(String[] args) {
		System.out.println(new SmsMessage("something + foo + bart", "9866", "564646").data());
	}
}
