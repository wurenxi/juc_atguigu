package com.bilibili.juc.rwlock;

import java.util.concurrent.TimeUnit;
import java.util.concurrent.locks.StampedLock;

/**
 * @author gxl
 * @description
 * @createDate 2022/7/13 20:25
 */
public class StampedLockDemo {

    static int number = 37;

    static StampedLock stampedLock = new StampedLock();

    public void write() {
        long stamped = stampedLock.writeLock();
        System.out.println(Thread.currentThread().getName()+"\t写线程准备修改");
        try {
            number += 13;
        }finally {
            stampedLock.unlockWrite(stamped);
        }
        System.out.println(Thread.currentThread().getName()+"\t写线程结束修改");
    }

    // 悲观读，读没有完成时候写锁无法获得锁
    public void read() {
        long stamped = stampedLock.readLock();
        System.out.println(Thread.currentThread().getName()+"\tcome in readlock codeblock, 4 sec continue");
        for (int i = 0; i < 4; i++) {
            try {
                TimeUnit.SECONDS.sleep(1);
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
            System.out.println(Thread.currentThread().getName()+"\t正在读取中...");
        }

        try {
            int result = number;
            System.out.println(Thread.currentThread().getName()+"\t获得成员变量值result: " + result);
            System.out.println("写线程没有修改成功，读锁时候写锁无法介入，传统的读写互斥");
        }finally {
            stampedLock.unlockRead(stamped);
        }
    }

    // 乐观读，读的过程中也允许获取写锁介入
    public void tryOptimisticRead() {
        long stamped = stampedLock.tryOptimisticRead();
        int result = number;
        // 故意间隔4秒钟，很乐观认为读取中没有其他线程修改过number值，具体靠判断
        System.out.println("4sec 前stampedLock.validate方法值（true无修改，false有人改过）\t"+stampedLock.validate(stamped));
        for (int i = 0; i < 4; i++) {
            try {
                TimeUnit.SECONDS.sleep(1);
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
            System.out.println(Thread.currentThread().getName()+"\t正在读取..."+i+"sec"
                            +"后stampedLock.validate方法值（true无修改，false有人改过）\t"+stampedLock.validate(stamped));
        }
        if(!stampedLock.validate(stamped)) {
            System.out.println("有人修改过------有写操作");
            stamped = stampedLock.readLock();
            try {
                System.out.println("从乐观读 升级为 悲观读");
                result = number;
                System.out.println("重新悲观读后result："+result);
            }finally {
                stampedLock.unlockRead(stamped);
            }
        }
        System.out.println(Thread.currentThread().getName()+"\t finally value: "+result);
    }

    public static void main(String[] args) throws InterruptedException {
        StampedLockDemo resource = new StampedLockDemo();

        // 传统版
        /*new Thread(() -> {
            resource.read();
        }, "readThread").start();

        TimeUnit.SECONDS.sleep(1);

        new Thread(() -> {
            System.out.println(Thread.currentThread().getName()+"\t---come in");
            resource.write();
        }, "writeThread").start();*/

        new Thread(() -> {
            resource.tryOptimisticRead();
        }, "readThread").start();

        // 读过程可以写介入，演示
        TimeUnit.SECONDS.sleep(2);

        // 写无介入
//        TimeUnit.SECONDS.sleep(6);

        new Thread(() -> {
            System.out.println(Thread.currentThread().getName()+"\t---come in");
            resource.write();
        }, "writeThread").start();
    }
}
