package com.test.exec;
/**
 * @className: Test6
 * @description:   
 * @author SunHang
 * @createTime 2021/3/30 13:18
 */

import org.junit.Test;

/**
 * 字符串反转。 举例：键盘录入”abc”
 * 输出结果：”cba”
 */

public class Test6 {
    public static void main(String[] args) {
        test();
    }
    public static void test(){
        System.out.println("请输入一个字符串:");
        String str = Test1.scanner.next();
        char[] ch = str.toCharArray();
        char[] newCh = new char[ch.length];
        int k = 0;
        for(int i = ch.length-1;i>=0;i--){
            newCh[k++] = ch[i];
        }
        String newStr = new String(newCh);
        System.out.println("原字符串为:      "+str);
        System.out.println("反转后新的字符串: "+newStr);
    }
}
