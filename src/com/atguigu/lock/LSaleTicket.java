package com.atguigu.lock;

import java.util.concurrent.locks.ReentrantLock;

/**
 * @author gxl
 * @description
 * @createDate 2021/11/5 9:26
 */
public class LSaleTicket {
    public static void main(String[] args) {
        LTicket ticket = new LTicket();

        // 创建三个线程
        new Thread(() -> {
            // 调用卖票方法
            for (int i = 0; i < 40; i++) {
                ticket.sale();
            }
        },"AA").start();

        new Thread(() -> {
            // 调用卖票方法
            for (int i = 0; i < 40; i++) {
                ticket.sale();
            }
        },"BB").start();

        new Thread(() -> {
            // 调用卖票方法
            for (int i = 0; i < 40; i++) {
                ticket.sale();
            }
        },"VV").start();
    }
}

// 第一步 创建资源类，定义属性和操作方法
class LTicket{
    private int number = 30;
    private int count = 0;

    private final ReentrantLock lock = new ReentrantLock();

    public void sale(){
        // 上锁
        lock.lock();

        try {
            // 判断是否有票
            if(number > 0){
                System.out.println(Thread.currentThread().getName()+" : 卖出："+(++count)+"号票 剩下："+(--number)+"张");
            }
        } finally {
            // 解锁
            lock.unlock();
        }
    }
}
