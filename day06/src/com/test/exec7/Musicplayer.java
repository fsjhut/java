package com.test.exec7;
/**
 *@className: Musicplayer
 *@description
 *@author SunHang
 *@createTime 2021/3/20 9:53
 */

import java.util.Scanner;

/**
 * 描述音乐播放器类，具有属性：播放器名字、播放音乐方法。
 * 控制台录入音乐名、播放时长、歌手名
 * 根据信息创建音乐对象
 * 音乐对象传入播放器的播放音乐方法
 * 在播放音乐方法中输出音乐信息
 * 正在播放xx歌唱的音乐【xxx】剩余时间xxx
 */
public class Musicplayer {
    static String videoName = "默认播放器";
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        System.out.print("请输入音乐名: ");
        String musicName = scanner.next();
        System.out.print("请输入播放时长: ");
        int time = scanner.nextInt();
        System.out.print("请输入歌手名:");
        String singerName = scanner.next();
        Music music = new Music(musicName, time, singerName);
        airplay(music);
    }

    public static void airplay(Music music){
        System.out.println("正在播放["+music.singerName+"]歌唱的音乐["+music.musicName+"]"
                            + "\t剩余时间是: " + music.time + " 分钟!");
    }
}
