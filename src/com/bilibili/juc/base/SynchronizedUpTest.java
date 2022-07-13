package com.bilibili.juc.base;

import org.openjdk.jol.info.ClassLayout;

import java.util.Random;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;

/**
 * @author gxl
 * @description
 * @createDate 2022/7/12 11:14
 */
public class SynchronizedUpTest {
    public static void main(String[] args) {

        Object o = new Object();

        ExecutorService threadPool = Executors.newFixedThreadPool(5);

        for (int i = 0; i < 10; i++) {
            threadPool.execute(() -> {
                synchronized (o) {
                    System.out.println("芜湖");
                    System.out.println(ClassLayout.parseInstance(o).toPrintable());
                }
            });
        }
    }
}
