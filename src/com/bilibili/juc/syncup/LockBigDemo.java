package com.bilibili.juc.syncup;

/**
 * @author gxl
 * @description 锁粗化
 * 假如方法中首尾相接，前后相邻的都是同一个锁对象，那JIT编译器就会把这几个synchronized块合并成一个大块，
 * 加粗加大范围，一次申请使用即可，避免次次的申请和释放锁，提升了性能
 * @createDate 2022/7/12 13:54
 */
public class LockBigDemo {

    static Object objectLock = new Object();

    public static void main(String[] args) {
        new Thread(() -> {
            synchronized (objectLock) {
                System.out.println("11111");
            }
            synchronized (objectLock) {
                System.out.println("22222");
            }
            synchronized (objectLock) {
                System.out.println("3333");
            }
            synchronized (objectLock) {
                System.out.println("44444");
            }

            // 等同于
            synchronized (objectLock) {
                System.out.println("11111");
                System.out.println("22222");
                System.out.println("3333");
                System.out.println("44444");
            }
        }, "t1").start();
    }
}
