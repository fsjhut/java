package com.test.exec3;
/**
 *@className: Human
 *@description 
 *@author SunHang
 *@createTime 2021/3/20 9:48
 */

/**
 * 定义一个人类，id ，name , age 三个属性，
 * 还具备一个自己与他人比较年龄的方法。
 * 类中有属性，可以统计创建人类对象的次数
 */
public class Human {
    int id;
    public static int num;
    public String name;
    public int age;

    public Human(String name, int age) {
        this.id = num;
        this.name = name;
        this.age = age;
        num++;
    }

    public Human() {
        this.id = num;
        num++;
    }

    public void compareAge(Human another){
        if(this.age == another.age){
            System.out.println("你们的年龄相等!");
        }else if(this.age>another.age){
            System.out.println("你的年龄较大!");
        }else{
            System.out.println("你的年龄较小!");
        }
    }
}

class HumanTest{
    public static void main(String[] args) {
        Human human1 = new Human("sun",25);
        Human human2 = new Human("hang",25);
        Human human3 = new Human();
        System.out.println(human1.id);
        System.out.println(human2.id);
        System.out.println(human3.id);
        human1.compareAge(human2);
        System.out.println(Human.num);
    }
}
