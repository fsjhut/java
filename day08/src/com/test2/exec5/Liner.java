package com.test2.exec5;

import lombok.Getter;
import lombok.Setter;

/**
 * @author SunHang
 * @className: Liner
 * @description:
 * @createTime 2021/3/24 19:17
 */
@Getter
@Setter
public class Liner extends Pattern {
    private int xCoordinates;
    private int yCoordinates;

    public Liner(int xAxis, int yAxis, String color, int xCoordinates, int yCoordinates) {
        super(xAxis, yAxis, color);
        this.xCoordinates = xCoordinates;
        this.yCoordinates = yCoordinates;
    }

    public Liner(int xCoordinates, int yCoordinates) {
        this.xCoordinates = xCoordinates;
        this.yCoordinates = yCoordinates;
    }

    @Override
    public void draw() {
        System.out.println("这是一个线条!");
        for (int i = 0; i < Math.abs(yCoordinates - xCoordinates); i++) {
            System.out.print("*");
        }
        System.out.println();
    }
}
