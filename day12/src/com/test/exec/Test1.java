package com.test.exec;
/**
 * @className: Test1
 * @description:   
 * @author SunHang
 * @createTime 2021/3/30 13:15
 */

import java.util.Arrays;
import java.util.Scanner;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * 用户从控制台输入一个字符串，要求判断是否为合法的email地址，如果合法，请判断是否为sina的邮箱地址。
 * (PS:记住异常处理) 合法邮箱规则:
 * 有且仅有一个@和.
 * @在.之前 且@不能是第一位  .不能是最后一位
 * @与.不能相邻
 * 新浪邮箱应当以@sina.com结尾
 */
public class Test1 {
    public static Scanner scanner = new Scanner(System.in);

    public static void main(String[] args) {
        System.out.print("请输入你的email:");
        // 例如: weriw@qerqwrqf.qwer
        String str = scanner.next();
//        System.out.println(test(str));
        System.out.println(test3(str));
    }

    public static boolean test(String str){

        int len = str.length();
        if(str.charAt(0)=='@'||str.charAt(len-1)=='.'){
            return false;
        }
        // 判断@是否在.之前
        if((str.indexOf("@")>str.indexOf("."))||
                ((str.indexOf("@"))==-1)||((str.indexOf("."))==-1)) {
            return false;
        }
        // 判断@与. 是否相邻
        String regex = ".*?(@\\.)+";
        Pattern pattern = Pattern.compile(regex);
        Matcher matcher = pattern.matcher(str);
        boolean matchers = matcher.lookingAt();
        if(matchers){
            return false;
        }
        // 先排序,判断是否出现0次或者多次
        char[] chars = str.toCharArray();
        Arrays.sort(chars);
        String newStr = new String(chars);
        System.out.println(newStr);
        String regex2 = ".*?\\.[^.]+?@[^@]*?";
//        String regex2 = ".*?@[^@]*?"; //可以保证只有一个@吗?
//        String regex3 = ".*?@{1}";
        Pattern pattern2 = Pattern.compile(regex2);
        Matcher matcher2 = pattern2.matcher(newStr);
        boolean matchers2 = matcher2.matches();
        if(matchers2){
            return true;
        }
        return false;
    }

    public static boolean test2(String str){
        String regex = ".*?@sina\\.com$";
        Pattern pattern = Pattern.compile(regex);
        Matcher matcher = pattern.matcher(str);
        return matcher.matches();
    }
    public static boolean test3(String str){
//        String regex1 = "[^@]+?@[^@]+?\\.[^\\.]+?$";
        String regex = "^[^.@]+?@[^.@]+?\\.[^.@]+$";
//        String regex = "^\\w{5,}@\\w{2,}\\.[a-zA-Z]{2,3}$";
//        String regex2 = "^(([^<>()\\[\\]\\\\.,;:\\s@\"]+" +
//                "(\\.[^<>()\\[\\]\\\\.,;:\\s@\"]+)*)" +
//                "|(\".+\"))@((\\[[0-9]{1,3}\\.[0-9]{1,3}" +
//                "\\.[0-9]{1,3}\\.[0-9]{1,3}])|" +
//                "(([a-zA-Z\\-0-9]+\\.)+[a-zA-Z]{2,}))$";
//        String regex2 = ".*?@[^@]*?"; //可以保证只有一个@吗?
//        String regex3 = ".*?@{1}";
        Pattern pattern = Pattern.compile(regex);
        Matcher matcher = pattern.matcher(str);
        return matcher.matches();
    }
}
