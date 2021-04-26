package com.test2;
/**
 * @className: Cariculate
 * @description:   
 * @author SunHang
 * @createTime 2021/3/25 17:03
 */
public class Circulate {
    public static void main(String[] args) {
        Graphy circle = new Circle("圆",2);
        Graphy rectangle = new Rectangle("矩形",2,5);
        Circulate.circulate(circle);
        Circulate.circulate(rectangle);
        Circulate.returnObj(circle);
        Circulate.returnObj(rectangle);

    }
    public static void circulate(Graphy graphy){
        graphy.area();
        graphy.perimeter();
    }
    public static void returnObj(Graphy graphy){
        System.out.println(graphy);
    }
}
