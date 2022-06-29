package com.atguigu.readwrite;

import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.locks.ReadWriteLock;
import java.util.concurrent.locks.ReentrantReadWriteLock;

/**
 * @author gxl
 * @description
 * @createDate 2021/11/8 8:59
 */
// 资源类
class MyCache{
    // 创建map集合
    private volatile Map<String,Object> map = new HashMap<>();

    // 创建读写锁对象
    private ReadWriteLock rwLock = new ReentrantReadWriteLock();

    // 放数据
    public void put(String key, Object value){
        // 添加写锁
        rwLock.writeLock().lock();


        try {
            System.out.println(Thread.currentThread().getName()+" 正在写操作"+key);
            //睡一会儿
            TimeUnit.MILLISECONDS.sleep(300);

            map.put(key,value);

            System.out.println(Thread.currentThread().getName()+" 写完了"+key);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }finally {
            // 释放写锁
            rwLock.writeLock().unlock();
        }
    }

    // 取数据
    public Object get(String key){
        // 添加读锁
        rwLock.readLock().lock();

        Object result = null;
        //睡一会儿
        try {
            System.out.println(Thread.currentThread().getName()+" 正在读取操作"+key);
            TimeUnit.MILLISECONDS.sleep(300);

            result = map.get(key);

            System.out.println(Thread.currentThread().getName()+" 取完了"+key);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }finally {
            // 释放读锁
            rwLock.readLock().unlock();
        }

        return result;
    }
}

public class ReadWriteLockDemo {
    public static void main(String[] args) {
        MyCache myCache = new MyCache();

        // 创建线程放数据
        for (int i = 1; i <= 5; i++) {
            final int num = i;
            new Thread(() -> {
               myCache.put(num+"",num+"");
            },String.valueOf(i)).start();
        }

        // 创建线程取数据
        for (int i = 1; i <= 5; i++) {
            final int num = i;
            new Thread(() -> {
                myCache.get(num+"");
            },String.valueOf(i)).start();
        }
    }
}
