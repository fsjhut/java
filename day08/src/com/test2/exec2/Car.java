package com.test2.exec2;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

/**
 * @className: Car
 * @description:   
 * @author SunHang
 * @createTime 2021/3/24 16:19
 */
@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor

public class Car {
    private String color;
    private String name;
    private int wheelNum;

    public  void run(Car car){
        if(wheelNum<4){
            System.out.println(name + "车辆故障,无法驾驶!");
        }
        System.out.println(car + "正在跑------");
    }

    @Override
    public String toString() {
        return name+"{" +
                "color='" + color + '\'' +
                ", name='" + name + '\'' +
                ", wheelNum=" + wheelNum +
                '}';
    }
}
