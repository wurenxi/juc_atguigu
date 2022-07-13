package com.bilibili.juc.volatiles;

import java.util.concurrent.TimeUnit;

/**
 * @author gxl
 * @description
 * @createDate 2022/7/7 14:04
 */
public class VolatileSeeDemo {

//    static boolean flag = true;
    static volatile boolean flag = true;

    public static void main(String[] args) {
        new Thread(() -> {
            System.out.println(Thread.currentThread().getName()+"\t---come in");
            while (flag) {

            }
            System.out.println(Thread.currentThread().getName()+"\t---flag被设置为false，程序停止");
        }, "t1").start();

        try {
            TimeUnit.SECONDS.sleep(2);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }

        flag = false;

        System.out.println(Thread.currentThread().getName()+"\t修改完成 flag：" + flag);
    }
}
