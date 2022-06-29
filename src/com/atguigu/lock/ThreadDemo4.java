package com.atguigu.lock;

import java.util.*;
import java.util.concurrent.CopyOnWriteArrayList;
import java.util.concurrent.CopyOnWriteArraySet;

/**
 * @author gxl
 * @description List集合线程不安全
 * @createDate 2021/11/6 10:27
 */
public class ThreadDemo4 {
    public static void main(String[] args) {
        // 创建ArrayList集合
//        List<String> list = new ArrayList<>();
        // Vector解决
//        List<String> list = new Vector<>();

        // Collections解决
//        List<String> list = Collections.synchronizedList(new ArrayList<>());

        // CopyOnWriteArrayList解决
//        List<String> list = new CopyOnWriteArrayList<>();
//
//        for (int i = 0; i < 30; i++) {
//            new Thread(() -> {
//                // 向集合添加内容
//                list.add(UUID.randomUUID().toString().substring(0,5));
//
//                // 从集合获取内容
//                System.out.println(list);
//            },String.valueOf(i)).start();
//        }

        // 演示Hashset
//        Set<String> set = new HashSet<>();
        Set<String> set = new CopyOnWriteArraySet<>();
        for (int i = 0; i < 30; i++) {
            new Thread(() -> {
                // 向集合添加内容
                set.add(UUID.randomUUID().toString().substring(0,5));
                // 从集合获取内容
                System.out.println(set);
            },String.valueOf(i)).start();
        }
    }
}
