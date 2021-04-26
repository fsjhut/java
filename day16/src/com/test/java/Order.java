package com.test.java;

import java.util.ArrayList;
import java.util.List;

/**
 * @className: Order
 * @description:   
 * @author SunHang
 * @createTime 2021/4/4 10:47
 */
public class Order {
    // 泛型方法：在方法中出现了泛型的结构
    // 泛型方法所属的类是不是泛型类都没关系
    // 泛型类和泛型方法
    // DAO：date(base) access object
    // 增删改查  ORM思想，一张表对应一个类
    // public class DAO<T>
    // public class CustomerDAO expends DAO<Customer>{}
    // 泛型在继承方面的体现
    // 通配符的使用
    // 类A是类B的一个父类，G<A> 和G<B>是并列关系，没有子父类的关系
    // 类A是类B的一个父类，A<G> 是B<G>的父类
    public static <E> List<E> copyFrom(E[] arr){
        ArrayList<E> list = new ArrayList<>();
        for(E e:arr){
            list.add(e);
        }
        return list;
    }
}
