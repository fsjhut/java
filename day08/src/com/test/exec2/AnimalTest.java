package com.test.exec2;

/**
 * @author SunHang
 * @className: AnimalTest
 * @description:
 * @createTime 2021/3/24 16:17
 */
public class AnimalTest {
    public static void main(String[] args) {
        Bird bird = new Bird("bird", 5, "红");
        bird.fly();
        bird.move();
        System.out.println(bird.getColor());
    }
}
