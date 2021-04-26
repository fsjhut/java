package com.thread.java;

import java.io.FileInputStream;
import java.io.IOException;
import java.util.Properties;

/**
 * @author SunHang
 * @className: ThreadTest
 * @description:
 * @createTime 2021/4/8 17:31
 */
public class ThreadTest {
    public static void main(String[] args) {
//        test1();
//        test2();
        test3();
        Thread thread = Thread.currentThread();
        thread.setName("main线程");
        thread.setPriority(Thread.MAX_PRIORITY);
        System.out.println(thread);
        System.out.println(thread.getName());
        System.out.println(thread.getId());
        System.out.println(thread.getPriority());
        System.out.println(thread.getState());
    }

    public static void test1() {
        Properties properties = new Properties();
        try {
            properties.load(new FileInputStream("day19/src/a.properties"));
        } catch (IOException e) {
            e.printStackTrace();
        }
        properties.setProperty("uid","11111");
        properties.setProperty("uname","hang");
        properties.put("id", 1001);
        properties.put("name", "张三");
        properties.put("balance", 488766.97);
        System.out.println(properties);
        System.out.println(properties.getProperty("uid"));
    }
    public static void test2(){
        Class<ThreadTest> demoClass = ThreadTest.class;
        System.out.println(demoClass);
        Properties properties = new Properties();
        try{
            properties.load(demoClass.getClassLoader().getResourceAsStream("a.properties"));
        }catch (IOException e){
            e.printStackTrace();
        }
        System.out.println(properties);

    }

    public static void test3(){
        
    }

}
