package com.example.xppdemo.chapter3;

import com.example.xppdemo.chapter3.Instance;

public class InstanceFactory {
    private static class InstanceHolder {
        public static Instance instance = new Instance();
    }
    public static Instance getInstance() {
        return InstanceHolder.instance ; // 这里将导致InstanceHolder类被初始化
    }
}
