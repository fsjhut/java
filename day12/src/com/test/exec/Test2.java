package com.test.exec;
/**
 * @className: Test2
 * @description:   
 * @author SunHang
 * @createTime 2021/3/30 13:15
 */

import java.util.Objects;

/**
 * 用户从控制台输入一个字符串，
 * 请判断是否为回文字符串。
 */
public class Test2 {
    public static void main(String[] args) {
        test();
    }
    public static void test(){
        System.out.print("请输入一个字符串:");
        String str = Test1.scanner.next();
        Objects.requireNonNull(str);
        char[] chs = str.toCharArray();
        if(str.equals("")){
            System.out.println("字符串为空!");
            return;
        }
        for(int i=0;i<chs.length/2;i++){
            if(chs[i]!=chs[chs.length-1-i]){
                System.out.println("不是回文字符串");
                return;
            }
        }
        System.out.println("是回文字符串");
    }
}
