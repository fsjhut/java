package com.test2.exec5;

import lombok.Getter;
import lombok.Setter;

/**
 * @className: Circle
 * @description:   
 * @author SunHang
 * @createTime 2021/3/24 19:17
 */
@Getter
@Setter
public class Circle extends Pattern{
    private int radius;

    public Circle(int xAxis, int yAxis, String color, int radius) {
        super(xAxis, yAxis, color);
        this.radius = radius;
    }

    public Circle(int radius) {
        this.radius = radius;
    }

    public void draw(){
        System.out.println("这是一个圆!!!!");
        for(int i = 0; i<3;i++){
            for(int j = 5; j-i>0;j--){
                System.out.print(" ");
            }
            for(int k = 0; k<10+2*i; k++){
                System.out.print("*");
            }
            System.out.println();
        }
        for(int i = 0; i<4;i++){
            for(int j = 0; j<i+1;j++){
                System.out.print(" ");
            }
            for(int k = 0; k<18-2*i; k++){
                System.out.print("*");
            }
            System.out.println();
        }
    }
}
