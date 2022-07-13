package com.bilibili.juc.base;

import org.junit.Test;

import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.TimeUnit;

/**
 * @author gxl
 * @description
 * @createDate 2022/7/8 10:37
 */
public class VolatileTest {

    @Test
    public void testEntry(){
        Map<String, Object> map = new HashMap<>();
        map.put("1", "a");
        map.put("2", "b");
        map.put("3", "v");
        map.put(null, "v");

        for (Map.Entry<String, Object> entry : map.entrySet()) {
            System.out.println(entry.getKey());
        }

    }
}
