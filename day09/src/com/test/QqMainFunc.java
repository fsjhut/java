package com.test;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

/**
 * @className: QqMainFunc
 * @description:   
 * @author SunHang
 * @createTime 2021/3/25 16:36
 */
@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
public class QqMainFunc implements QqGame,QqMusic,QqZone{
   private String name;
   private int Id;

    @Override
    public void playGame() {
        System.out.println(name + "的qq正在加载游戏模块-----");
    }

    @Override
    public void playerMusic() {
        System.out.println(name + "的qq正在加载音乐模块-----");
    }

    @Override
    public void playZone() {
        System.out.println(name + "的qq正在加载空间模块-----");
    }
}
