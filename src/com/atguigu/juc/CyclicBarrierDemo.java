package com.atguigu.juc;

import java.util.concurrent.BrokenBarrierException;
import java.util.concurrent.CountDownLatch;
import java.util.concurrent.CyclicBarrier;

/**
 * @author gxl
 * @description
 * @createDate 2021/11/7 12:42
 */
public class CyclicBarrierDemo {
    // 创建固定值
    private static final int NUMBER = 7;

    public static void main(String[] args) {
        // 创建CyclicBarrier
        CyclicBarrier cyclicBarrier = new CyclicBarrier(NUMBER,() -> {
            System.out.println("集齐7颗龙珠就可以召唤神龙");
        });

        for (int i = 1; i <= 7; i++) {
            new Thread(() ->{
                try {
                    System.out.println(Thread.currentThread().getName()+"星龙被收集到了");

                    // 等待
                    cyclicBarrier.await();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                } catch (BrokenBarrierException e) {
                    e.printStackTrace();
                }
            },String.valueOf(i)).start();
        }

        // 减少计数实现
//        CountDownLatch countDownLatch = new CountDownLatch(7);
//
//        for (int i = 1; i <= 7; i++) {
//            new Thread(() -> {
//                System.out.println(Thread.currentThread().getName()+"星龙被收集到了");
//                countDownLatch.countDown();
//            }, String.valueOf(i)).start();
//        }
//
//        try {
//            countDownLatch.await();
//        } catch (InterruptedException e) {
//            e.printStackTrace();
//        }
//
//        System.out.println("集齐7颗龙珠就可以召唤神龙");
    }
}
