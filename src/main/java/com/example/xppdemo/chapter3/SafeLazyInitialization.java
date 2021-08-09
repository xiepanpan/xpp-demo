package com.example.xppdemo.chapter3;

public class SafeLazyInitialization {
    private static Instance instance;

    public synchronized static Instance getInstance() {
        if (instance == null)
            instance = new Instance();
        return instance;
    }
}
