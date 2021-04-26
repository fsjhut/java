package com.java;
/**
 * @className: Myclass
 * @description:   
 * @author SunHang
 * @createTime 2021/3/25 19:01
 */
public class Myclass implements MyInterface{

    @Override
    public void a() {
        System.out.println("Myclass ----a-------"+NUM);
        MyInterface.super.demo("abc");
        demo("123");
        MyInterface.demo1();
    }
    // 调用demo
//    MyInterface.demo("abc");
    @Override
    public void demo(String str){
        System.out.println("MyClass-----重写的demo-----"+str);
    }

}
