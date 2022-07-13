package com.bilibili.juc.base;

import java.util.concurrent.TimeUnit;
import java.util.concurrent.locks.LockSupport;

/**
 * @author gxl
 * @description
 * @createDate 2022/7/13 11:15
 */
public class InterruptTestDemo {
    public static void main(String[] args) throws InterruptedException {

        Thread t1 = new Thread(() -> {
            LockSupport.park();

            System.out.println("结束");
            System.out.println(Thread.interrupted());
//            System.out.println(Thread.interrupted());
        }, "t1");
        t1.start();

        TimeUnit.SECONDS.sleep(1);

        t1.interrupt();
    }
}
