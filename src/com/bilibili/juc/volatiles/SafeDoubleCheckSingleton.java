package com.bilibili.juc.volatiles;

/**
 * @author gxl
 * @description
 * @createDate 2022/7/8 12:19
 */
public class SafeDoubleCheckSingleton {

    // 通过volatile声明，实现线程安全的延迟初始化
    private volatile static SafeDoubleCheckSingleton singleton;

    // 私有化构造方法
    private SafeDoubleCheckSingleton() {}

    // 双重锁操作
    public static SafeDoubleCheckSingleton getInstance() {
        if(singleton == null) {
            // 1.多线程并发创建对象时，会通过加锁保证只有一个线程能创建对象
            synchronized (SafeDoubleCheckSingleton.class) {
                if(singleton == null) {
                    // 隐患：多线程环境下，由于重排序，该对象可能还未完成初始化就被其他线程读取
                    singleton = new SafeDoubleCheckSingleton();
                }
            }
        }

        // 2.对象创建完毕，执行getInstance()将不需要读取锁，直接返回创建对象
        return singleton;
    }
}
