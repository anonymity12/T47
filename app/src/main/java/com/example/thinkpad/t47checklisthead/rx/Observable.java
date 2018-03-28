package com.example.thinkpad.t47checklisthead.rx;



/**
 * Created by thinkpad on 2018/3/27.
 */

public interface Observable {
    void attach(Observer observer);

    void detach(Observer observer);

    void notifyObservers(String message);
}
