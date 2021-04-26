package com.thread.exec1;

import com.thread.java.ThreadTest;

/**
 * @author SunHang
 * @className: ThreadTest
 * @description:
 * @createTime 2021/4/8 22:33
 */
public class Runnable {
    public  static volatile int testNum;
    public static void main(String[] args) {
//        Thread thread1 = new Thread(Runnable::test1, "线程1");
        Thread thread2 = new Thread(Runnable::test2, "线程2");

//        thread1.start();
        thread2.start();
        for (int i = 0; i < 5; i++) {
            System.out.println(Thread.currentThread().getName());
            try {
                Thread.sleep(1000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }


    public  static void test1() {
        for (int i = 0; i < 5; i++) {
            testNum++;
            System.out.println(testNum);
            System.out.println(Thread.currentThread().getName());
            try {
                Thread.sleep(1000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }

    public static void test2() {
        for (int i = 0; i < 5; i++) {
            System.out.println(Thread.currentThread().getName());
            try {
                Thread.sleep(1000);
//                ThreadLocal
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }
}
