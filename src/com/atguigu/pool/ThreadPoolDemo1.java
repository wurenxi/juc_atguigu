package com.atguigu.pool;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/**
 * @author gxl
 * @description
 * @createDate 2021/11/8 11:33
 */
public class ThreadPoolDemo1 {
    public static void main(String[] args) {
        // 一池五线程
//        ExecutorService threadPool1 = Executors.newFixedThreadPool(5);

        // 一池一线程
//        ExecutorService threadPool2 = Executors.newSingleThreadExecutor();

        // 一池可扩容线程
        ExecutorService threadPool3 = Executors.newCachedThreadPool();

        // 10个顾客请求
        try {
            for (int i = 0; i < 20; i++) {
                // 执行
                threadPool3.execute(() -> {
                    System.out.println(Thread.currentThread().getName()+" 办理业务");
                });
            }
        }catch (Exception e){
            e.printStackTrace();
        }finally {
            // 关闭
            threadPool3.shutdown();
        }

    }
}
