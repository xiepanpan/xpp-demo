package com.example.xppdemo.chapter5;

import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantReadWriteLock;

public class Cache {
    static Map<String, Object> map = new HashMap<String, Object>();
    static ReentrantReadWriteLock rwl = new ReentrantReadWriteLock();
    static Lock r = rwl.readLock();
    static Lock w = rwl.writeLock();
    // 获取一个key对应的value
    public static final Object get(String key) {
        r.lock();
        try {
            return map.get(key);
        } finally {
            r.unlock();
        }
    }
    // 设置key对应的value，并返回旧的value
    public static final Object put(String key, Object value) {
        w.lock();
        try {
            return map.put(key, value);
        } finally {
            w.unlock();
        }
    }// 清空所有的内容
    public static final void clear() {
        w.lock();
        try {
            map.clear();
        } finally {
            w.unlock();
        }
    }

//    public void processData() {
//        readLock.lock();
//        if (!update) {
//            // 必须先释放读锁
//            readLock.unlock();
//            // 锁降级从写锁获取到开始
//            writeLock.lock();
//            try {
//                if (!update) {
//                    // 准备数据的流程（略）
//                    update = true;
//                }
//                readLock.lock();
//            } finally {
//                writeLock.unlock();
//            }
//            // 锁降级完成，写锁降级为读锁
//        }
//        try {
//            // 使用数据的流程（略）
//        } finally {
//            readLock.unlock();
//        }
//    }
}
