package com.bilibili.juc.base;

/**
 * @author gxl
 * @description
 * @createDate 2022/6/29 12:05
 */
public class ThreadBaseDemo {
    public static void main(String[] args) {
        new Thread(() -> {}, "t1").start();

        Object o = new Object();

        Thread t2 = new Thread(() -> {
            synchronized (o) {

            }
        }, "t2");
        t2.start();
    }
}

// java = C++ --> (C++)-- = java
