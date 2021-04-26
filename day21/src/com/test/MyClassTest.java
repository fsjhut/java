package com.test;
/**
 * @className: MyClassTest
 * @description:   
 * @author SunHang
 * @createTime 2021/4/13 18:42
 */
public class MyClassTest {
    public static void main(String[] args) {
        MyClass myClass = new MyClass();
        myClass.setS(100);
        Object s = myClass.getS();
        MyClass<String, Integer> myClass1 = new MyClass<>();
        myClass1.setS("saf");
        System.out.println(myClass1.getS());
        myClass1.setT(2341);
        System.out.println(myClass1.getT());
    }
}
