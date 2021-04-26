package com.test.exec;

import lombok.Getter;
import lombok.Setter;

/**
 * @className: Pet
 * @description:   
 * @author SunHang
 * @createTime 2021/3/24 15:55
 */
@Getter
@Setter
public class Pet {
    private String name;
    private char gender;
    private int exp;
    private int grade;

    public void follow(){
        System.out.println(name + "跟随主人!");
    }
    public void eat(){
        System.out.println(name + "正在吃食物");
    }

    public Pet(String name, char gender, int exp, int grade) {
        this.name = name;
        this.gender = gender;
        this.exp = exp;
        this.grade = grade;
    }

    public Pet() {
    }
}
