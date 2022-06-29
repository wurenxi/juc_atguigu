package com.atguigu.lock;

import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

/**
 * @author gxl
 * @description
 * @createDate 2021/11/6 10:03
 */
public class ThreadDemo3 {
    public static void main(String[] args) {
        ShareResource shareResource = new ShareResource();

        new Thread(() -> {
            for (int i = 0; i < 10; i++) {
                try {
                    shareResource.print5(i);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        },"AA").start();

        new Thread(() -> {
            for (int i = 0; i < 10; i++) {
                try {
                    shareResource.print10(i);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        },"BB").start();

        new Thread(() -> {
            for (int i = 0; i < 10; i++) {
                try {
                    shareResource.print15(i);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        },"CC").start();
    }
}

// 第一步 创建资源类
class ShareResource{
    // 定义标志位
    private int flag = 1; // 1 AA   2 BB    3 CC
    // 创建Lock锁
    private Lock lock = new ReentrantLock();

    // 创建三个condition
    private Condition c1 = lock.newCondition();
    private Condition c2 = lock.newCondition();
    private Condition c3 = lock.newCondition();

    // 打印5次
    public void print5(int loop) throws InterruptedException {
        // 上锁
        lock.lock();

        try{
            // 判断
            while (flag != 1){
                c1.await();
            }

            // 干活
            for (int i = 0; i < 5; i++) {
                System.out.println(Thread.currentThread().getName()+" :: "+i+" :轮数:"+loop);
            }

            // 通知
            flag = 2; // 修改标志位 2
            c2.signal(); // 通知BB线程
        }finally {
            lock.unlock();
        }
    }

    public void print10(int loop) throws InterruptedException {
        // 上锁
        lock.lock();

        try{
            // 判断
            while (flag != 2){
                c2.await();
            }

            // 干活
            for (int i = 0; i < 10; i++) {
                System.out.println(Thread.currentThread().getName()+" :: "+i+" :轮数:"+loop);
            }

            // 通知
            flag = 3; // 修改标志位 3
            c3.signal(); // 通知CC线程
        }finally {
            lock.unlock();
        }
    }

    public void print15(int loop) throws InterruptedException {
        // 上锁
        lock.lock();

        try{
            // 判断
            while (flag != 3){
                c3.await();
            }

            // 干活
            for (int i = 0; i < 15; i++) {
                System.out.println(Thread.currentThread().getName()+" :: "+i+" :轮数:"+loop);
            }

            // 通知
            flag = 1; // 修改标志位 1
            c1.signalAll(); // 通知AA线程
        }finally {
            lock.unlock();
        }
    }
}
