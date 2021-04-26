package exec;

import java.util.Scanner;

/**
 * @author : SunHang
 * @className: Test01
 * @description:
 * @date: 2021/3/16 10:45
 * @version: 0.1
 * @since: 1.8
 */
public class Test {
    public static Scanner input = new Scanner(System.in);
    public static int sumDay;

    public static void main(String[] args) {
        Test test = new Test();
        System.out.println("--------第一道题--------");
        test.testNo1();
        System.out.println("--------第二道题--------");
        test.testNo2();
        System.out.println("--------第三道题--------");
        test.testNo3();
        System.out.println("--------第四道题--------");
        test.testNo4();
        System.out.println("--------第五道题--------");
        test.testNo5();
        System.out.println("--------第六道题--------");
        test.testNo6();
        System.out.println("--------第七道题(循环)--------");
        test.testNo7();
        System.out.println("--------第七道题(递归)--------");
        int temp = test.testNo7Recursion(9, 1);
        System.out.print("答案：");
        System.out.println("(递归)一共有" + temp + "个桃子");
        System.out.println("--------第八道题--------");
        test.testNo8();
        System.out.println("--------第九道题--------");
        System.out.println("请输入三角形的高度(n)：");
        int n = input.nextInt();
        test.testNo9(n);
        System.out.println("--------第十道题--------");
        test.testNo10();
        System.out.println("--------第十一道题--------");
        test.testNo11();
        System.out.println("--------第十二道题--------");
        test.testNo12();
        System.out.println("--------第十三道题--------");
        test.testNo13();
        System.out.println("--------第十四道题--------");
        test.testNo14();
        System.out.println("--------第十五道题--------");
        test.testNo15();
        System.out.println("--------第十六道题--------");
        test.testNo16();
    }

    public void testNo1() {
        System.out.print("请输入第一个数a：");
        int a = input.nextInt();
        System.out.print("请输入第二个数b：");
        int b = input.nextInt();
        System.out.print("答案：");
        if ((a % b == 0) || ((a + b) > 1000)) {
            System.out.println(a);
        } else {
            System.out.println(b);
        }
    }

    public void testNo2() {
        System.out.print("请输入第一个数a：");
        int a = input.nextInt();
        System.out.print("请输入第二个数b：");
        int b = input.nextInt();
        System.out.print("请输入第三个数c：");
        int c = input.nextInt();
        int min, max, temp;
        min = ((temp = (a < b) ? a : b) < c) ? temp : c;
        max = ((temp = (a > b) ? a : b) > c) ? temp : c;
        System.out.println("答案：");
        System.out.println("\t最小值min为：" + min);
        System.out.println("\t最大值max为：" + max);
    }

    /**
     * 求1到n之间所有能被3整除的整数个数，以及整除的整数和
     */
    public void testNo3() {
        System.out.print("请输入一个整数n：");
        int num = input.nextInt();
        int sum = 0;
        int total = 0;
        for (int i = 1; i < num; i++) {
            if (i % 3 == 0) {
                total++;
                sum += i;
            }
        }
        System.out.println("答案：");
        System.out.println("\t被3整除的个数为：" + total);
        System.out.println("\t被3整除的整数和为：" + sum);
    }

    /**
     * 需求说明：录入会员信息（会员号、会员生日、会员积分）并显示录入信息
     * 升级功能：
     * 判断录入是否合法（会员号必为4位整数），
     * 录入合法，显示录入的信息；
     * 不合法，显示“录入失败”
     */
    public void testNo4() {
        System.out.print("请输入会员号(4位整数)：");
        int num = input.nextInt();
        if ((num < 1000) || (num > 9999)) {
            System.out.println("录入失败！");
            return;
        }
        System.out.print("请输入会员的生日: ");
        input.nextLine();
        String date = input.nextLine();
        System.out.print("请输入会员的积分: ");
        Long score = input.nextLong();
        System.out.println("会员ID为：" + num);
        System.out.println("会员生日为：" + date);
        System.out.println("会员积分为：" + score);
    }

