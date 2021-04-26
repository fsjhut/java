package com.test.exec7;/**
 *@className: Music
 *@description 具有属性：音乐名、播放时长、歌手
 *@author SunHang
 *@createTime 2021/3/20 9:52
 */
public class Music {
    String musicName;
    int time;
    String singerName;

    public Music(String musicName, int time, String singerName) {
        this.musicName = musicName;
        this.time = time;
        this.singerName = singerName;
    }
}
