package com.example.xppdemo.chapter5;

import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

/**
 * <p>Description: </p>
 * <p>Copyright: Copyright (c)2,020</p>
 * <p>Company: tope</p>
 * <P>Created Date :2021/7/30</P>
 *
 * @author xiepanpan
 * @version 1.0
 */
public class ConditionUseCase {

    Lock lock = new ReentrantLock();
    Condition condition = lock.newCondition();
    public void conditionWait() throws InterruptedException {
        lock.lock();
        try {
            condition.await();
        } finally {
            lock.unlock();
        }
    }
    public void conditionSignal() throws InterruptedException {
        lock.lock();
        try {
            condition.signal();
        } finally {
            lock.unlock();
        }
    }

}
