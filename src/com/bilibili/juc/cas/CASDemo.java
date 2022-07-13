package com.bilibili.juc.cas;

import java.util.concurrent.atomic.AtomicInteger;

/**
 * @author gxl
 * @description
 * @createDate 2022/7/8 17:29
 */
public class CASDemo {
    public static void main(String[] args) {
        AtomicInteger atomicInteger = new AtomicInteger(5);

        System.out.println(atomicInteger.compareAndSet(5, 2022) + "\t" + atomicInteger.get());
        System.out.println(atomicInteger.compareAndSet(5, 2022) + "\t" + atomicInteger.get());
    }
}
