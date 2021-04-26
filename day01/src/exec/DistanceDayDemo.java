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
public class DistanceDayDemo {
    public static int sumDay;
    public static Scanner scanner = new Scanner(System.in);
    public static DistanceDayDemo distanceDayDemo = new DistanceDayDemo();
    public static void main(String[] args){
        System.out.print("请输入年：");
        int year = scanner.nextInt();
        System.out.print("请输入月份：");
        int month = scanner.nextInt();
        System.out.print("请输入日期：");
        int day = scanner.nextInt();
        int oneYearDay = distanceDayDemo.getYearDays(year,month, day);
        distanceDayDemo.getDays(year);
        System.out.println("距离1990年1月1日有 " + (oneYearDay+sumDay) + " 天");
        System.out.println("是否要计算该日期距离你的出生日期有多少天？(true/false)");
        boolean isFlag = scanner.nextBoolean();
        if(isFlag){
            int day1 = sumDay + oneYearDay;
//            System.out.println("day1: "+day1);
            sumDay = 0;
            int day2 = distanceDayDemo.getBirthdaySum();
//            System.out.println(day2);
            int day3 = day1 - day2;
            System.out.println("该日期距离你的出生已经："+day3+" 天");
        }

    }

    public int getYearDays(int year, int month,int day){
        int getday = 0;
        int twoMonth ;
        if(((year/4==0)&&(year/100!=0))||(year/400==0)){
            twoMonth = 29;
        }else{
            twoMonth = 28;
        }
        switch (month){
            case(12):{
                getday += 30;
            }
            case(11):{
                getday += 31;
            }
            case(10):{
                getday += 30;
            }
            case(9):{
                getday += 31;
            }
            case(8):{
                getday += 31;
            }
            case(7):{
                getday += 30;
            }
            case(6):{
                getday += 31;
            }
            case(5):{
                getday += 30;
            }
            case(4):{
                getday += 31;
            }
            case(3):{
                getday += twoMonth;
            }
            case(2):{
                getday += 31;
            }
            case(1):{
                getday += day;
            }
        }

        return getday;
    }

    public void getDays(int year){
        int day;
        if(year==1990){
            return;
        }
        if((year/4==0)&&(year/100!=0)||year/400==0){
            day = 366;
        }else{
            day = 365;
        }
        sumDay = sumDay + day;
        year--;
        getDays(year);
    }
    public int getBirthdaySum(){
        System.out.print("请输入你的出生年份：");
        int year = scanner.nextInt();
        System.out.print("请输入你的出生月份：");
        int month = scanner.nextInt();
        System.out.print("请输入你的出生日期：");
        int day = scanner.nextInt();
        distanceDayDemo.getDays(year);
        int oneYearDay = distanceDayDemo.getYearDays(year,month,day);
        return oneYearDay+sumDay;
    }
}
