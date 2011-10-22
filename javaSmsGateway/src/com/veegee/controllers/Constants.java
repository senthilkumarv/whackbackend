package com.veegee.controllers;

public class Constants {

    public static final String SEPARATOR = "\\+";
    public static final String PARENT_SERVER_URL = "http://172.16.1.15:3000/complaint/create.json/";
    public static final String CREATE_URL = "http://172.16.1.15:3000/complaint/create.json/";
    public static final String oururl = "http://localhost:8080/vgtest/our";
    public static final String USER_ERROR_MESSAGE = "Sorry+the+format+of+the+SMS+sent+is+incorrect.Please+sending+the+format+code%2Blocation%2Bname";
    public static final String SYSTEM_ERROR_MESSAGE = "Sorry+there+was+a+server+error.+Please+try+again+with+code%2Blocation%2Bname.";
    public static final String KANNEL_SEND_URL = "http://localhost:13003/cgi-bin/sendsms?username=kannel&password=kannel&from=9800000000&to=";


    public static final String STATUS_URL = "http://172.16.1.15:3000/complaint/status.json/";
    public static final String CLOSE_URL = "http://172.16.1.15:3000/complaint/close.json/";
}
