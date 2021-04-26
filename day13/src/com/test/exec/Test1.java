package com.test.exec;

import org.junit.Test;
import sun.security.util.Length;

import javax.xml.stream.events.StartDocument;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.*;
import java.util.concurrent.ThreadLocalRandom;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * @author SunHang
 * @className: Test
 * @description:
 * @createTime 2021/4/1 16:55
 */
public class Test1 {
    public static Scanner scanner = new Scanner(System.in);
    private static final String PATTERN = "yyyy-MM-dd HH:mm:ss";
    private static final DateTimeFormatter FORMATTER = DateTimeFormatter.ofPattern(PATTERN);
    private static final DateFormat DATE_FORMAT = new SimpleDateFormat(PATTERN);

    public static void main(String[] args) {
        test1();
//        test2();
//        test3();
//        test4();
//        test5();
//        test6();
        test7();
    }

    private static void test7() {
        // 0-9      a-z      A-Z 的Ascii
        // 48-57    97-122   65-90
        System.out.print("请输入随机数字的长度：");
        int length = scanner.nextInt();
        ThreadLocalRandom random = ThreadLocalRandom.current();
        StringBuilder str = new StringBuilder();
        int widthIndex;
        char ch = '\u0000';
        int num = 0;
        while (num<length){
            widthIndex = random.nextInt(3);
            if(widthIndex == 0){
                ch = (char) random.nextInt(48,58);
            }
            if(widthIndex == 1){
                ch = (char) random.nextInt(97,123);
            }
            if(widthIndex == 2){
                ch = (char) random.nextInt(65,91);
            }
            str.append(ch);
            num++;

        }
        System.out.println("该随机数是： " + str.toString());
    }

    private static void test6() {
        Calendar calendar = Calendar.getInstance();
        String pattern = "yyyy-MM-dd";
        System.out.println("请输入年月日(yyyy-MM-dd):");
        String inputTime = scanner.next();
        System.out.print("请输入间隔天数：");
        int dayDiff = scanner.nextInt();
        String dateRgex = "^\\d{4}-\\d{1,2}-\\d{1,2}$";
        Pattern patt = Pattern.compile(dateRgex);
        Matcher matcher = patt.matcher(inputTime);
        if (!matcher.matches()) {
            System.out.println("你输入日期格式不正确！");
            return;
        }
        DateFormat dateFormat = new SimpleDateFormat(pattern);
        Date date = null;
        try {
            date = dateFormat.parse(inputTime);
        } catch (ParseException e) {
            e.printStackTrace();
        }
        Objects.requireNonNull(date);
        calendar.setTime(date);
        calendar.add(Calendar.DATE, -dayDiff);
        Date time = calendar.getTime();
        System.out.println("新的日期为："+dateFormat.format(time));
    }

    private static void test5() {
        System.out.println("请输入第一个时间(yyyy-MM-dd HH:mm:ss)");
        String time1 = scanner.nextLine();
//        scanner.next();
        System.out.println("请输入第二个时间(yyyy-MM-dd HH:mm:ss)");
        String time2 = scanner.nextLine();
        Date start = null;
        Date end = null;
        try {
            start = DATE_FORMAT.parse(time1);
            end = DATE_FORMAT.parse(time2);
        } catch (ParseException e) {
            e.printStackTrace();
        }
        Objects.requireNonNull(start);
        Objects.requireNonNull(end);
        long diff = Math.abs(start.getTime()-end.getTime());
        double hour = (diff / (60 * 60 * 1000));
        System.out.println("两个时间相差： " + hour + "小时！");
    }

    private static void test4() {
        Calendar calendar = Calendar.getInstance();
        String pattern = "yyyy-MM-dd";
        System.out.println("请输入年月日(yyyy-MM-dd):");
        String inputTime = scanner.next();
        String dateRgex = "^\\d{4}-\\d{1,2}-\\d{1,2}$";
        Pattern patt = Pattern.compile(dateRgex);
        Matcher matcher = patt.matcher(inputTime);
        if (!matcher.matches()) {
            System.out.println("你输入日期格式不正确！");
            return;
        }
        DateFormat dateFormat = new SimpleDateFormat(pattern);
        Date date = null;
        try {
            date = dateFormat.parse(inputTime);
        } catch (ParseException e) {
            e.printStackTrace();
        }
        calendar.setTime(date);
        int monthMax = calendar.getActualMaximum(Calendar.DAY_OF_MONTH);
        int addDay = monthMax - calendar.get(Calendar.DATE);
        calendar.add(Calendar.DATE, addDay);
        Date time = calendar.getTime();
        System.out.println(dateFormat.format(time));
    }

