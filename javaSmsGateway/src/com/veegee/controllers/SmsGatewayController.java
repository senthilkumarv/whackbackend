package com.veegee.controllers;

import com.google.gson.Gson;
import com.veegee.db.BaseRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class SmsGatewayController {


    @Autowired
    private BaseRepository baseRepository;

    @RequestMapping(value = "/receive/{mobile}/{text}/{timestamp}")
    public void receiveSms(@PathVariable String mobile, @PathVariable String text, @PathVariable String timestamp) {

        Message message = null;
        if(Constants.currentIdVal.equals(timestamp)) return;
        Constants.currentIdVal=timestamp;
        String type = text.split(Constants.SEPARATOR)[0];
        if (type.equalsIgnoreCase("status"))
            message = new StatusMessage(text, mobile, timestamp);
        else if (type.equalsIgnoreCase("close"))
            message = new CloseMessage(text, mobile, timestamp);
        else if (type.equalsIgnoreCase("WD") || type.equalsIgnoreCase("WL") || type.equalsIgnoreCase("WS"))
            message = new SmsMessage(text, mobile, timestamp);
        else {
            new InformationMessage(mobile, timestamp).sendToKannel(null);
            return;

        }


        String url = message.getUrl();

        String jsonResponse = Util.doGetWithParseJsonResponse(url, "");


        JsonObject jsonObject = new Gson().fromJson(jsonResponse, JsonObject.class);
        message.sendToKannel(jsonObject);
        return;


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
