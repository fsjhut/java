package com.test2;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

/**
 * @className: Circle
 * @description:   
 * @author SunHang
 * @createTime 2021/3/25 16:41
 */
@Setter
@Getter
@ToString
public class Circle extends Graphy {
    private int radius;

    public Circle(String name, int radius) {
        super(name);
        this.radius = radius;
    }

    public Circle() {

    }

    @Override
    public void area() {
        double area = Math.PI*radius*radius;
        System.out.println(getName() + "的面积是: " + area);
    }

    @Override
    public void perimeter() {
        double perimeter = Math.PI*radius*2;
        System.out.println(getName() + "的周长是: " + perimeter);
    }
}
