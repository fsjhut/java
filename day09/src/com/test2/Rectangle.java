package com.test2;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

/**
 * @author SunHang
 * @className: Rectangle
 * @description:
 * @createTime 2021/3/25 16:41
 */
@Getter
@Setter
@ToString
public class Rectangle extends Graphy {
    private int length;
    private int width;

    public Rectangle(String name, int length, int width) {
        super(name);
        this.length = length;
        this.width = width;
    }

    public Rectangle() {

    }

    @Override
    public void area() {
        int area = length * width;
        System.out.println(getName() + "的面积是: " + area);
    }

    @Override
    public void perimeter() {
        int perimeter = 2 * (length + width);
        System.out.println(getName() + "的周长是: " + perimeter);

    }
}
