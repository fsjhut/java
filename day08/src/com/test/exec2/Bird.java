package com.test.exec2;

import lombok.Getter;
import lombok.Setter;

/**
 * @className: Bird
 * @description:   
 * @author SunHang
 * @createTime 2021/3/24 16:14
 */
@Getter
@Setter
public class Bird extends Animal {
    private String color;

    public Bird(String name, int age, String color) {
        super(name, age);
        this.color = color;
    }

    public Bird() {

    }

    public void fly(){
        System.out.println(this.getName() + "正在飞----");
    }
}
