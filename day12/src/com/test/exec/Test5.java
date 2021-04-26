package com.test.exec;
/**
 * @className: Test5
 * @description:   
 * @author SunHang
 * @createTime 2021/3/30 13:17
 */

import java.util.Arrays;

/**
 * 把数组中的数据按照指定格式拼接成一个字符串。
 * 举例：int[] arr = {1,2,3};
 * 输出结果：[1, 2, 3]
 */
public class Test5 {
    public static void main(String[] args) {
        test();
    }
    public static void test(){
        int[] arr = {1,2,3,0,23,12};
        String str = "";
        for(int i = 0;i<arr.length;i++){
            Integer a = arr[i];
            str = str + a.toString();
        }
        System.out.println(str);
        char[] ch = str.toCharArray();
        System.out.println(Arrays.toString(ch));
    }
}
