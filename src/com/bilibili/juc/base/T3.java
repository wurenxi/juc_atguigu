package com.bilibili.juc.base;

import java.util.concurrent.atomic.AtomicInteger;

/**
 * @author gxl
 * @description
 * @createDate 2022/7/8 17:09
 */
public class T3 {

    volatile int number = 0;

    // 读取
    public int getNumber() {
        return number;
    }

    // 写入加锁保证原子性
    public synchronized void setNumber() {
        number++;
    }
    //=======================================
    AtomicInteger atomicInteger = new AtomicInteger();

    public int getAtomicInteger() {
        return atomicInteger.get();
    }

    public void setAtomicInteger() {
        atomicInteger.getAndIncrement();
    }
}
