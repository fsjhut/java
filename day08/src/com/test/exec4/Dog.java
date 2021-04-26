package com.test.exec4;
/**
 * @className: Dog
 * @description:   
 * @author SunHang
 * @createTime 2021/3/24 16:30
 */
public class Dog extends Animal {

    public Dog(String name, String color) {
        super(name, color);
    }

    public Dog() {
    }

    public void eatPeople(){
        System.out.println(this.getName()+"正在咬人---");
    }
}
