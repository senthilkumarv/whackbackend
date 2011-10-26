package com.veegee.controllers;

public interface Message {

    public String getUrl();

    boolean sendToKannel(JsonObject jsonObject);

	public String data();
}
