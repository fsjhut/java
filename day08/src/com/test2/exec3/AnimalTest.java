package com.test2.exec3;

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
        AnimalTest.test(dog);
        AnimalTest.test(fish);
    }

    public static void test(Animal animal){
        animal.move();
        if(animal instanceof Dog){
            Dog dog = (Dog)animal;
            dog.eatPeople();
        }
        if(animal instanceof Fish){
            Fish fish = (Fish)animal;
            fish.bubble();
        }
    }
}
