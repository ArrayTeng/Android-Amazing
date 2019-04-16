package com.example.observer;

import javafx.beans.InvalidationListener;

import java.util.Observable;

/**
 * @author tengfei
 */
public class DevTechFrontier extends Observable {

    public void postNewInFo(String content){
        setChanged();
        notifyObservers(content);
    }

}
