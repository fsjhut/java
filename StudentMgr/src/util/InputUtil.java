package util;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Scanner;


public class InputUtil {
    private static Scanner scanner;

    static {
        scanner = new Scanner(System.in);
    }

    private InputUtil() {

    }

    public static String input() {
        return scanner.next();
    }

    public static int inputInt() {
        return scanner.nextInt();
    }

    public static String input(String regex) {
        String str = scanner.next();
        if (str.matches(regex)) {
            return str;
        }
        while (!str.matches(regex)) {
            System.out.print("你的输入有误，请重新输入:");
            str = scanner.next();
        }
        return str;
    }

    public static String input(String regex, String message) {
        String str = scanner.next();
        if (str.matches(regex)) {
            return str;
        }
        while (!str.matches(regex)) {
            System.out.println(message);
            System.out.print("请重新输入:");
            str = scanner.next();
        }
        return str;
    }

    public static Date inputDate() {
        boolean isFlag = true;
        String year;
        String month;
        String day;
        Date birthday = null;
        while (isFlag) {
            System.out.print("请输入年：");
            year = InputUtil.input("^(19|20)\\d{2}$", "输入不合法");
            System.out.print("请输入月:");
            month = InputUtil.input("^([1-9]|1[0-2])$", "输入不合法");
            System.out.print("请输入日:");
            day = InputUtil.input("[1-9]|[1-2][0-9]|30|31", "输入不合法");
            if(month.length()==1){
                month = "0"+month;
            }
            if(day.length()==1){
                day = "0"+day;
            }
            String birth = year+"-"+month+"-"+day;
            SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd");
            try {
                birthday = simpleDateFormat.parse(birth);
                isFlag = false;
            } catch (ParseException e) {
                System.out.println("输入的时间有误！");
            }
        }
        return birthday;
    }
}
