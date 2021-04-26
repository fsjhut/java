package com.test.exec;

import lombok.Getter;
import lombok.Setter;

/**
 * @className: Cattle
 * @description:   
 * @author SunHang
 * @createTime 2021/3/24 16:04
 */
@Setter
@Getter
public class Cattle extends Pet{
    private String shape;

    public Cattle(String name, char gender, int exp, int grade, String shape) {
        super(name, gender, exp, grade);
        this.shape = shape;
    }

    public Cattle() {

    }

    public void pickUp(){
        System.out.println(getName()+"正在捡道具----");
    }
}