    /**
     * 	商场实行新的抽奖规则：会员号的百位数字等于产生的随机数字即为幸运会员。实现：
     * 	1、从键盘接收4位数会员号
     * 	2、生成随机数
     * 	int random = (int) (Math.random() * 10);
     * 	3、算出会员号中在百位的数字号码
     * 	int baiwei = custNo / 100 % 10;
     * 	3、使用if-else实现幸运抽奖
     */
    public void testNo5() {
        System.out.print("请输入会员号(4位整数)：");
        int no = input.nextInt();
        int random = (int) (Math.random() * 10);
//        int random = 0;
        int hundred = no / 100 % 10;
        if (hundred == random) {
            System.out.println("恭喜你，你是本次活动的幸运会员！");
        } else {
            System.out.println("祝你下次好运！");
        }
    }

    /**
     * 会员根据积分享受不同折扣，如下表。使用多重if结构计算会员折扣
     */
    public void testNo6() {
        System.out.print("请输入你的会员积分：");
        int score = input.nextInt();
        if (score >= 8000) {
            System.out.println("恭喜你拥有6折优惠！");
        } else if (score >= 4000) {
            System.out.println("恭喜你拥有7折优惠！");
        } else if (score >= 2000) {
            System.out.println("恭喜你拥有8折优惠！");
        } else {
            System.out.println("恭喜你拥有9折优惠！");
        }
    }

    /**
     * 猴子第一天摘下若干个桃子，当即吃了一半，
     * 还不过瘾，又多吃了一个。以后每天早上都吃了前一天剩下的一半零一个。
     * 到第十天早上想再吃时，见只剩一个桃子了。求第一天共摘了多少?
     */
    public void testNo7() {
        int eat = 3; // 第九天吃了3个桃子
        int remain = 1; // 第九天剩余1个桃子
        for (int i = 9; i > 1; i--) { //求第一天一共多少桃子
            remain = remain + eat; // 前一天剩下的总数
            eat = remain + 2;  // 前一天吃的数量
        }
        System.out.print("答案：");
        System.out.println("(循环)一共有" + (eat + remain) + "个桃子");
    }

    /**
     * 利用递归的方法求有多少桃子
     *
     * @param day 最后吃了多少天(吃了9天)
     * @param num 最后剩下的桃子个数(剩下1个桃子)
     * @return 返回最开始时的桃子个数
     */
    public int testNo7Recursion(int day, int num) {
        if (day == 0) {
            return num;
        } else {
            num = 2 * testNo7Recursion(day - 1, num) + 2;
            return num;
        }
    }

    /**
     * 要求用户输入一个0到2之间的整数
     * 如果用户输入0输出“你出的是石头”，
     * 如果用户输入1就输出“你出的是剪刀”，
     * 如果用户输入的是2就输出“你出的是布”，
     * 然后再问是否要继续出拳，
     * 如果回答“y”就重复以上过程，
     * 否则结束程序
     */
    public void testNo8() {
        boolean isFlag = true;
        while (isFlag) {
            System.out.println("请输入一个整数[0,2]：");
            int number = input.nextInt();
            if (number == 0) {
                System.out.println("你出的是石头");
            } else if (number == 1) {
                System.out.println("你出的是剪刀");
            } else if (number == 2) {
                System.out.println("你出的是布");
            } else {
                System.out.println("输入的数据有误");
            }
            System.out.println("是否继续出拳？(y/n)");
            char ans = input.next().charAt(0);
            if (ans != 'y') {
                isFlag = false;
            }
        }
    }

    /**
     * 打印如图类似的三角形
     */
    public void testNo9(int n) {
        int maxLength;
        for (int i = 1; i <= n; i++) {
            maxLength = 2 * i - 1;
            for (int j = 0; j < n - i; j++) {
                System.out.print(" ");
            }
            for (int k = 0; k < maxLength; k++) {
                System.out.print("*");
            }
            System.out.println();
        }
    }

    /**
     * 10. 打印菱形
     */
    public void testNo10() {
        System.out.println("请输入菱形的高度(奇数)：");
        int n = input.nextInt();
        int m = n / 2 + 1; //4
        testNo9(m);
        n = n - m;   // 3
        int maxLength;
        for (int i = n; i >= 0; i--) {
            maxLength = 2 * i - 1;
            for (int j = 0; j < n - i + 1; j++) {
                System.out.print(" ");
            }
            for (int k = 0; k < maxLength; k++) {
                System.out.print("*");
            }
            System.out.println();
        }
    }

    /**
     * 循环输出以下结果
     */
    public void testNo11() {
        for (int i = 5; i <= 100; i += 5) {
            int j = i / 5 * 2;
            System.out.println(j + "*" + i + "=" + i * j + "\t");
        }
    }

