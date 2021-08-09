package com.example.xppdemo;

public class ReorderExample {
    int a = 0;
    volatile boolean flag = false;
    public void writer() {
        a = 2; // 1
    }
    public void reader() {
        if (flag) { // 3
            int i = a * a; // 4
            System.out.println(i);
        }
    }

}
