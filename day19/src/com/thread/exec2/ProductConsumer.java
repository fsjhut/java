package com.thread.exec2;

import java.util.Vector;
import java.util.concurrent.TimeUnit;

/**
 * @author SunHang
 * @className: ProductConsum
 * @description:
 * @createTime 2021/4/9 20:46
 */
public class ProductConsumer {
    private static Vector<String> pool = new Vector<>(11);

    public static synchronized void produceStr() {
        StringBuilder stringBuilder = new StringBuilder();
        int num = (int) (Math.random() * 5+5);
        try {
            for (int i = 0; i < num; i++) {
                char temp;
                if ((int) (Math.random() * 2) == 0) {
                    temp = (char) (Math.random() * 26 + 65);
                } else {
                    temp = (char) (Math.random() * 26 + 97);
                }
                stringBuilder.append(temp);
            }
            while (pool.size() == 30) {
                ProductConsumer.class.wait();
            }
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
//        try {
//            TimeUnit.MILLISECONDS.sleep(1000);
//        } catch (InterruptedException e) {
//            e.printStackTrace();
//        }
        pool.add(stringBuilder.toString());
        ProductConsumer.class.notify();
        System.out.println(Thread.currentThread().getName() + "生产了字符串：<"+stringBuilder+">,池子里面剩余:" + pool.size());
    }


    public static synchronized void consumStr() {
        try {
            while (pool.size() == 0) {
                ProductConsumer.class.wait();
            }
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        String str = pool.remove(0);
        ProductConsumer.class.notify();
        System.out.println(Thread.currentThread().getName() + "消费了字符串<"+str+">,池子里面剩余:" + pool.size());
    }
}
