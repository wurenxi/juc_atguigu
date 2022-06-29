package com.atguigu.sync;

/**
 * @author gxl
 * @description
 * @createDate 2021/11/6 12:46
 */
public class SyncLockDemo {

    public synchronized void add(){
        add();
    }

    public static void main(String[] args) {

        new SyncLockDemo().add();

        Object o = new Object();
        new Thread(() -> {
            synchronized (o){
                System.out.println(Thread.currentThread().getName()+" 外层");

                synchronized (o){
                    System.out.println(Thread.currentThread().getName()+" 中层");

                    synchronized (o){
                        System.out.println(Thread.currentThread().getName()+" 内层");
                    }
                }
            }
        },"t1").start();
    }
}
