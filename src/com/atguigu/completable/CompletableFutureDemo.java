package com.atguigu.completable;

import java.util.concurrent.CompletableFuture;

/**
 * @author gxl
 * @description
 * @createDate 2021/11/8 13:06
 */
public class CompletableFutureDemo {
    public static void main(String[] args) throws Exception{
        CompletableFuture<Void> completableFuture1 = CompletableFuture.runAsync(()->{
            System.out.println(Thread.currentThread().getName()+"completableFuture2");
        });

        completableFuture1.get();

        CompletableFuture<Integer> completableFuture2 = CompletableFuture.supplyAsync(() -> {
            System.out.println(Thread.currentThread().getName()+"completableFuture2");

            // 模拟异常
            int i = 10/0;

            return 1024;
        });

        completableFuture2.whenComplete((t,u) -> {
            System.out.println("----t="+t); // 返回值
            System.out.println("----u="+u); // 异常
        }).get();
    }
}
