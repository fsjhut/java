package com.callable.java;

import java.util.concurrent.*;

/**
 * @className: ThreadPooleTest
 * @description:   
 * @author SunHang
 * @createTime 2021/4/8 22:10
 */
public class ThreadPooleTest {
    public static void main(String[] args) {
        Executors.newFixedThreadPool(3);// 初始化的线程数量
        Executors.newScheduledThreadPool(4);// 线程池里得空闲数量超出4，则会在指定时间销毁

        ThreadPoolExecutor executor =
                new ThreadPoolExecutor(5,20,400,
                        TimeUnit.MILLISECONDS,new ArrayBlockingQueue<>(30),
                        Executors.defaultThreadFactory(),new ThreadPoolExecutor.CallerRunsPolicy());
//        ExecutorService executorService = Executors.newFixedThreadPool(5);
        MoneyCallable2 callable2 = new MoneyCallable2();
        executor.submit(()->{
            callable2.money(300,false);
            return MoneyCallable2.balance;
        });
        executor.submit(()->{
            callable2.money(1000,true);
            return MoneyCallable2.balance;
        });
        executor.shutdown();


    }
}
