package com.bilibili.juc.objecthead;

/**
 * @author gxl
 * @description
 * @createDate 2022/7/11 10:24
 */
public class ObjectHeadDemo {
    public static void main(String[] args) {
        Object o = new Object();    // new 一个对象，占内存多少？

        System.out.println(o.hashCode());   // 这个hashcode记录在对象的什么地方？

        synchronized (o) {

        }

        System.gc();    // 手动收集垃圾... 15次可以从新生区 --- 老年代
    }
}
