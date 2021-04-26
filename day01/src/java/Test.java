package java;

/**
 * @author : SunHang
 * @className: Test
 * @description:
 * @date: 2021/3/15 18:00
 * @version: 0.1
 * @since: 1.8
 */
public class Test {
    public static void main(String[] args) {
        Test test = new Test();
        test.lightYear();
        test.dayTest();
        test.getArea();
        test.getMax();
        test.getMaxAreaFan();
        test.exchangeNumber();
        test.digitSum();
    }

    // 计算光年的距离
    public void lightYear() {
        long speed = 299792458;
        int year = 365;
        int day = 24;
        int hour = 3600;
        long lightYear;
        lightYear = speed * year * day * hour / 1000;
        System.out.print("第一题：");
        System.out.println("一光年是：" + lightYear + " 千米");
    }

    //根据天数（46）计算周数和剩余的天数
    public void dayTest() {
        int dayNumber = 46;
        int weekNumber = dayNumber / 7;
        int remainDay = dayNumber % 7;
        System.out.print("第二题：");
        System.out.println("周数为：" + weekNumber);
        System.out.println("\t\t剩余的天数为：" + remainDay);
    }

    // 已知圆的半径radius= 1.5，求其面积 (Java中π用Math.PI表示)
    public void getArea() {
        float radius = 1.5F;
        double area = radius * radius * Math.PI;
        System.out.print("第三题：");
        System.out.println("半径为1.5的圆面积是：" + area);
    }

    // 自己给定5个整数，通过编程求出最大值(使用三元运算符)
    public void getMax() {
        int a = 10, b = 20, c = 5, d = 7, e = 5;
        int max;
        max = (a > b) ? a : b;
        max = (max > c) ? max : c;
        max = (max > d) ? max : d;
        max = (max > e) ? max : e;
        System.out.print("第四题：");
        System.out.println("5个数中最大的数为：" + max);
    }

    /*
    A款折叠扇：展开后角度为134.6°,扇骨长26.5cm
    B款圆扇：扇柄长12.3cm，扇子总长度36.5cm
    注：圆形面积 = 3.14 * 半径平方
 	    扇形面积 = 3.14 * 半径平方 * (度数/360)
     */
    public void getMaxAreaFan() {
        double angle = 134.6;
        double lnA = 26.5;
        double lnB1 = 12.3;
        double lnB2 = 36.5;
        double areaA = Math.PI * angle * lnA * lnA / 360;
        double radius = (lnB2 - lnB1) / 2;
        double areaB = Math.PI * radius * radius;
        double maxArea = (areaA > areaB) ? areaA : areaB;
        char selectFan = (maxArea == areaA) ? 'A' : 'B';
        System.out.print("第五题：");
        System.out.println("两款扇子应该选择：" + selectFan + ", 该扇形面积为：" + maxArea);
    }

    //变量a和b的值互换  例如：int a = 10,b=20;  结果：a=20,b=10;
    public void exchangeNumber() {
        int a = 10, b = 20;
        int temp;
        temp = a;
        a = b;
        b = temp;
        System.out.print("第六题：");
        System.out.println("交换以后: a = " + a + ", b = " + b);
    }

    // 定义一个变量，是一个三位数，求各个位数的和. 如：123，结果是1+2+3=6
    public void digitSum() {
        int number = 123;
        int single, ten, hundred;
        hundred = number / 100;
        ten = (number % 100) / 10;
        single = number % 10;
        int sum = hundred + ten + single;
        System.out.print("第七题：");
        System.out.println(number + "各个位数的和是：" + sum);
    }

}


