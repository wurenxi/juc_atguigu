package com.atguigu.pool;

import java.util.concurrent.*;

/**
 * @author gxl
 * @description 自定义线程池创建
 * @createDate 2021/11/8 12:15
 */
public class ThreadPoolDemo2 {
    public static void main(String[] args) {
        ExecutorService threadPool = new ThreadPoolExecutor(
                2,
                5,
                2L,
                TimeUnit.SECONDS,
                new ArrayBlockingQueue<>(3),
                Executors.defaultThreadFactory(),
                new ThreadPoolExecutor.AbortPolicy()
        );
    }
}
