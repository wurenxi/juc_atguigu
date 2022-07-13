package com.bilibili.juc.rwlock;

import java.util.concurrent.locks.ReentrantReadWriteLock;

/**
 * @author gxl
 * @description
 * 锁降级：遵循获取写锁→再获取读锁→再释放写锁的次序，写锁能够降级成为读锁。
 *
 * 如果一个线程占有了写锁，在不释放写锁的情况下，它还能占有读锁，即写锁降级为读锁。
 *
 * 读没有完成时候写锁无法获取锁，必须要等着读锁读完后才有机会写
 * @createDate 2022/7/13 18:26
 */
public class LockDownGradingDemo {
    public static void main(String[] args) {
        ReentrantReadWriteLock rwLock = new ReentrantReadWriteLock();

        ReentrantReadWriteLock.ReadLock readLock = rwLock.readLock();
        ReentrantReadWriteLock.WriteLock writeLock = rwLock.writeLock();

        // 正常 A B两个线程
        // A
//        writeLock.lock();
//        System.out.println("---写入");
//        writeLock.unlock();
//
//        // B
//        readLock.lock();
//        System.out.println("---读取");
//        readLock.unlock();

        // 本例 only one 同一个线程
        writeLock.lock();
        System.out.println("---写入");

        /*
        ...
         */
        readLock.lock();
        System.out.println("---读取");

        readLock.unlock();

        writeLock.unlock();

        readLock.unlock();

    }
}
