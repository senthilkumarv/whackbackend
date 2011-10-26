package com.veegee.controllers;

public class Constants {

    public static final String SEPARATOR = "\\+";
    public static final String CREATE_URL = "http://172.16.1.44:3000/complaints.json";
    public static final String oururl = "http://localhost:8080/vgtest/our";
    public static final String USER_COMPLAINT_ERROR_MESSAGE = "Sorry the format of the SMS sent is incorrect.Please use the format {code}+{location}+{yourname}";
    public static final String USER_STATUS_ERROR_MESSAGE = "Sorry the format of the SMS sent is incorrect.Please use the format STATUS+{complaint-id}";
    public static final String USER_CLOSE_ERROR_MESSAGE = "Sorry the format of the SMS sent is incorrect.Please use the format CLOSE+{complaint-id}";
    public static final String SYSTEM_ERROR_MESSAGE = "Sorry there was a server error. Please try again.";
    public static final String SMS_COMPLAINT_RESPONSE = "Your complaint has been registered under complaint number - %s. To query for your status SMS STATUS+{complaint-number} to us.";
    public static final String SMS_INFORMATION_RESPONSE = "To register a complaint SMS to us {code}+{location}+{name}. The  {code} values are WD - Water Dumping, WS - Water Supply, WL - Water Leakage.";
    public static final String SMS_STATUS_RESPONSE = "The status of your complaint id - %s is : %s";
    public static final String SMS_CLOSE_RESPONSE = "Thank you for closing your complaint.Do contact us for future complaints.";

    public static final String KANNEL_SEND_URL = "http://localhost:13003/cgi-bin/sendsms?username=kannel&password=kannel&from=9800000000&to=";
    public static String currentIdVal="";


    public static final String STATUS_URL = "http://172.16.1.44:3000/complaints/status.json";
    public static final String CLOSE_URL = "http://172.16.1.44:3000/complaints/close.json";
}
