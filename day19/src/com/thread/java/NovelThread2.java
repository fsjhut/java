package com.thread.java;

/**
 * @author SunHang
 * @className: NovelThread
 * @description:
 * @createTime 2021/4/8 21:11
 */
public class NovelThread2 implements Runnable {
    private String novelPath;
    private String targetFilePath;

    // 重写父类的run方法
    @Override
    public void run() {
        DownloadNovelUtil.downloadNovel(novelPath, targetFilePath);
        System.out.println("成功下载了："+targetFilePath);
    }

    public NovelThread2(String novelPath, String targetFilePath) {
        this.novelPath = novelPath;
        this.targetFilePath = targetFilePath;
    }
}
