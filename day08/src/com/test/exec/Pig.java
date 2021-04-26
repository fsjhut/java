package com.test.exec;
/**
 * @className: Pig
 * @description:   
 * @author SunHang
 * @createTime 2021/3/24 15:59
 */
public class Pig extends Pet{
    private String hairColor;

    public Pig(String name, char gender, int exp, int grade, String hairColor) {
        super(name, gender, exp, grade);
        this.hairColor = hairColor;
    }

    public Pig() {
    }

    public void roll(){
        System.out.println(this.getName()+"正在滚动--");
    }
}
