package com.test.exec;

import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * @className: ParseIntTest
 * @description:   
 * @author SunHang
 * @createTime 2021/3/29 19:42
 */
public class ParseIntTest {
    final static char[] digits = {
            '0' , '1' , '2' , '3' , '4' , '5' ,
            '6' , '7' , '8' , '9' , '+' , '-' ,
    };
    public static void main(String[] args) {
        // 随机生成一个字符串
        // 第一数字为0-9 + -  其余数字为0-9
        Date data1 = new Date();
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("HH:mm:ss:SSS");
        String date1Str = simpleDateFormat.format(data1);
        System.out.println("计算前的时间是=" + date1Str);
        char[] chars = new char[6];
        for(int i = 0 ; i<100000000;i++){
            chars[0] =digits[(int)(Math.random()*12)];
            for(int j = 1; j<chars.length;j++){
                chars[j] = digits[(int)(Math.random()*10)];
            }
            String str = String.valueOf(chars);
            ParseInt.parseInt(str);
        }
        Date data2 = new Date();
        String date2Str = simpleDateFormat.format(data2);
        System.out.println("计算后的时间是=" + date2Str);
        System.out.println("------------------------------------");
        Date data3 = new Date();
        String date3Str = simpleDateFormat.format(data3);
        System.out.println("计算前的时间是=" + date3Str);
        for(int i = 0 ; i<100000000;i++){
            chars[0] =digits[(int)(Math.random()*12)];
            for(int j = 1; j<chars.length;j++){
                chars[j] = digits[(int)(Math.random()*10)];
            }
            String str = String.valueOf(chars);
            Integer.parseInt(str);
        }
        Date data4 = new Date();
        String date4Str = simpleDateFormat.format(data4);
        System.out.println("计算后的时间是=" + date4Str);
    }
}
