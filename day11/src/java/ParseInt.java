package java;

import java.util.Arrays;
import java.util.Objects;

/**
 * 0-9的Ascii码是48-57
 * - 的Ascii码为:45, + 的ascii为43
 *
 * @author SunHang
 * @className: ParseInt
 * @description: 将字符串转化为整形
 * @createTime 2021/3/29 12:28
 */
public class ParseInt {

    public static void main(String[] args) {
        String str = "555";
        String str1 = "-555";
        String str2 = "+515";
//        String str3 = "+5a5";
        System.out.println(parseInt(str));
        System.out.println(parseInt(str1));
        System.out.println(parseInt(str2));
//        System.out.println(parseInt(str3));
        int test = 551332;
//        System.out.println(changeRadix(test).toString());
        int[] arr = changeRadix(test);
        int[] arr2 = eightRadix(arr);
        char[] arr3 = sixteenRadix(arr);
        System.out.println(Arrays.toString(arr));
        System.out.println(Arrays.toString(arr2));
        System.out.println(Arrays.toString(arr3));
        System.out.println(Integer.toHexString(551332));
        System.out.println(Integer.toOctalString(551332));
        System.out.println(Integer.toBinaryString(551332));

    }


    public static int parseInt(String str) {
        Objects.requireNonNull(str);
        int len = str.length();
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

    // 十进制转换为二进制
    // 如何实现二进制,八进制和16进制的互转?
    public static int[] changeRadix(int num) {
        //
        if (num == 0) {
            return new int[]{0};
        }
        String str = "" + num;
        // 数组的长度最大为整数的4倍
        int[] arr = new int[str.length() * 4];
        int result = num;
        int temp;
        int i = 0;
        for (; result > 0; i++) {
            temp = result % 2;
            arr[i] = temp;
            result = result / 2;
        }
        int[] newArr = new int[i];
        int k = 0;
        for (int j = i - 1; j >= 0; j--) {
            newArr[k] = arr[j];
            k++;
        }
        return newArr;
    }

    // 二进制转为8进制
    public static int[] eightRadix(int[] arr) {
        int len = arr.length;
        int[] newArr;
        if ((len % 3) == 0) {
            newArr = new int[arr.length / 3];
        } else {
            newArr = new int[arr.length / 3 + 1];
        }
        int i = len - 1;
        int temp;
        for (int k = 0; k < len / 3; k++, i = i - 3) {
            temp = arr[i] + arr[i - 1] * 2 + arr[i - 2] * 4;
            newArr[newArr.length - 1 - k] = temp;
        }
        if (len % 3 == 1) {
            newArr[0] = 1;
        } else if (len % 3 == 2) {
            newArr[0] = arr[1] + arr[0] * 2;
        }

        return newArr;
    }

    // 二进制转换为16进制
    public static char[] sixteenRadix(int[] arr) {
        int len = arr.length;
        int[] newArr;
        if ((len % 4) == 0) {
            newArr = new int[arr.length>>2];
        } else {
            newArr = new int[arr.length>>2 + 1];
        }
        int i = len - 1;
        int temp;
        for (int k = 0; k < len>>2; k++, i = i - 4) {
            temp = arr[i] + (arr[i - 1]<<1) + (arr[i - 2]<<2) + (arr[i - 3]<<3);
            newArr[newArr.length - 1 - k] = temp;
        }
        if (len % 4 == 1) {
            newArr[0] = 1;
        } else if (len % 4 == 2) {
            newArr[0] = arr[1] + arr[0]<<1;
        } else if (len % 4 == 3) {
            newArr[0] = arr[2] + (arr[1]<<1) + (arr[0]<<2);
        }
//        System.out.println(Arrays.toString(newArr));
        char[] chArr = new char[newArr.length];
        for (int j = 0; j < chArr.length; j++) {
            if (newArr[j]>=10){
                chArr[j] = (char)(newArr[j] + 87);
            }else{
                chArr[j] = (char)(newArr[j] + 48);
            }
        }
        return chArr;
    }

    // 利用位运算符
    public static char[] sixteenRadix2(int[] arr) {
        int len = arr.length;
        int radix = 15;
        int[] newArr;
        if ((len % 4) == 0) {
            newArr = new int[arr.length / 4];
        } else {
            newArr = new int[arr.length / 4 + 1];
        }
        int i = len - 1;
        int temp;
        for (int k = 0; k < len / 4; k++, i = i - 4) {
            temp = arr[i] + arr[i - 1] >> 1 + arr[i - 2] * 4 + arr[i - 3] * 8;
            newArr[newArr.length - 1 - k] = temp;
        }
        if (len % 4 == 1) {
            newArr[0] = 1;
        } else if (len % 4 == 2) {
            newArr[0] = arr[1] + arr[0] * 2;
        } else if (len % 4 == 3) {
            newArr[0] = arr[2] + arr[1] * 2 + arr[0] * 4;
        }
//        System.out.println(Arrays.toString(newArr));
        char[] chArr = new char[newArr.length];
        for (int j = 0; j < chArr.length; j++) {
            if (newArr[j]>=10){
                chArr[j] = (char)(newArr[j] + 55);
            }else{
                chArr[j] = (char)(newArr[j] + 48);
            }
        }
        return chArr;
    }
}
