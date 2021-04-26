package com.test.exec;
/**
 * @className: Test8
 * @description:
 * @author SunHang
 * @createTime 2021/3/30 13:18
 */

/**
 * 获取一个字符串中指定子串出现的次数。
 * 比如说“hanbasdnbafllgnbahjnbakqqqqlnbaxi”
 * 在这个字符串中，有几个nba？
 */
public class Test8 {
    public static void main(String[] args) {
        test();
        test2();
    }

    public static void test() {
        String str = "hanbasdnbafllgnbahjnbakqqqqlnbaxi";
        String str2 = "nba";
        int len = str2.length();
        int index = 0;
        int num = 0;
        while (index != (len - 1)) {
            index = str.indexOf(str2, index);
//            System.out.println(index);
            index = index + len;
            num++;
        }
        System.out.println("一共出现了:" + (num - 1) + "次");
    }

    public static void test2() {
        String str = "hanbasdnbafllgnbahjnbakqqqqlnbaxi";
        String str2 = "nba";
        int len1 = str.length();
        int len2 = str2.length();
        int num = 0;
        for (int i = 0; i < len1; ) {
            String str3;
            if ((i + len2) >= len1) {
                break;
            }
            str3 = str.substring(i, i + len2);
            if (str2.equals(str3)) {
                num++;
                i += len2;
            } else {
                i++;
            }
        }
        System.out.println("一共出现了:" + num + "次");
    }
}
