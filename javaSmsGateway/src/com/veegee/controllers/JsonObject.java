package com.veegee.controllers;

public class JsonObject {

    private String response;
    private String reference_id;
    private String status;


    public JsonObject() {


    }

    public JsonObject(String response, String reference_id, String status) {
        this.response = response;
        this.reference_id = reference_id;
        this.status = status;
    }

    public String getResponse() {
        return response;
    }

    public String getReference_id() {
        return reference_id;
    }

    public String getStatus() {
        return status;
    }
}

