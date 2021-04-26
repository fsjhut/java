package com.test;

import lombok.Getter;
import lombok.Setter;

/**
 * @className: MyTest
 * @description:   
 * @author SunHang
 * @createTime 2021/4/13 18:41
 */
@Getter
@Setter
public class MyClass <S,T>{
    private S s;  // 成员变量，全局变量 实例变量
    private T t;
}
