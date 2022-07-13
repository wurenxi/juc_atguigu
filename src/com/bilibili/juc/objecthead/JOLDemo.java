package com.bilibili.juc.objecthead;

import org.openjdk.jol.info.ClassLayout;
import org.openjdk.jol.vm.VM;

/**
 * @author gxl
 * @description
 * @createDate 2022/7/11 12:09
 */
public class JOLDemo {
    public static void main(String[] args) {
        Object o = new Object(); // 16 bytes

        System.out.println(ClassLayout.parseInstance(o).toPrintable());

//        Customer c1 = new Customer(); // 16 bytes
//        System.out.println(ClassLayout.parseInstance(c1).toPrintable());

    }
}

class Customer {    // 只有一个对象头的实例对象，16字节（忽略压缩指针的影响）+4字节+1字节=21字节-->对齐填充：24字节
    // 1.第一种情况，只有对象头，没有其他任何实例数据

    // 2.第二种情况，int + boolean，默认满足对齐填充：24byte
//    int id;
//    boolean flag = false;
}

/*
1.默认配置，启动了压缩指针：-XX:+UseCompressedClassPointers
    12(8 + 4) + 4（对齐填充）= 一个对象16字节

2.手动配置，关闭压缩指针，-XX:-UseCompressedClassPointers
    8 + 8 = 一个对象16字节（无对齐填充）
 */
