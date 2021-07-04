package com.example.springbootstudy.entity;

import java.io.Serializable;

public class User implements Serializable {
    private static final long serialVersionUID = 5681729147013401856L;
    private String content;
    private String sendTime;

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public String getSendTime() {
        return sendTime;
    }

    public void setSendTime(String sendTime) {
        this.sendTime = sendTime;
    }
}
