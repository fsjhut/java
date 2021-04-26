package com.callable.java;

import java.util.concurrent.FutureTask;

/**
 * @className: MoneyTest
 * @description:   
 * @author SunHang
 * @createTime 2021/4/8 21:53
 */
public class MoneyTest {
    public static void main(String[] args) {
        MoneyCallable callable1 = new MoneyCallable(500, false);
        MoneyCallable callable2 = new MoneyCallable(1000, true);

        // 利用Future接受程序返回的结果
        FutureTask<Integer> task1 = new FutureTask<>(callable1);
        FutureTask<Integer> task2 = new FutureTask<>(callable2);
        // task1是一个FutureTask对象
        // 该类实现了Runnable 和 Future接口
        Thread lisi = new Thread(task1, "李四");
        Thread wangwu = new Thread(task2, "王五");

        lisi.start();
        wangwu.start();
        // 进入阻塞的两种状态
//        try{
//            lisi.join();
//            wangwu.join();
//        }catch (InterruptedException e){
//            e.printStackTrace();
//        }
        // 进入阻塞得第二种方法
        try{
            System.out.println(task1.get());
            System.out.println(task2.get());
        }catch (Exception e){
            e.printStackTrace();
        }
        // 正常的编写方式
        MoneyCallable2 account = new MoneyCallable2();
        FutureTask<Integer> task3 = new FutureTask<>(()->{
            account.money(400,false);
            return MoneyCallable2.balance;
        });

        FutureTask<Integer> task4 = new FutureTask<Integer>(()->{
            account.money(500,true);
            return MoneyCallable2.balance;
        });

        Thread wifi = new Thread(task3, "wifi");
        Thread ww = new Thread(task4, "ww");
        wifi.start();
        ww.start();

    }
}
