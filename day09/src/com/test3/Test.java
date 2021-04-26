package com.test3;
/**
 * @className: Test
 * @description:   
 * @author SunHang
 * @createTime 2021/3/25 17:15
 */
public class Test {
    public static void main(String[] args) {
        StudentBase normalStu = new NormalStudent("普通学生",1001);
        StudentBase earnStu = new EarnMoneyStudent("赚钱学生",2001);
        ((NormalStudent) normalStu).study();
        ((EarnMoneyStudent) earnStu).study();
        ((EarnMoneyStudent) earnStu).earnMoney();
    }
}
