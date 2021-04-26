package com.test2.exec3;

import lombok.*;

/**
 * @className: Animal
 * @description:   
 * @author SunHang
 * @createTime 2021/3/24 16:29
 */
@Getter
@NoArgsConstructor
@AllArgsConstructor
@Setter
@ToString
public class Animal {
    private String name;
    private String color;

    public void move(){
        System.out.println(name +"正在移动-----");
    }
}
