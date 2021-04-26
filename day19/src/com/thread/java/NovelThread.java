package com.thread.java;

/**
 * @author SunHang
 * @className: NovelThread
 * @description:
 * @createTime 2021/4/8 21:11
 */
public class NovelThread extends Thread {
    private String novelPath;
    private String targetFilePath;

    // 重写父类的run方法
    @Override
    public void run() {
        DownloadNovelUtil.downloadNovel(novelPath, targetFilePath);
        System.out.println(getName()+"成功下载了："+targetFilePath);
    }

    public NovelThread(String name,String novelPath, String targetFilePath) {
        super(name);
        this.novelPath = novelPath;
        this.targetFilePath = targetFilePath;
    }
}
