package com.test2.exec4;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

/**
 * @className: Monster
 * @description:   
 * @author SunHang
 * @createTime 2021/3/24 18:55
 */
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class Monster {
    private String name;
    private int blood;
    private int attack;
    private int defense;
    public void attack(){

    }
    public void move(){

    }

    @Override
    public String toString() {
        return "Monster{" +
                "name='" + name + '\'' +
                ", blood=" + blood +
                ", attack=" + attack +
                ", defense=" + defense +
                '}';
    }
}
