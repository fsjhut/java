package com.test.exec;
/**
 * @className: Test12
 * @description:   
 * @author SunHang
 * @createTime 2021/3/30 13:24
 */

/**
 * 现有一个字符串“我我....我...我.要...要要...要学....学学..学.编..编编.编.程.程.程..程”。
 * 将该字符串变成:“我要学编程”。使用正则
 */
public class Test12 {
    public static void main(String[] args) {
        test();
    }
    public static void test(){
        String str = "我我....我...我.要...要要...要学....学学..学.编..编编.编.程.程.程..程";
        str = str.replaceAll("\\.*","");
        str = str.replaceAll("(.)\\1+","$1");
        System.out.println(str);
    }
}
