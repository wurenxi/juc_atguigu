package com.bilibili.juc.base;

/**
 * @author gxl
 * @description
 * @createDate 2022/7/8 11:37
 */
public class UseVolatileDemo {

    /**
     * 使用：当读多写少，结合使用内部锁和volatile变量减少同步的开销
     * 理由：利用volatile保证读取操作的可见性；利用synchronized保证复合操作的原子性
     */
    public class Counter {
        private volatile int value;

        public int getValue() {
            return value; // 利用volatile保证服务操作的可见性
        }

        public synchronized int increment() {
            return value++; // 利用synchronized保证复合操作的原子性
        }
    }
}
