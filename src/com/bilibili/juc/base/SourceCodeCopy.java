package com.bilibili.juc.base;

import org.junit.Test;

/**
 * @author gxl
 * @description
 * @createDate 2022/7/10 16:12
 */
public class SourceCodeCopy {

//    @Test
//    public void test1(){
//        // CASE1: cells已经被初始化了
//        if ((as = cells) != null && (n = as.length) > 0) {
//            if ((a = as[(n - 1) & h]) == null) { // 当前线程的hash值运算后映射得到的Cell单元为null，说明该Cell没有被使用
//                if (cellsBusy == 0) {       // Cell[]数组没有正在扩容
//                    Cell r = new Cell(x);   // 创建一个Cell单元
//                    if (cellsBusy == 0 && casCellsBusy()) { // 尝试加锁，成功后cellsBusy==1
//                        boolean created = false;
//                        try {               // 在有锁的情况下再检测一遍之前的判断
//                            Cell[] rs; int m, j;  // 将Cell单元赋到Cell[]数组上
//                            if ((rs = cells) != null &&
//                                    (m = rs.length) > 0 &&
//                                    rs[j = (m - 1) & h] == null) {
//                                rs[j] = r;
//                                created = true;
//                            }
//                        } finally {
//                            cellsBusy = 0;
//                        }
//                        if (created)
//                            break;
//                        continue;           // Slot is now non-empty
//                    }
//                }
//                collide = false;
//            }
//            else if (!wasUncontended)       // wasUncontended表示前一次CAS更新Cell单元是否成功了
//                wasUncontended = true;      // 重新置为true，后面会重新计算线程的hash值
//            else if (a.cas(v = a.value, ((fn == null) ? v + x : // 尝试CAS更新Cell单元值
//                    fn.applyAsLong(v, x))))
//                break;
//            else if (n >= NCPU || cells != as)  // 当Cell数组的大小超过CPU核数后，永远不会再进行扩容
//                collide = false;            // 扩容标识，置为false，表示不会再进程扩容
//            else if (!collide)
//                collide = true;
//            else if (cellsBusy == 0 && casCellsBusy()) {    // 尝试加锁进行扩容
//                try {
//                    if (cells == as) {      // Expand table unless stale
//                        Cell[] rs = new Cell[n << 1];   // 扩容后的大小 == 当前容量*2
//                        for (int i = 0; i < n; ++i)
//                            rs[i] = as[i];
//                        cells = rs;
//                    }
//                } finally {
//                    cellsBusy = 0;
//                }
//                collide = false;
//                continue;                   // Retry with expanded table
//            }
//            h = advanceProbe(h);    // 计算当前线程数的hash值
//        }
//    }
}
