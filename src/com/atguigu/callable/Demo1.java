package com.atguigu.callable;

import java.util.concurrent.Callable;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.FutureTask;

/**
 * @author gxl
 * @description 比较两个接口
 * @createDate 2021/11/7 11:32
 */
// 实现Runnable接口
class MyThread1 implements Runnable{

    @Override
    public void run() {

    }
}

// 实现Callable接口
class MyThread2 implements Callable{

    @Override
    public Integer call() throws Exception {
        return 200;
    }
}

public class Demo1 {
    public static void main(String[] args) throws ExecutionException, InterruptedException {
        // Runnable接口创建线程
        new Thread(new MyThread1(),"AA").start();

        // Callable接口
        // FutureTask
        FutureTask<Integer> futureTask1 = new FutureTask<>(new MyThread2());

        // Lambda表达式
        FutureTask<Integer> futureTask2 = new FutureTask<>(() -> {
            System.out.println(Thread.currentThread().getName()+" come in");
            return 1024;
        });

        // 创建一个线程
        new Thread(futureTask2,"lucy").start();
        new Thread(futureTask1,"marry").start();

//        while (!futureTask2.isDone()){
//            System.out.println("wait......");
//        }

        // 调用FutureTask的get方法
        System.out.println(futureTask2.get());

        System.out.println(futureTask2.get());

        System.out.println(Thread.currentThread().getName()+" come over");
    }
}
