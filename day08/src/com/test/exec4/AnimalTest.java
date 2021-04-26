package com.test.exec4;
/**
 * @className: AnimalTest
 * @description:   
 * @author SunHang
 * @createTime 2021/3/24 16:33
 */
public class AnimalTest {
    public static void main(String[] args) {
        Dog dog = new Dog("dog","红色");
        Fish fish = new Fish("fish","黑色");
        dog.eatPeople();
        fish.bubble();
        System.out.println(dog);
        System.out.println(fish);
    }
}
