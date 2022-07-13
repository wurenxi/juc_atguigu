package com.bilibili.juc.atomics;

import java.util.concurrent.TimeUnit;
import java.util.concurrent.atomic.AtomicMarkableReference;

/**
 * @author gxl
 * @description
 * @createDate 2022/7/9 21:35
 */
public class AtomicMarkableReferenceDemo {

    static AtomicMarkableReference markableReference = new AtomicMarkableReference(100, false);

    public static void main(String[] args) {
        new Thread(() -> {
            boolean marked = markableReference.isMarked();
            System.out.println(Thread.currentThread().getName()+"\t默认标识：" + marked);
            // 等待后面的t2线程拿到一样的flag标识
            try {
                TimeUnit.SECONDS.sleep(1);
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
            markableReference.compareAndSet(100, 1000, marked, !marked);
        }, "t1").start();

        new Thread(() -> {
            boolean marked = markableReference.isMarked();
            System.out.println(Thread.currentThread().getName()+"\t默认标识：" + marked);
            try {
                TimeUnit.SECONDS.sleep(2);
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
            boolean b = markableReference.compareAndSet(100, 1000, marked, !marked);
            System.out.println(Thread.currentThread().getName()+"\tt2线程CAS result：" + b);
            System.out.println(markableReference.isMarked());
            System.out.println(markableReference.getReference());
        }, "t2").start();
    }
}

/*
CAS --- Unsafe --- do while + ABA --- AtomicStampedReference, AtomicMarkableReference

AtomicStampedReference, version号, +1
AtomicMarkableReference, 一次, false/true
 */
