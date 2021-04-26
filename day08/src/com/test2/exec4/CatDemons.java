package com.test2.exec4;

import lombok.Getter;
import lombok.Setter;

/**
 * @author SunHang
 * @className: CatDemons
 * @description:
 * @createTime 2021/3/24 19:00
 */
public class CatDemons extends Monster {
    public CatDemons(String name, int blood, int attack, int defense) {
        super(name, blood, attack, defense);
    }


    public CatDemons() {
    }
    @Override
    public void attack() {
        System.out.println(getName() + "用爪子进行攻击---");
    }
    @Override
    public void move() {
        System.out.println(getName() + "跳着移动-------");
    }
}
