package com.bilibili.juc.tl;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/**
 * @author gxl
 * @description
 * 【强制】必须回收自定义的ThreadLocal变量，尤其在线程池场景下，线程经常会被复用，如果不清理
 * 自定义的ThreadLocal变量，可能会影响后续业务逻辑和造成内存泄露等问题，尽量在代理中使用
 * try-finally块进行回收。
 * @createDate 2022/7/10 19:48
 */
public class ThreadLocalDemo02 {
    public static void main(String[] args) {
        MyData myData = new MyData();

        ExecutorService threadPool = Executors.newFixedThreadPool(3);

        try {
            for (int i = 0; i < 10; i++) {
                threadPool.submit(() -> {
                    try {
                        Integer beforeInt = myData.threadLocal.get();
                        myData.add();
                        Integer afterInt = myData.threadLocal.get();
                        System.out.println(Thread.currentThread().getName()+"\tbeforeInt："+beforeInt+"\tafterInt："+afterInt);
                    } finally {
                        myData.threadLocal.remove();
                    }
                });
            }
        }catch (Exception e) {
            e.printStackTrace();
        }finally {
            threadPool.shutdown();
        }
    }
}

class MyData {
    ThreadLocal<Integer> threadLocal = ThreadLocal.withInitial(() -> 0);

    public void add() {
        threadLocal.set(1 + threadLocal.get());
    }
}
