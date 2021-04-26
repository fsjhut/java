package com.test.java1;

import java.io.Serializable;

/**
 * @className: Creature
 * @description:   
 * @author SunHang
 * @createTime 2021/4/4 15:22
 */
public class Creature<T> implements Serializable {
    private char gender;
    public double weight;

    private void breath(){
        System.out.println("生物呼吸");
    }
    public void eat(){
        System.out.println("生物吃东西");
    }
}
