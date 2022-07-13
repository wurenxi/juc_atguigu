package com.bilibili.juc.base;

/**
 * @author gxl
 * @description
 * @createDate 2022/7/7 16:27
 */
public class PlusPlusTest {

    public int i;

    public void plusplus() {
        i++;
    }

    public static void main(String[] args) {
        PlusPlusTest plusPlusTest = new PlusPlusTest();

        plusPlusTest.plusplus();
    }
}
