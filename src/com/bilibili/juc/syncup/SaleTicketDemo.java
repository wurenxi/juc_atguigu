package com.bilibili.juc.syncup;

import org.openjdk.jol.info.ClassLayout;

/**
 * @author gxl
 * @description
 * @createDate 2022/7/11 21:30
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

class Ticket { // 资源类，模拟3个售票员卖完50张票

    private int number = 50;

    Object lockObject = new Object();

    public void sale() {
        synchronized (lockObject) {
            if(number > 0) {
                System.out.println(Thread.currentThread().getName()+"\t卖出第："+(number--)+"\t还剩下："+number);
            }
        }
    }
}
