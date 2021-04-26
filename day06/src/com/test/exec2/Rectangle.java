package com.test.exec2;
/**
 *@className: Rectangle
 *@description 
 *@author SunHang
 *@createTime 2021/3/20 9:48
 */

/**
 * 矩形具备高、宽两个属性，
 * 还具备计算面积与周长的方法。
 * 一旦创建矩形对象时，那么该矩形对象
 * 就具备了高、宽两个属性值（不能使默认的初始值）
 */
public class Rectangle {
    private double height;
    private double width;

    public Rectangle(double height, double width) {
        this.height = height;
        this.width = width;
    }

    public void setHeight(double height) {
        this.height = height;
    }

    public void setWidth(double width) {
        this.width = width;
    }

    public double getHeight() {
        return height;
    }

    public double getWidth() {
        return width;
    }

    public void rectangleArea(){
        double area = width * height;
        System.out.println("该矩形的面积是: " + area);
    }

    public void perimeter() {
        double per = 2*(width+height);
        System.out.println("该矩形的周长是: " + per);
    }
}

class RectangleTest{
    public static void main(String[] args) {
        Rectangle rectangle = new Rectangle(3,5);
        rectangle.rectangleArea();
        rectangle.perimeter();
    }
}