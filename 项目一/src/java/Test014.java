package java;

/**
 * @author : SunHang
 * @className: Test014
 * @description:
 * @date: 2021/3/16 20:41
 * @version: 0.1
 * @since: 1.8
 */
import java.util.Scanner;

public class Test014 {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        System.out.println("请输入年数：");
        int year = scanner.nextInt();
        System.out.println("请输入月数：");
        int month = scanner.nextInt();
        System.out.println("请输入日数：");
        int day = scanner.nextInt();

        //  yearDay+countDay是  输入年数的1月1号到1900年1月1日的天数
        int yearDay = (year-1900)*365;
        int countDay=0;
        for (int i = 1900; i <=year ; i++) {
            if(i/4==0 && i/100!=0){
                countDay++;
            }
        }
        //输入的月数到1月的天数；
        int monthDay = 0;
        for (int i = 1; i <month ; i++) {
            switch(i){
                case 1:
                    monthDay=+0;
                    break;
                case 3: case 5: case 7: case 8: case 10: case 12:
                    monthDay+=31;
                    break;
                case 2:
                    if(i/4==0 && i/100!=0){
                        monthDay+=28;
                    }else{
                        monthDay+=29;
                    }
                    break;
                default:
                    monthDay+=30;
            }
        }
        //总天数
        System.out.println(yearDay+countDay+monthDay+day);

    }
}