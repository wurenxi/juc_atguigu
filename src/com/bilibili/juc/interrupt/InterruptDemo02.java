package com.bilibili.juc.interrupt;

import java.util.concurrent.TimeUnit;

/**
 * @author gxl
 * @description
 * @createDate 2022/7/5 9:52
 */
public class InterruptDemo02 {
    public static void main(String[] args) {
        // 实例方法interrupt()仅仅是设置线程的中断状态位为true，不会停止线程
        Thread t1 = new Thread(() -> {
            for (int i = 1; i <= 300; i++) {
                System.out.println("-----: " + i);
            }
            System.out.println("t1线程默认的中断标志02：" + Thread.currentThread().isInterrupted()); // false
        }, "t1");
        t1.start();

        System.out.println("t1线程默认的中断标志：" + t1.isInterrupted()); // false

        try {
            TimeUnit.MICROSECONDS.sleep(2);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }

        t1.interrupt(); // true

        System.out.println("t1线程调用interrupt()后的中断标志01：" + t1.isInterrupted()); // true

        try {
            TimeUnit.SECONDS.sleep(2);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }

        System.out.println("t1线程调用interrupt()后的中断标志03：" + t1.isInterrupted()); // false 中断不获得的线程不会产生任何影响

    }
}
