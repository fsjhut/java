package com.thread.java;
/**
 * @className: DownloadTest
 * @description:   
 * @author SunHang
 * @createTime 2021/4/8 21:15
 */
public class DownloadTest {
    public static void main(String[] args) {
        String path1 = "https://read.qidian.com/chapter/BqyZ35Utv4wmPxgcqNL9uQ2/-AE0MuwfvmNMs5iq0oQwLQ2";
        String path2 = "https://read.qidian.com/chapter/xHWZgTS9bW951l-ULjeaVw2/7mOjl_Mc3WfM5j8_3RRvhw2";
        String path3 = "https://read.qidian.com/chapter/fbBpHOc_0dhTPkn-OiZztA2/1hu0g1Tk3PC2uJcMpdsVgA2";
//        NovelThread thread1 = new NovelThread("A线程", path1, "day19/novel/a.txt");
//        NovelThread thread2 = new NovelThread("B线程", path2, "day19/novel/b.txt");
//        NovelThread thread3 = new NovelThread("C线程", path3, "day19/novel/c.txt");
        // 实现接口的方法
//        NovelThread2 novelThread1 = new NovelThread2(path1, "day19/novel/aa.txt");
//        NovelThread2 novelThread2 = new NovelThread2(path2, "day19/novel/bb.txt");
//        NovelThread2 novelThread3 = new NovelThread2(path3, "day19/novel/cc.txt");
//        Thread thread1 = new Thread( novelThread1, "A线程");
//        Thread thread2 = new Thread( novelThread2, "B线程");
//        Thread thread3 = new Thread( novelThread3, "C线程");
//        thread1.start();
//        thread2.start();
//        thread3.start();
        // 正常编写，大部分人用的方法
        NovelThread3 novelThread1 = new NovelThread3(path1, "day19/novel/aaa.txt");
        NovelThread3 novelThread2 = new NovelThread3(path2, "day19/novel/bbb.txt");
        NovelThread3 novelThread3 = new NovelThread3(path3, "day19/novel/ccc.txt");
        Thread thread5 = new Thread(new Runnable() {
            @Override
            public void run() {
                novelThread1.downLoad();
            }
        },"er");

        Thread thread = new Thread(() -> { novelThread1.downLoad(); },"dsdf");
        Thread thread1 = new Thread(novelThread1::downLoad,"A线程");
        Thread thread2 = new Thread(novelThread2::downLoad,"B线程");
        Thread thread3 = new Thread(novelThread3::downLoad,"C线程");
        thread1.start();
        thread2.start();
        thread3.start();

    }
}
