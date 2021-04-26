package com.test.exec1;

import org.junit.Test;

import java.util.*;

/**
 * @className: GenericTest
 * @description:   
 * @author SunHang
 * @createTime 2021/4/4 21:21
 */
public class GenericTest {
    @Test
    public void test1(){
        Map<String,Integer> map = new HashMap<>();
        map.put("Tom",55);
        map.put("Jerry",77);
        map.put("Jack",88);
        // 泛型的嵌套
        Set<Map.Entry<String, Integer>> entries = map.entrySet();
        Iterator<Map.Entry<String, Integer>> iterator = entries.iterator();
        while(iterator.hasNext()){
            Map.Entry<String, Integer> next = iterator.next();
            String key = next.getKey();
            Integer value = next.getValue();
            System.out.println(key+"------->"+value);


        }

    }

    @Test
    public void test2(){
        List<Object> list1 = null;
        List<String> list2 = null;
        List<?> list = null;
        System.out.println(list1);
        ArrayList<String> list3 = new ArrayList<>();
        list3.add("AA");
        list3.add("BB");
        list3.add("CC");
        list = list3;
        //添加(写入)：对于List<?>就不能向其内部添加数据。
        //除了添加null之外。
//        list.add("DD");
//        list.add('?');
        list.add(null);
        //获取(读取)：允许读取数据，读取的数据类型为Object。
        System.out.println(list.get(0));
    }
    @Test
    public void test3(){
        /**
        3.有限制条件的通配符的使用。
        ? extends A:
                G<? extends A> 可以作为G<A>和G<B>的父类，其中B是A的子类

         ? super A:
                G<? super A> 可以作为G<A>和G<B>的父类，其中B是A的父类
                */
        List<? extends Person> list1 = null; // <=Person
        List<? super Person> list2 = null;   // >=Person
        List<Student> list3 = new ArrayList<>();
        List<Person> list4 = new ArrayList<>();
        List<Object> list5 = new ArrayList<>();
        list1 = list3;
        list1 = list4;
//        list1 = list5;
        list2 = list4;
//        list2 = list3;
        list2 = list5;
        //读取数据：
        list1 = list3;
        Person p = list1.get(0);

        list2 = list4;
        Object obj = list2.get(0);

        list2.add(new Person());
        list2.add(new Student());

    }
}
