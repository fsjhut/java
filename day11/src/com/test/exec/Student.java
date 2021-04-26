package com.test.exec;
/**
 * @className: Student
 * @description:   
 * @author SunHang
 * @createTime 2021/3/29 19:22
 */


import lombok.Getter;
import lombok.Setter;

/**
 * 编写一个Student类，包含name、age等属性，
 * 要求使用System.out.println()打印Student类的对象引用时，
 * 输出的为name的值！
 */
@Getter
@Setter
public class Student {
    private String name;
    private int age;
    private int id;
    public Student(){

    }

    public Student(String name, int age, int id) {
        this.name = name;
        this.age = age;
        this.id = id;
    }
    @Override
    public String toString(){
        return name;
    }
}
