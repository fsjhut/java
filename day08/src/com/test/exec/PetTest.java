package com.test.exec;
/**
 * @className: PetTest
 * @description:   
 * @author SunHang
 * @createTime 2021/3/24 16:09
 */
public class PetTest {
    public static void main(String[] args) {
        Pig pig = new Pig("pig",'公',100,3,"红");
        Cat cat = new Cat("cat",'公',100,23,"新版");
        Cattle cattle = new Cattle("cattle",'公',1000,20,"矩形");
        pig.follow();
        cat.fly();
        cattle.follow();
    }
}
