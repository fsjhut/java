package com.test2.exec5;

import lombok.Getter;
import lombok.Setter;

/**
 * @author SunHang
 * @className: Rectangle
 * @description:
 * @createTime 2021/3/24 19:17
 */
@Getter
@Setter
public class Rectangle extends Pattern {
    private int length;
    private int width;

    public Rectangle(int xAxis, int yAxis, String color, int length, int width) {
        super(xAxis, yAxis, color);
        this.length = length;
        this.width = width;
    }

    public Rectangle(int length, int width) {
        this.length = length;
        this.width = width;
    }

    @Override
    public void draw() {
        System.out.println("这是一个矩形!");
        for (int i = 0; i < width; i++) {
            for (int j = 0; j < length; j++) {
                System.out.print("*");
            }
            System.out.println();
        }
    }
}
