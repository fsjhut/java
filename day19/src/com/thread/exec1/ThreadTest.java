package com.thread.exec1;

/**
 * @author SunHang
 * @className: RunnableTest
 * @description:
 * @createTime 2021/4/8 22:34
 */
public class ThreadTest extends Thread {

//    public static final Runnable runnable = new Runnable();

    public static void main(String[] args) {
        ThreadTest threadTest = new ThreadTest();
        ThreadTest threadTest2 = new ThreadTest();

        threadTest.start();
        threadTest2.start();
//        synchronized (threadTest) {
//            if(Runnable.testNum != 0) {  // 主线程拿不到testNum的最新值，因此该条件永远无法满足
//                for (int i = 0; i < 5; i++) {
//                    System.out.println(Thread.currentThread().getName());
//                    //            System.out.println();
//                    try {
//                        //                ThreadTest.class.wait(1000);
//                        Thread.sleep(1000);
//                    } catch (InterruptedException e) {
//                        e.printStackTrace();
//                    }
//                }
//            }
//        }
        // 主线程拿不到testNum的最新值，因此该条件永远无法满足
        for (int i = 0;; i++) {
            System.out.println(Runnable.testNum);
            if (Runnable.testNum != 0) {
                System.out.println(Thread.currentThread().getName());
                //            System.out.println();
                try {
                    //                ThreadTest.class.wait(1000);
                    Thread.sleep(1000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }

        }

    }

    @Override
    public void run() {
//        System.out.println(Thread.currentThread().getName());
        Runnable.test1();
    }
}