    /**
     * 12. 输出乘法口诀表
     */
    public void testNo12() {
        for (int i = 1; i <= 9; i++) {
            for (int j = 1; j <= i; j++) {
                System.out.print(j + "*" + i + "=" + i * j + "\t");
            }
            System.out.println();
        }
    }

    /**
     * 13.一张纸的厚度大约是0.08mm，对折多少次之后
     * 能达到珠穆朗玛峰的高度（8848.13米）？
     */
    public void testNo13() {
        boolean isFlag = true;
        int num = 0;
        double thickness = 0.08 / 1000;
        while (isFlag) {
            thickness = thickness * 2;
            num++;
            if (thickness >= 8848.13) {
                isFlag = false;
            }
        }
        System.out.println("折叠" + num + "次，可以达到珠穆拉玛峰高度");
    }

    /**
     * 14 计算用户输入的日期离1900年1月1日相距多少天
     */
    public void testNo14() {
        System.out.print("请输入年：");
        int year = input.nextInt();
        System.out.print("请输入月份：");
        int month = input.nextInt();
        System.out.print("请输入日期：");
        int day = input.nextInt();
        int oneYearDay = getYearDays(year, month, day);
        if (year >= 1900) {
            getDays(year-1);
            System.out.println("距离1900年1月1日有 " + (oneYearDay + sumDay - 1) + " 天");
        } else if (year > 0) {
            getDays2(year);
            System.out.println("距离1900年1月1日有 " + (sumDay - oneYearDay + 1) + " 天");
        } else {
            System.out.println("输入的年份非法！");
        }
    }

    /**
     * 15 购物管理系统生日问候
     * 请输入今天的日期（月/日<用两位数表示：）： 05/29
     * 请输入客户生日：08/09
     * 然后判断是否今天生日
     * 是否继续（y/n)
     * 如果是y就重复以上步骤，否则退出程序
     * 字符串判断相等： "05/29".equals(birthday);
     */
    public void testNo15() {
        boolean isFlag = true;
        while (isFlag) {
            System.out.println("请输入今天的日期(月/日)：");
            String date = input.next();
            System.out.println("请输入客户的生日(月/日)：");
            String birthday = input.next();
            if (date.equals(birthday)) {
                System.out.println("祝你生日快乐！");
                break;
            } else {
                System.out.println("是否继续(y/n)?");
                char ch = input.next().charAt(0);
                if (ch != 'y') {
                    isFlag = false;
                }
            }
        }
    }

    /**
     * 16 打印出所有的 "水仙花数"
     * 所谓 "水仙花数"是指一个三位数，其各位数字立方和等于该数本身。
     * 例如：153是一个 "水仙花数 "，因为153=1的三次方＋5的三次方＋3的三次方。
     */
    public void testNo16() {
        int cubeSum = 0;  //cube:立方
        int single = 0, tens = 0, hundred = 0;
        System.out.println("所有的水仙花数为：");
        for (int i = 100; i <= 999; i++) {
            hundred = i / 100;
            int tmp = i % 100;
            tens = tmp / 10;
            single = tmp % 10;
            cubeSum = single * single * single + tens * tens * tens + hundred * hundred * hundred;
            if (i == cubeSum)
                System.out.print(i + "\t");
        }
    }

    /**
     * 得到当年份的月和日期是属于当前年份的第多少天
     * @param year  输入的年
     * @param month 输入的月
     * @param day   输入的日期
     * @return
     */
    public int getYearDays(int year, int month, int day) {
        int getday = 0;
        int twoMonth;
        if (((year % 4 == 0) && (year % 100 != 0)) || (year % 400 == 0)) {
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

    /**
     * 大于等于1900的年份，距离有多少天
     *
     * @param year 输入的年份
     */
    public void getDays(int year) {
        int day;
        if (year == 1899) {
            return;
        }
        if (((year % 4 == 0) && (year % 100 != 0)) || (year % 400 == 0)) {
            day = 366;
        } else {
            day = 365;
        }
        sumDay = sumDay + day;
        year--;
        getDays(year);
    }

    /**
     * 小于等于1900的年份，距离有多少天
     *
     * @param year 输入的年份
     */
    public void getDays2(int year) {
        int day;
        if (year == 1900) {
            return;
        }
        if ((year % 4 == 0) && (year % 400 != 0) || year % 400 == 0) {
            day = 366;
        } else {
            day = 365;
        }
        sumDay = sumDay + day;
        year++;
        getDays2(year);
    }
}
