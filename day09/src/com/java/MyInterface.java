package com.java;

/**
 * @author SunHang
 * @className: MyInterface
 * @description:
 * @createTime 2021/3/25 19:00
 */
public interface MyInterface {
    // 省略了public static final(常量)
    int NUM = 100;
    // 省略了public abstract
    void a();

    // 普通功能方法
    default void demo(String str){
        System.out.println("MyInterface----demo---Str:"+str);
    }
    //静态方法
    static void demo1(){
        System.out.println("MyInterface---demo1---Static");
    }



}

