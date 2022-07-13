package com.bilibili.juc.rwlock;

import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReadWriteLock;
import java.util.concurrent.locks.ReentrantLock;
import java.util.concurrent.locks.ReentrantReadWriteLock;

/**
 * @author gxl
 * @description
 * @createDate 2022/7/13 17:39
 */
public class ReentrantReadWriteLockDemo {
    public static void main(String[] args) {

        MyResource myResource = new MyResource();

        for (int i = 0; i < 10; i++) {
            int finalI = i;
            new Thread(() -> {
                myResource.write(finalI +"", finalI +"");
            }, String.valueOf(i)).start();
        }

        for (int i = 0; i < 10; i++) {
            int finalI = i;
            new Thread(() -> {
                myResource.read(finalI +"");
            }, String.valueOf(i)).start();
        }

        try {
            TimeUnit.SECONDS.sleep(1);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }

        for (int i = 0; i < 10; i++) {
            int finalI = i;
            new Thread(() -> {
                myResource.write(finalI +"", finalI +"");
            }, "新写锁线程->"+i).start();
        }
    }
}

class MyResource { // 资源类，模拟一个简单的缓存
    Map<String, String> map = new HashMap<>();
    //=======ReentrantLock 等价于 ======synchronized
    Lock lock = new ReentrantLock();
    //=======ReentrantReadWriteLock一体两面，读写互斥，读读共享
    ReadWriteLock rwLock = new ReentrantReadWriteLock();

    public void write(String key, String value) {
        rwLock.writeLock().lock();
        try {
            System.out.println(Thread.currentThread().getName()+"\t正在写入");
            map.put(key,value);
            TimeUnit.MILLISECONDS.sleep(500);
            System.out.println(Thread.currentThread().getName()+"\t完成写入");
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        } finally {
            rwLock.writeLock().unlock();
        }
    }

    public void read(String key) {
        rwLock.readLock().lock();
        try {
            System.out.println(Thread.currentThread().getName()+"\t正在读取");
            String result = map.get(key);
//            TimeUnit.MILLISECONDS.sleep(200);
            // 演示读锁没有完成之前，写锁无法获得
            TimeUnit.MILLISECONDS.sleep(2000);
            System.out.println(Thread.currentThread().getName()+"\t完成读取\t"+result);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        } finally {
            rwLock.readLock().unlock();
        }
    }
}
