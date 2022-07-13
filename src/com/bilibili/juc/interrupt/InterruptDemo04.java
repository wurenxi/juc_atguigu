package com.bilibili.juc.interrupt;

/**
 * @author gxl
 * @description
 * @createDate 2022/7/5 10:44
 */
public class InterruptDemo04 {
    public static void main(String[] args) {
        // 测试当前线程是否被中断（检查中断标志），返回一个boolean并清除中断状态。
        // 第二次调用时中断标志已经被清除，将返回一个false。

        System.out.println(Thread.currentThread().getName()+"\t"+Thread.interrupted()); // false
        System.out.println(Thread.currentThread().getName()+"\t"+Thread.interrupted()); // false
        System.out.println("-----1");
        Thread.currentThread().interrupt(); // 中断标志位设置为true
        System.out.println("-----2");
        System.out.println(Thread.currentThread().getName()+"\t"+Thread.interrupted()); // true
        System.out.println(Thread.currentThread().getName()+"\t"+Thread.interrupted()); // false

        Thread.interrupted();

        Thread.currentThread().isInterrupted();
    }
}
