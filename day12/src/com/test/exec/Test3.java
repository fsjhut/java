package com.test.exec;
/**
 * @className: Test3
 * @description:   
 * @author SunHang
 * @createTime 2021/3/30 13:16
 */

import java.util.Arrays;

/**
 * 编写自定义的字符串一致性匹配方法，
 * 只要两个字符串包含同样的字符，
 * 不管字符的顺序如何，都认为两个字符串一致，
 * 如：”aabbcc”和”abcabc”被认为是一致的
 */
public class Test3 {
    public static void main(String[] args) {
        String str1 = "aabbcc";
        String str2 = "acbbac";
        System.out.println(equals(str1, str2));
    }

    public static boolean equals(String str1, String str2) {
        if(str1.equals("")|| str1.equals("")||(str1.length()!=str2.length())){
            return false;
        }
        char[] ch1 = str1.toCharArray();
        char[] ch2 = str2.toCharArray();
        Arrays.sort(ch1);
        Arrays.sort(ch2);
//        Arrays.equals(ch1,ch2);
        int n = ch1.length;
        int i = 0;
        while (n-- != 0){
            if(ch1[i]!=ch2[i])
                return false;
            i++;
        }
        return true;
    }
}
