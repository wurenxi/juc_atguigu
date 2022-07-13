package com.bilibili.juc.locks;

import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

/**
 * @author gxl
 * @description
 * @createDate 2022/7/1 13:17
 */
public class SaleTicketDemo {
    public static void main(String[] args) {
        Ticket ticket = new Ticket();

        new Thread(() -> {
            for (int i = 0; i < 55; i++) {
                ticket.sale();
            }
        }, "a").start();

        new Thread(() -> {
            for (int i = 0; i < 55; i++) {
                ticket.sale();
            }
        }, "b").start();

        new Thread(() -> {
            for (int i = 0; i < 55; i++) {
                ticket.sale();
            }
        }, "c").start();
    }
}

/**
 * 资源类，模拟3个售票员卖完50张票
 */
class Ticket{
    private int number = 50;
    Lock lock = new ReentrantLock(true);

    public void sale() {
        lock.lock();

        try {
            if(number > 0) {
                System.out.println(Thread.currentThread().getName()+"卖出第：\t" + (number--) + "\t还剩下：" + number);
            }
        } finally {
            lock.unlock();
        }
    }
}
