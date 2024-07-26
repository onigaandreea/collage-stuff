package com.example;

import com.google.gson.Gson;

public class Message {
    private String sender;
    private String content;

    public Message(String sender, String content) {
        this.sender = sender;
        this.content = content;
    }

    public String toJson(){
        return new Gson().toJson(this);
    }

    public static Message fromJson(String json){
        return new Gson().fromJson(json, Message.class);
    }

    public String getSender() {
        return sender;
    }

    public void setSender(String sender) {
        this.sender = sender;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }
}
