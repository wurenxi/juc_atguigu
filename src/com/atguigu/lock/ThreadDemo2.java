package com.atguigu.lock;

import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

/**
 * @author gxl
 * @description
 * @createDate 2021/11/5 10:58
 */
public class ThreadDemo2 {
    public static void main(String[] args) {
        Share share = new Share();

        new Thread(() -> {
            for (int i = 1; i <= 10; i++) {
                try {
                    share.incr();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        },"AA").start();

        new Thread(() -> {
            for (int i = 1; i <= 10; i++) {
                try {
                    share.decry();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        },"BB").start();

        new Thread(() -> {
            for (int i = 1; i <= 10; i++) {
                try {
                    share.incr();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        },"CC").start();

        new Thread(() -> {
            for (int i = 1; i <= 10; i++) {
                try {
                    share.decry();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        },"DD").start();
    }
}

class Share{
    private int number = 0;

    // 创建lock
    private Lock lock = new ReentrantLock();
    private Condition condition = lock.newCondition();

    // +1
    public void incr() throws InterruptedException {
        // 上锁
        lock.lock();

        try {
            // 判断
            while (number != 0){
                condition.await();
            }
            // 干活
            number++;
            System.out.println(Thread.currentThread().getName()+" :: "+number);
            // 通知
            condition.signalAll();
        }finally {
            lock.unlock();
        }
    }

    // -1
    public void decry() throws InterruptedException {
        // 上锁
        lock.lock();

        try {
            // 判断
            while (number != 1){
                condition.await();
            }
            // 干活
            number--;
            System.out.println(Thread.currentThread().getName()+" :: "+number);
            // 通知
            condition.signalAll();
        }finally {
            lock.unlock();
        }
    }
}
