package com.test.exec2;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

/**
 * @className: Animal
 * @description:   
 * @author SunHang
 * @createTime 2021/3/24 16:13
 */
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class Animal {
    private String name;
    private int age;


    public void move(){
        System.out.println(name + "正在移动-----");
    }
}
