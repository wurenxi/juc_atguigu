package com.bilibili.juc.base;

import java.util.concurrent.TimeUnit;

/**
 * @author gxl
 * @description
 * @createDate 2022/6/29 16:43
 */
public class DaemonDemo {
    public static void main(String[] args) {
        Thread t1 = new Thread(() -> {
            System.out.println(Thread.currentThread().getName()+"\t开始运行，"+
                    (Thread.currentThread().isDaemon() ? "守护线程":"用户线程"));
            while (true) {

            }
        }, "t1");

        t1.setDaemon(true);
        t1.start();

        try {
            TimeUnit.SECONDS.sleep(3);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        System.out.println(Thread.currentThread().getName()+"\t ---end 主线程");
    }
}
