package com.bilibili.juc.interrupt;

import java.util.concurrent.TimeUnit;
import java.util.concurrent.atomic.AtomicBoolean;

/**
 * @author gxl
 * @description
 * @createDate 2022/7/4 21:02
 */
public class InterruptDemo {

    static volatile boolean isStop = false;

    static AtomicBoolean atomicBoolean = new AtomicBoolean(false);

    public static void main(String[] args) {
        Thread t1 = new Thread(() -> {
            while (true) {
                if(Thread.currentThread().isInterrupted()) {
                    System.out.println(Thread.currentThread().getName()+"\t isInterrupted()的值被修改为true，程序停止");
                    break;
                }
                System.out.println("t1 ----hello isInterrupt api");
            }
        }, "t1");
        t1.start();

        System.out.println("-----t1的默认中断标志位：" + t1.isInterrupted());

        try {
            TimeUnit.MICROSECONDS.sleep(20);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }

        // t2向t1发起协商，将t1的中断标志位设为true希望t1停下来
//        new Thread(() -> {
//            t1.interrupt();
//        }, "t2").start();
        t1.interrupt();
    }

    private static void m2_atomicBoolean() {
        new Thread(() -> {
            while (true) {
                if(atomicBoolean.get()) {
                    System.out.println(Thread.currentThread().getName()+"\t atomicBoolean的值被修改为true，程序停止");
                    break;
                }
                System.out.println("t1 ----hello atomicBoolean");
            }
        }, "t1").start();

        try {
            TimeUnit.MICROSECONDS.sleep(20);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }

        new Thread(() -> {
            atomicBoolean.set(true);
        }, "t2").start();
    }

    private static void m1_volatile() {
        new Thread(() -> {
            while (true) {
                if(isStop) {
                    System.out.println(Thread.currentThread().getName()+"\t isStop的值被修改为true，程序停止");
                    break;
                }
                System.out.println("t1 ----hello volatile");
            }
        }, "t1").start();

        try {
            TimeUnit.MICROSECONDS.sleep(20);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }

        new Thread(() -> {
            isStop = true;
        }, "t2").start();
    }
}
