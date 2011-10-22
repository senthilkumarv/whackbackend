package com.veegee.controllers;

import com.google.gson.Gson;
import com.google.gson.JsonArray;
import com.veegee.db.BaseRepository;
import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.DefaultHttpClient;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

import java.io.*;
import java.lang.reflect.Array;
import java.net.URL;
import java.util.HashMap;

@Controller
public class SmsGatewayController {


    @Autowired
    private BaseRepository baseRepository;

    @RequestMapping(value = "/receive/{mobile}/{text}/{timestamp}")
    public String receiveSms(@PathVariable String mobile, @PathVariable String text, @PathVariable String timestamp) {

        String url;
        String type = text.split(Constants.SEPARATOR)[0];
        if (type.equals("status")) {
            url = new StatusMessage(text, mobile, timestamp).getUrl();
        } else if (type.equals("close")) {
            url = new CloseMessage(text, mobile, timestamp).getUrl();
        } else {
            url = new SmsMessage(text, mobile, timestamp).getUrl();

        }

        String jsonResponse = Util.doGetWithParseJsonResponse(url, "");


        JsonObject jsonObject = new Gson().fromJson(jsonResponse, JsonObject.class);
        return "WEB-INF/views/empty.jsp";


    }


//     public HttpStatus doPostToParentApp(smsMessage smsMessage, String mobile) {
//        try {
//            DefaultHttpClient client = new DefaultHttpClient();
//            HttpPost httpPost = new HttpPost(Constants.PARENT_SERVER_URL);
//            HttpParams params = httpPost.getParams();
//            params.setParameter("mobile",smsMessage.mobile);
//            params.setParameter("location",smsMessage.location);
//            params.setParameter("name",smsMessage.name);
//            params.setParameter("type",smsMessage.type);
//            org.apache.http.HttpResponse response = client.execute(httpPost);
//            return HttpStatus.valueOf(response.getStatusLine().getStatusCode());
//        } catch (IOException e) {
//            doGet(Constants.KANNEL_SEND_URL + mobile + "&text=" + Constants.SYSTEM_ERROR_MESSAGE, mobile);
//
//        }
//        return null;
//    }


}
