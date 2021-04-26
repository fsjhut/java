package com.test.exec4;
/**
 * @className: Fish
 * @description:   
 * @author SunHang
 * @createTime 2021/3/24 16:32
 */
public class Fish extends Animal {
    public Fish(String name, String color) {
        super(name, color);
    }

    public Fish() {
    }
    public void bubble(){
        System.out.println(getName()+"正在吹泡泡------");
    }
}
