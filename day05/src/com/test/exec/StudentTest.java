package com.test.exec;
/** 不要对成员变量初始化操作
 *@className: Student
 *@description 
 *@author SunHang
 *@createTime 2021/3/18 21:47
 */
public class StudentTest{
    public static void main(String[] args) {
        Student student = new Student();
        System.out.println(student.gender);
        student.eat();
        student.study();
        // 使用的时候在定义
        // 不要在对象属性定义时初始化对象属性
        student.computer = new Computer();
        student.playComputer();
    }
}

class Student {
    int studentId = 0;
    String name = "Sun";
    char gender = '男';
    Computer computer ;


    public void eat(){
        System.out.println("正在吃饭---");
    }
    public void study(){
        System.out.println("正在学习---");
    }

    public void playComputer(){
        if(computer==null){
            System.out.println("请初始化电脑信息");
        }
        computer.surfInternet(this);
    }
}

class Computer{
    String cpu,networkCard,displayCard,soundCard;
    String memory;
    public void surfInternet(Student student){
        System.out.println(student.name + "正在上网");
    }
}
