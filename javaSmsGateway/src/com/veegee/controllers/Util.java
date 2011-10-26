package com.veegee.controllers;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.URLEncoder;

import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.impl.client.DefaultHttpClient;

public class Util {

    public static String doGetWithParseJsonResponse(String url, String mobile) {
        try {

            HttpResponse response = doGet(url, mobile);
            String json = null;
            int statusCode = response.getStatusLine().getStatusCode();

            if (statusCode == 200)
                json = getJson(new StringBuilder(), response);
            else
                doGet(Constants.KANNEL_SEND_URL + mobile + "&text=" + URLEncoder.encode(Constants.SYSTEM_ERROR_MESSAGE), mobile);


            return json;
        } catch (Exception e) {
            doGet(Constants.KANNEL_SEND_URL + mobile + "&text=" + URLEncoder.encode(Constants.SYSTEM_ERROR_MESSAGE), mobile);

        }
        return null;
    }

    public static HttpResponse doGet(String url, String mobile) {
        try {

            DefaultHttpClient client = new DefaultHttpClient();
            HttpGet httpGet = new HttpGet(url);
            org.apache.http.HttpResponse response = client.execute(httpGet);


            return response;
        } catch (Exception e) {
            doGet(Constants.KANNEL_SEND_URL + mobile + "&text=" + URLEncoder.encode(Constants.SYSTEM_ERROR_MESSAGE), mobile);

        }
        return null;
    }

    private static String getJson(StringBuilder builder, HttpResponse response) throws IOException {
        HttpEntity entity = response.getEntity();
        InputStream content = entity.getContent();
        BufferedReader reader = new BufferedReader(
                new InputStreamReader(content));
        String line;
        while ((line = reader.readLine()) != null) {
            builder.append(line);
        }
        return builder.toString();
    }

	public static String doPost(String url, String data, String mobile) {
        try {
            DefaultHttpClient client = new DefaultHttpClient();
            HttpPost httpPost = new HttpPost(url);
            httpPost.setHeader("Content-Type", "application/json");
            HttpResponse response = client.execute(httpPost);
            
            String json = null;
            int statusCode = response.getStatusLine().getStatusCode();

            if (statusCode == 200)
                json = getJson(new StringBuilder(), response);
            else
                doGet(Constants.KANNEL_SEND_URL + mobile + "&text=" + URLEncoder.encode(Constants.SYSTEM_ERROR_MESSAGE), mobile);


            return json;
        } catch (Exception e) {
            doGet(Constants.KANNEL_SEND_URL + mobile + "&text=" + URLEncoder.encode(Constants.SYSTEM_ERROR_MESSAGE), mobile);

        }
        return null;
	}

}
