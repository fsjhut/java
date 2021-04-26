package com.test.exec;
/**
 * @className: Test7
 * @description:   
 * @author SunHang
 * @createTime 2021/3/30 13:18
 */

/**
 * 去掉一个字符串中的所有空格(包括中间的空格)
 * 使用正则
 */
public class Test7 {
    public static void main(String[] args) {
        test();
    }
    public static void test(){
        System.out.print("请输入一个字符串:");
//        String str = Test1.scanner.next();
        String str = "wqer  qwerwwq q ";
        str.replaceAll(" ", "");
        System.out.println(str.replaceAll("\\s+", ""));
//        System.out.println(str);
    }
}
