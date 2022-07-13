package com.bilibili.juc.locks;

import java.util.concurrent.TimeUnit;

/**
 * @author gxl
 * @description
 * @createDate 2022/7/3 12:10
 */
public class MyLockTest {

    public static synchronized void myLockFun() {
        System.out.println("MyLock中的方法被调用");
    }

    public static void main(String[] args) {
        Phone2 phone2 = new Phone2();

        new Thread(() -> {
            phone2.sendMsg();
        }, "AA").start();

        try {
            TimeUnit.SECONDS.sleep(1);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }

        new Thread(() -> {
            phone2.sendEmail();
        }, "BB").start();

        try {
            TimeUnit.SECONDS.sleep(1);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }

        new Thread(MyLockTest::myLockFun, "mylock").start();
    }
}

class Phone2 {

    public synchronized void sendMsg() {
        try {
            TimeUnit.SECONDS.sleep(1);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
        System.out.println("发送短信");
    }

    public void sendEmail() {
        synchronized (MyLockTest.class) {
            try {
                TimeUnit.SECONDS.sleep(3);
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
            System.out.println("发送邮件");
        }
    }
}
