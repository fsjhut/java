package exec;


import java.util.Scanner;

/**
 * @author : SunHang
 * @className: DistanceDayDemo
 * @description: 求出输入的年月日距离1990年1月1日，有多少天
 * @date: 2021/3/15 22:09
 * @version: 0.1
 * @since: 1.8
 */
public class DistanceDayDemo2 {
    public static int sumDay;
    public static Scanner scanner = new Scanner(System.in);
    public static DistanceDayDemo2 distanceDayDemo = new DistanceDayDemo2();

    public static void main(String[] args) {
        System.out.print("请输入年：");
        int year = scanner.nextInt();
        System.out.print("请输入月份：");
        int month = scanner.nextInt();
        System.out.print("请输入日期：");
        int day = scanner.nextInt();
        if(year>=1990){
            int oneYearDay = distanceDayDemo.getYearDays(year, month, day);
            distanceDayDemo.getDays(year);
            System.out.println("距离1990年1月1日有 " + (oneYearDay + sumDay) + " 天");
        } else if(year>=0){
            int oneYearDay = distanceDayDemo.getYearDays(year, month, day);
            distanceDayDemo.getDays2(year);
            System.out.println("距离1990年1月1日有 " + (sumDay - oneYearDay) + " 天");

        }else{
            System.out.println("输入的年份非法！");
        }



    }

    public int getYearDays(int year, int month, int day) {
        int getday = 0;
        int twoMonth;
        if (((year / 4 == 0) && (year / 100 != 0)) || (year / 400 == 0)) {
            twoMonth = 29;
        } else {
            twoMonth = 28;
        }
        switch (month) {
            case (12): {
                getday += 30;
            }
            case (11): {
                getday += 31;
            }
            case (10): {
                getday += 30;
            }
            case (9): {
                getday += 31;
            }
            case (8): {
                getday += 31;
            }
            case (7): {
                getday += 30;
            }
            case (6): {
                getday += 31;
            }
            case (5): {
                getday += 30;
            }
            case (4): {
                getday += 31;
            }
            case (3): {
                getday += twoMonth;
            }
            case (2): {
                getday += 31;
            }
            case (1): {
                getday += day;
            }
        }

        return getday;
    }

    public void getDays(int year) {
        int day;
        if (year == 1990) {
            return;
        }
        if ((year / 4 == 0) && (year / 100 != 0) || year / 400 == 0) {
            day = 366;
        } else {
            day = 365;
        }
        sumDay = sumDay + day;
        year--;
        getDays(year);
    }

    public void getDays2(int year) {
        int day;
        if (year == 1990) {
            return;
        }
        if ((year / 4 == 0) && (year / 100 != 0) || year / 400 == 0) {
            day = 366;
        } else {
            day = 365;
        }
        sumDay = sumDay + day;
        year++;
        getDays2(year);
    }

}
