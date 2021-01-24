package com.centralgateway.task;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;


public class SMSMessage {

    private static final Logger logger = LoggerFactory.getLogger(SMSMessage.class);

    private String text;
    private String phone;

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }
    public void welcome(){
        logger.info("This SMS Message Is A welcomed relief!");
    }

    public void goodBye(){
        logger.info("So Sad To See You Go!");
    }

}
