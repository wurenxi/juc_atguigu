package com.bilibili.juc.tl;

import java.lang.ref.*;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.TimeUnit;

/**
 * @author gxl
 * @description
 * @createDate 2022/7/10 22:06
 */
public class ReferenceDemo {
    public static void main(String[] args) {
        MyObject myObject = new MyObject();
        ReferenceQueue<MyObject> referenceQueue = new ReferenceQueue<>();
        PhantomReference<MyObject> phantomReference = new PhantomReference<>(myObject, referenceQueue);
//        System.out.println(phantomReference.get());

        List<byte[]> list = new ArrayList<>();

        new Thread(() -> {
            while (true) {
                list.add(new byte[1 * 1024 * 1024]);    // -Xms10m -Xmx10m
                try {
                    TimeUnit.MILLISECONDS.sleep(500);
                } catch (InterruptedException e) {
                    throw new RuntimeException(e);
                }
                System.out.println(phantomReference.get()+"\tlist add ok");
            }
        }, "t1").start();

        new Thread(() -> {
            while (true) {
                Reference<? extends MyObject> reference = referenceQueue.poll();
                if(reference != null) {
                    System.out.println("---有虚对象回收加入了队列");
                    break;
                }
            }
        }, "t2").start();
    }

    private static void weakReference() {
        WeakReference<MyObject> weakReference = new WeakReference<>(new MyObject());
        System.out.println("---gc before 内存够用：" + weakReference.get());

        System.gc();
        try {
            TimeUnit.SECONDS.sleep(1);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
        System.out.println("---gc after 内存够用：" + weakReference.get());
    }

    private static void softReference() {
        SoftReference<MyObject> softReference = new SoftReference<>(new MyObject());
//        System.out.println("---softReference: " + softReference.get());

        System.gc();
        try {
            TimeUnit.SECONDS.sleep(1);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
        System.out.println("---gc after内存够用: " + softReference.get());

        try {
            byte[] bytes = new byte[20 * 1024 * 1024];  // 20MB对象 提前设置好堆空间 -Xms20m -Xmx20m
        }catch (Exception e) {
            e.printStackTrace();
        }finally {
            System.out.println("---gc after内存不够用: " + softReference.get());
        }
    }

    private static void strongReference() {
        MyObject myObject = new MyObject();
        System.out.println("gc before: " + myObject);

        myObject = null;
        System.gc();    // 人工开启GC，一般不用
        System.out.println("gc after: " + myObject);
    }
}

class MyObject {

    // 这个方法一般不用重写，只是演示说明
    @Override
    protected void finalize() throws Throwable {
        // finalize的通常目的是在对象被不可撤销地丢弃之前执行清理操作。
        System.out.println("---invoke finalize method");
    }
}