    /**
     * @Title: test1
     * @Description: 将长时间格式时间转换为字符串
     * yyyy-MM-dd HH:mm:ss
     * @param: []
     * @return: void
     * @date: 2021/4/1 16:57
     * @version: 1.0
     */
    public static void test1() {
        Date date = new Date();
        String pattern = "yyyy-MM-dd HH:mm:ss";
        DateFormat dateFormat = new SimpleDateFormat(pattern);
        String nowTime = date.toString();
        String format = dateFormat.format(date);
        System.out.println(nowTime);
        System.out.println(format);
    }

    // 得到月份
    public static void test2() {
        Calendar calendar = Calendar.getInstance();
        String pattern = "yyyy-MM-dd";
        System.out.println("请输入年月日(yyyy-MM-dd):");
        String inputTime = scanner.next();
        // 格式验证\\"(([0-9]{3}[1-9]|[0-9]{2}[1-9][0-9]{1}|[0-9]{1}[1-9][0-9]{2}|[1-9][0-9]{3})-
        // (((0[13578]|1[02])-(0[1-9]|[12][0-9]|3[01]))|((0[469]|11)-(0[1-9]|[12][0-9]|30))|(02-(0[1-9]|[1][0-9]|2[0-8]))))|
        // ((([0-9]{2})(0[48]|[2468][048]|[13579][26])|((0[48]|[2468][048]|[3579][26])00))-02-29)
        // (([0-9]{3}[1-9]|[0-9]{2}[1-9][0-9]{1}|[0-9]{1}[1-9][0-9]{2}|[1-9][0-9]{3})-
        // (((0[13578]|1[02])-(0[1-9]|[12][0-9]|3[01]))|((0[469]|11)-(0[1-9]|[12][0-9]|30))|(02-(0[1-9]|[1][0-9]|2[0-8]))))|
        // ((([0-9]{2})(0[48]|[2468][048]|[13579][26])|((0[48]|[2468][048]|[3579][26])00))-02-29) ";
        String dateRgex = "^\\d{4}-\\d{1,2}-\\d{1,2}$";
        Pattern patt = Pattern.compile(dateRgex);
        Matcher matcher = patt.matcher(inputTime);
        if (!matcher.matches()) {
            System.out.println("你输入日期格式不正确！");
            return;
        }
        DateFormat dateFormat = new SimpleDateFormat(pattern);
        Date date = null;
        try {
            date = dateFormat.parse(inputTime);
        } catch (ParseException e) {
            e.printStackTrace();
        }
        calendar.setTime(date);
        System.out.println(date.toString());
//        calendar.set();
        calendar.add(Calendar.DATE, (-calendar.get(Calendar.DAY_OF_MONTH)) + 1);
        int startIndex = calendar.get(Calendar.DAY_OF_WEEK) - 1;
        int monthMax = calendar.getActualMaximum(Calendar.DAY_OF_MONTH);
        System.out.println("一\t二\t三\t四\t五\t六\t日");
        for (int j = 0; j < startIndex - 1; j++) {
            System.out.print("\t");
        }
        for (int i = 1; i < monthMax + 1; i++) {
            System.out.print(i+"\t");
            if ((i + startIndex - 1) % 7 == 0) {
                System.out.println();
            }
        }
    }
    public static void test3(){
        System.out.println("请输入一个长度大于6的字符串：");
        String str = scanner.next();
        char[] chars = str.toCharArray();
        int len = chars.length;
        Random random = new Random();
        StringBuilder newStr = new StringBuilder();
        int index;
        for(int i = 0; i<6;i++){
            index = random.nextInt(len);
            newStr.append(chars[index]);
        }
        System.out.println(newStr.toString());
    }

}
