package com.bilibili.juc.locks;

/**
 * @author gxl
 * @description
 * @createDate 2022/7/1 15:08
 */
public class LockSyncDemo {

    Object object = new Object();

    public static synchronized void m3() {
        System.out.println("--hello static synchronized m3");
    }

//    public synchronized void m2() {
//        System.out.println("--hello synchronized m2");
//    }

    //    public void m1() {
//        synchronized (object) {
//            System.out.println("--hello synchronized code block");
//            throw new RuntimeException("---exp");
//        }
//    }

    public static void main(String[] args) {

    }
}

class Book extends Object{

}
