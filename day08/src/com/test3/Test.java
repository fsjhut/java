package com.test3;

/**
 * @author SunHang
 * @className: Test
 * @description:
 * @createTime 2021/3/24 22:38
 */
// 在局部位置，可以创建内部类对象，通过对象调用和内部类方法
class Outer {
    private int age = 20;

    public void method() {
        int age2 = 30;
        class Inner {
            public void show() {
                System.out.println(age);
                // 从内部类中访问方法内变量age2，需要将变量声明为最终类型。
                System.out.println(age2);
            }
        }
        Inner i = new Inner();
        i.show();
    }
}

public class Test {
    public static void main(String[] args) {
        Outer outer = new Outer();
//        outer.method().age2;


    }
}