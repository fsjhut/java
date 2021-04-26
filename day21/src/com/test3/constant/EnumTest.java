package com.test3.constant;
/**
 * @className: EnumTest
 * @description:   
 * @author SunHang
 * @createTime 2021/4/13 21:35
 */
public class EnumTest {
    public static void main(String[] args) {
        // 对象的方法
        UserEnum user1 = UserEnum.USER1;
        System.out.println(user1);
        System.out.println(user1.getId());
        System.out.println(user1.getName());
        user1.demo();
//        user1.num 拿不到的
    }
}
