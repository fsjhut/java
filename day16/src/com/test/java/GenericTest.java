package com.test.java;

import org.junit.Test;

import java.util.ArrayList;
import java.util.Iterator;

/**
 * @className: GenericTest
 * @description:   
 * @author SunHang
 * @createTime 2021/4/3 22:03
 */
public class GenericTest {
    @Test
    public void test1(){
        ArrayList list = new ArrayList();
        list.add(78);
        list.add(76);
        list.add(88);
        list.add(89);
        // 编译时类型不安全
//        list.add("Tom");
        for(Object score:list){
            // 强制转换时出现ClassCastException
            int stuScore = (int)score;
            System.out.println(stuScore);
        }

    }
    // 在集合中加入泛型
    @Test
    public void test2(){
        ArrayList<Integer> arrayList = new ArrayList<>();
        // 使用泛型时，不能使用基本数据类型，因此需要使用包装类
        arrayList.add(44);
        arrayList.add(77);
        arrayList.add(88);
        arrayList.add(55);
        // 编译时会进行类型检查，保证数据的安全
//        arrayList.add("dfwr");
        for(Integer score:arrayList){
            // 避免了强制转换的操作
            int stuScore = score;
            System.out.println(stuScore);
        }
        Iterator<Integer> iterator = arrayList.iterator();
        while(iterator.hasNext()){
            System.out.println(iterator.next());
        }
        // 如果定义了泛型类，实例化时没有指明类的泛型，则认为此泛型类型为Object
        // 如果大家定义了类是带泛型，则在实例化时，指明类的泛型。
        // 子类在继承带泛型的父类时，指明了泛型类型，则
        // 泛型不同的引用不能相互赋值
        // 泛型的使用一路都用，或者都不用
        // 在静态方法中不能使用泛型
        // 异常类不能声明为泛型类
        // T[] arr = new T[10];不能通过
        // T[] arr = (T[]) new Object[10];可以通过
    }
}
