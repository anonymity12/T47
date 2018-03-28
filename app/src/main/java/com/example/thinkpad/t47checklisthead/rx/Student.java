package com.example.thinkpad.t47checklisthead.rx;

/**
 * Created by thinkpad on 2018/3/27.
 */

public class Student implements Observer {
    private String name;
    @Override
    public void setName(String name) {
        this.name = name;
    }

    @Override
    public void say(String message) {
        System.out.println(message + ":" + this.name + "dao!!!");
    }
}
