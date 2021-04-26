package com.thread.exec2;

import java.util.ArrayList;
import java.util.concurrent.TimeUnit;

/**
 * @author SunHang
 * @className: TestCon
 * @description:
 * @createTime 2021/4/9 20:53
 */
public class TestCon {
    public static void main(String[] args) {
        ArrayList<Thread> product = new ArrayList<>(10);
        for (int i = 1; i <= 3; i++) {
            product.add(new Thread(() -> {
                while (true) {
                    ProductConsumer.produceStr();
                    try {
                        TimeUnit.MILLISECONDS.sleep(500);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }
            }, "生产者" + i));
        }
        ArrayList<Thread> consume = new ArrayList<>(10);
        for (int j = 1; j <= 5; j++) {
            consume.add(new Thread(() -> {
                while (true) {
                    ProductConsumer.consumStr();
                    try {
                        TimeUnit.MILLISECONDS.sleep(1000);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }
            }, "消费者" + j));
        }
        product.forEach(Thread::start);
        consume.forEach(Thread::start);
    }
}
