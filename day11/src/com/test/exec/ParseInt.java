package com.test.exec;

import java.util.Objects;

/**
 * @className: ParseInt
 * @description:   
 * @author SunHang
 * @createTime 2021/3/29 19:27
 */
public class ParseInt {
    public static int parseInt(String str) {
        Objects.requireNonNull(str);
        int len = str.length();
//        Integer.valueOf();
        // 判断第一个字符是数字?+/-?
        char ch = str.charAt(0);
        boolean isFlag = false;
        int index = 0;
        int result = 0;
        int radix = 10;
        if (ch == '-') {
            isFlag = true;
            index++;
        } else if (ch == '+') {
            index++;
        } else if (ch > 57 || ch < 48) {
            throw new NumberFormatException("输入含有非法字符!");
        }
        for (; index < len; index++) {
            int num = str.charAt(index);
            if (num > 57 | num < 48) {
                throw new NumberFormatException("输入含有非法字符!");
            }
            int digit = num - 48;
            result = radix * result + digit;
        }
        result = isFlag ? -result : result;
        return result;
    }
}
