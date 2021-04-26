package com.thread.java;

/**
 * @author SunHang
 * @className: NovelThread
 * @description:
 * @createTime 2021/4/8 21:11
 */
public class NovelThread3 {
    private static String novelPath;
    private static String targetFilePath;

    // 重写父类的run方法
    public  void downLoad() {
        DownloadNovelUtil.downloadNovel(novelPath, targetFilePath);
        System.out.println("成功下载了："+targetFilePath);
    }

    public NovelThread3(String novelPath, String targetFilePath) {
        this.novelPath = novelPath;
        this.targetFilePath = targetFilePath;
    }
}
