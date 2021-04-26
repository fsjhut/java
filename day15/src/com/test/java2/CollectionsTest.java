package com.test.java2;

import org.junit.Test;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

/**
 * @className: CollectionsTest
 * @description:   
 * @author SunHang
 * @createTime 2021/4/4 18:42
 */
public class CollectionsTest {
    @Test
    public void test1(){
        List list = new ArrayList();
        list.add(123);
        list.add(34);
        list.add(765);
        list.add(34);
        list.add(0);
        // copy
//        List dest = new ArrayList();
//        Collections.copy(dest,list);
        List dest = Arrays.asList(new Object[list.size()]);
        Collections.copy(dest,list);
        System.out.println(dest);
        /**
         * Collections类中提供了多个的synchronizedXxxx()方法
         * 该方法可以将指定集合包装成线程同步的集合,从而解决
         * 多线程并发访问集合时的线程安全问题
         * 返回的list1就是一个线程安全的List
         */
        List list1 = Collections.synchronizedList(list);
        System.out.println(list1);
        Collections.reverse(list1);
        Collections.shuffle(list1);
        Collections.sort(list1);
        Collections.swap(list1,1,2);
        System.out.println(Collections.frequency(list1, 34));
        System.out.println(list1);
    }
}
