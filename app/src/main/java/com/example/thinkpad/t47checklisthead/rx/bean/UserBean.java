package com.example.thinkpad.t47checklisthead.rx.bean;

import com.google.gson.*;
/**
 * Created by thinkpad on 2018/4/2.
 */

public class UserBean {
    private String username;
    private String passwrod;
    public UserBean(String username, String passwrod) {
        this.passwrod = passwrod;
        this.username = username;
    }
    public String getPasswrod() {
        return passwrod;
    }
    public void setPasswrod(String passwrod) {
        this.passwrod = passwrod;
    }
    public String getUsername() {
        return username;
    }
    public void setUsername(String username) {
        this.username = username;
    }
    @Override
    public String toString() {
        return new Gson().toJson(this);
    }
}
