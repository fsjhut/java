package com.test.exec3;

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

    public void run(){
        System.out.println(name + "正在跑------");
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
