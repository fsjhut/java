package com.test.exec;
/**
 * @className: Cat
 * @description:   
 * @author SunHang
 * @createTime 2021/3/24 16:02
 */
public class Cat extends Pet {
    private String Coat;

    public Cat(String name, char gender, int exp, int grade, String coat) {
        super(name, gender, exp, grade);
        Coat = coat;
    }

    public Cat() {
    }

    public void fly(){
        System.out.println(this.getName()+"正在飞行---");
    }
}
