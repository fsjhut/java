package com.test.java2;

import lombok.val;
import org.junit.Test;

import java.util.*;

/**
 * @className: TreeMapTest
 * @description:   
 * @author SunHang
 * @createTime 2021/4/4 19:14
 */
public class TreeMapTest {
    @Test
    public void test1(){
        TreeMap map = new TreeMap();
        User u1 = new User("Tom",22);
        User u2 = new User("Jerry",23);
        User u3 = new User("Jack",21);
        User u4 = new User("Sun",24);
        User u5 = new User("Hang",18);
        map.put(u1,98);
        map.put(u2,29);
        map.put(u3,89);
        map.put(u4,55);
        map.put(u5,77);
        Set entrySet = map.entrySet();
        Iterator iterator = entrySet.iterator();
        while (iterator.hasNext()){
            Object obj = iterator.next();
            Map.Entry entry = (Map.Entry)obj;
            System.out.println(entry.getKey()+"----->"+entry.getValue());
        }
    }
    // 定制排序
    @Test
    public void test2(){
        Map<User,Integer> map = new TreeMap<>(new Comparator() {

            @Override
            public int compare(Object o1, Object o2) {
                if (o1 instanceof User && o2 instanceof User) {
                    User u1 = (User) o1;
                    User u2 = (User) o2;
                    return Integer.compare(u1.getAge(), u2.getAge());
                }
                throw new RuntimeException("输入类型不匹配");
            }
        });
//        Map<User,Integer> map = (User u1,User u2)-> { Integer.compare(u2.getAge(), u1.getAge());};

        User u1 = new User("Tom",22);
        User u2 = new User("Jerry",23);
        User u3 = new User("Jack",21);
        User u4 = new User("Sun",24);
        User u5 = new User("Hang",18);
        map.put(u1,98);
        map.put(u2,29);
        map.put(u3,89);
        map.put(u4,55);
        map.put(u5,77);
        Set entrySet = map.entrySet();
        Iterator iterator = entrySet.iterator();
        while (iterator.hasNext()){
            Object obj = iterator.next();
            Map.Entry entry = (Map.Entry)obj;
            System.out.println(entry.getKey()+"----->"+entry.getValue());
        }
    }
}
