package com.example.dellpc.skychat;


public class Message {

    private String fromName, message;
    private String isSelf;

    public Message() {
    }

    public Message(String fromName, String message, String isSelf) {
        this.fromName = fromName;
        this.message = message;
        this.isSelf = isSelf;
    }

    public String getFromName() {
        return fromName;
    }

    public void setFromName(String fromName) {
        this.fromName = fromName;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public String isSelf() {
        return isSelf;
    }

    public void setSelf(String isSelf) {
        this.isSelf = isSelf;
    }




}
