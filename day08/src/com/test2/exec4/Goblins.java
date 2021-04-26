package com.test2.exec4;

import lombok.Getter;
import lombok.Setter;

/**
 * @className: Goblins
 * @description:   
 * @author SunHang
 * @createTime 2021/3/24 18:59
 */
@Getter
@Setter
public class Goblins extends Monster {
    private int rage;

    public Goblins(String name, int blood, int attack, int defense, int rage) {
        super(name, blood, attack, defense);
        this.rage = rage;
    }

    public Goblins() {
    }

    @Override
    public void attack(){
        System.out.println(getName()+"用石头进行了攻击----");
    }
    @Override
    public void move(){
        System.out.println(getName()+"缓慢移动-------");
    }
}
