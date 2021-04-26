package com.test.exec1;

import org.junit.Test;

import java.util.*;
import java.util.function.Consumer;

/**
 * @className: CollectionTest
 * @description:   
 * @author SunHang
 * @createTime 2021/4/4 20:30
 */
public class CollectionTest {
    @Test
    public void test1(){
        Collection coll = new ArrayList();
        coll.add(123);
        coll.add(456);
        coll.add(343);
        coll.add(343);
        coll.add(768);
        // lambda表达式
        coll.forEach(str-> System.out.print(str+"\t"));
        System.out.println();
        // 匿名对象
        coll.forEach(new Consumer() {
            @Override
            public void accept(Object o) {
                System.out.print(o+"\t");
            }
        });
        // 函数的引用
        coll.forEach(System.out::println);
    }
    //在List内去除重复的数字值,要求尽量简单
    public static List duplicateList(List list){
        HashSet set = new HashSet();
        set.addAll(list);
        return new ArrayList(set);
    }
    @Test
    public void test2(){
        List list = new ArrayList();
        list.add(new Integer(1));
        list.add(new Integer(2));
        list.add(new Integer(2));
        list.add(new Integer(4));
        list.add(new Integer(4));
        List list2 = duplicateList(list);
        for (Object integer : list2) {
            System.out.println(integer);
        }
    }

    @Test
    public void test3(){
        HashSet set = new HashSet();
        Person p1 = new Person(1001,"AA");
        Person p2 = new Person(1002,"BB");

        set.add(p1);
        set.add(p2);
        System.out.println(set);

        p1.name = "CC";
        set.remove(p1);
        System.out.println(set);
        set.add(new Person(1001,"CC"));
        System.out.println(set);
        set.add(new Person(1001,"AA"));
        System.out.println(set);
    }

}
