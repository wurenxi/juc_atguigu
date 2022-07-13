package com.bilibili.juc.cf;

import java.util.concurrent.ExecutionException;
import java.util.concurrent.FutureTask;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.TimeoutException;

/**
 * @author gxl
 * @description
 * @createDate 2022/6/29 17:57
 */
public class FutureAPIDemo {
    public static void main(String[] args) throws ExecutionException, InterruptedException, TimeoutException {
        FutureTask<String> futureTask = new FutureTask<String>(() -> {
            System.out.println(Thread.currentThread().getName()+"\t ---come in");
            TimeUnit.SECONDS.sleep(5);
            return "task over";
        });

        Thread t1 = new Thread(futureTask, "t1");
        t1.start();

        System.out.println(Thread.currentThread().getName()+"\t --忙其他任务");

//        System.out.println(futureTask.get()); // 阻塞
//        System.out.println(futureTask.get(3, TimeUnit.SECONDS)); // TimeoutException

        while (true) {
            if(futureTask.isDone()) {
                System.out.println(futureTask.get());
                break;
            }else{
                TimeUnit.MILLISECONDS.sleep(500);
                System.out.println("正在处理中，不要再催了，越催越慢，再催熄火");
            }
        }
    }
}

/*
1.get容易导致阻塞，一般建议放在程序后面，一旦调用，容易程序阻塞
2.假如不愿意等待很长时间，希望过时不候，可以自动离开
 */
