package com.example.xppdemo.chapter6;

import java.util.HashMap;
import java.util.UUID;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.ConcurrentLinkedQueue;

/**
 * <p>Description: </p>
 * <p>Copyright: Copyright (c)2,020</p>
 * <p>Company: tope</p>
 * <P>Created Date :2021/7/30</P>
 *
 * @author xiepanpan
 * @version 1.0
 */
public class HashMapTest {
    public static void main(String[] args) throws InterruptedException {
        final HashMap<String, String> map = new HashMap<String, String>(2);
        Thread t = new Thread(new Runnable() {
            @Override
            public void run() {
                for (int i = 0; i < 10000; i++) {
                    new Thread(new Runnable() {
                        @Override
                        public void run() {
                            map.put(UUID.randomUUID().toString(), "");
                        }
                    }, "ftf" + i).start();
                }
            }
        }, "ftf");
        t.start();
        t.join();

        ConcurrentHashMap concurrentHashMap = new ConcurrentHashMap();
        concurrentHashMap.size();
        ConcurrentLinkedQueue concurrentLinkedQueue = new ConcurrentLinkedQueue();
        concurrentLinkedQueue.poll();
    }


}
