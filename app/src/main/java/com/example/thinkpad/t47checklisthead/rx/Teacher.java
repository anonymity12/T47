package com.example.thinkpad.t47checklisthead.rx;


import java.util.ArrayList;
import java.util.List;

/**
 * Created by thinkpad on 2018/3/27.
 */

public class Teacher implements Observable{

    List<Observer> observers = new ArrayList<>();

    @Override
    public void attach(Observer observer) {
        observers.add(observer);
    }

    @Override
    public void detach(Observer observer) {
        observers.add(observer);
    }

    @Override
    public void notifyObservers(String message) {
        for (Observer observer : observers) {
            observer.say(message);
        }
    }
}
