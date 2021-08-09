package com.example.xppdemo.chapter5;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.List;
import java.util.concurrent.locks.ReentrantLock;

public class FairAndUnfairTest {
    private static ReentrantLock2 fairLock = new ReentrantLock2(true);
    private static ReentrantLock2 unfairLock = new ReentrantLock2(false);

    public static void main(String[] args) {
//        fair();
        unfair();
    }
    public static void fair(){
        testLock(fairLock);
    }

    public static void unfair(){
        testLock(unfairLock);
    }

    private static void testLock(ReentrantLock2 lock){
        for (int i = 0; i < 5; i++) {
            //启动5个job
            new Job(lock).start();
        }
    }

    private static class Job extends Thread{
        private ReentrantLock2 lock;

        public Job(ReentrantLock2 lock) {
            this.lock = lock;
        }

        @Override
        public void run() {
            System.out.println("lock by"+currentThread().getName());
            for (Thread thread:lock.getQueuedThreads()){
                System.out.print("waiting by [" + thread.getName() + "]");
            }
            System.out.println("lock by"+currentThread().getName());
            for (Thread thread:lock.getQueuedThreads()){
                System.out.print("waiting by  [" + thread.getName() + "]");
            }
        }
    }


    private static class ReentrantLock2 extends ReentrantLock{
        public ReentrantLock2(boolean fair) {
            super(fair);
        }

        @Override
        protected Collection<Thread> getQueuedThreads() {
            List<Thread> arrayList = new ArrayList<Thread>(super.getQueuedThreads());
            Collections.reverse(arrayList);
            return arrayList;
        }
    }



}
