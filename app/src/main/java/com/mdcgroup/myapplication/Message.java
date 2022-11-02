package com.mdcgroup.myapplication;

import java.io.Serializable;

public class Message  implements Serializable {

    private String uid;
    private String body;

    public Message(String uid, String body) {
        this.uid = uid;
        this.body = body;
    }

    public String getUid() {
        return uid;
    }

    public void setUid(String uid) {
        this.uid = uid;
    }

    public String getBody() {
        return body;
    }

    public void setBody(String body) {
        this.body = body;
    }
}
