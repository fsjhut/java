package com.test.java;

import java.util.Scanner;

/**
 * 项目一：幸运抽奖系统
 *
 * @author : SunHang
 * @className: LuckyDraw
 * @description:
 * @date: 2021/3/16 19:14
 * @version: 0.1
 * @since: 1.8
 */
public class LuckyDraw1 {
    public static Scanner scanner = new Scanner(System.in);
    public static boolean isRegister;
    public static boolean isLogin;
    public static final int NUM = 10;
    public static int index = 0;
    public String trueName, truePassword;
    public int trueNo;

    public static void main(String[] args) {
        LuckyDraw luckyDraw = new LuckyDraw();
        boolean isFlag = true;
        while (isFlag) {
            luckyDraw.viewMenu();
            Member[] arr = new Member[NUM];
            System.out.print("请输入操作编号：");
            int num = scanner.nextInt();
            switch (num) {
                case (1):
                    System.out.println("[幸运抽奖系统> 注册]");
                    if (!isRegister) {
                        luckyDraw.register(arr);
                    } else {
                        System.out.println("你已注册本抽奖系统！");
                    }
                    break;
                case (2):
                    System.out.println("[幸运抽奖系统> 登录]");
                    if (isRegister) {
                        if (isLogin) {
                            System.out.println("你已经登录本系统！");
                        } else {
                            luckyDraw.login(arr);
                        }
                    } else {
                        System.out.println("请先注册本抽奖系统！");
                    }
                    break;
                case (3):
                    System.out.println("[幸运抽奖系统> 抽奖]");
                    if (isLogin) {
                        luckyDraw.lottery();
                    } else {
                        System.out.println("请你先登录本系统！");
                    }
                    break;
                default:
                    System.out.println("你输入的信息有误！");
            }
            System.out.println("继续吗？(y/n)");
            char ch = scanner.next().charAt(0);
            if (ch != 'y') {
                isFlag = false;
                System.out.println("系统退出，谢谢使用！");
            }
        }

    }

    // 注册程序
    private void register(Member[] arr) {
        System.out.println("请填写个人注册信息：");
        System.out.print("用户名：");
        trueName = scanner.next();
        System.out.print("密码：");
        truePassword = scanner.next();
        trueNo = (int) (Math.random() * 9000 + 1000);
        System.out.println("注册成功，请牢记您的会员卡号");
        System.out.println("用户名\t\t密码\t\t会员卡号\t\t");
        System.out.println(trueName + "\t\t\t" + truePassword + "\t" + trueNo);
        arr[index] = new Member();
        arr[index].name = trueName;
        arr[index].no = trueNo;
        arr[index].password = truePassword;
        index++;
        isRegister = true;
    }

    private void login(Member[] arr) {
        int num = 3;
        while (num > 0) {
            System.out.print("请输入用户名：");
            String name = scanner.next();
            System.out.print("请输入密码：");
            String password = scanner.next();
            // 判断输入的用户名和密码是否存在于数组当中
            if (isInArray(name, password, arr)) {
                System.out.println("欢迎您：" + name);
                isLogin = true;
                break;
            } else {
                if (num != 0) {
                    System.out.println("请重新输入！");
                    num--;
                    System.out.println("您还有" + num + "次机会！");
                } else {
                    System.out.println("机会使用完毕，流程结束.");
                    break;
                }
            }
        }
    }

    // 判断输入的账号密码是否在数组中？
    private boolean isInArray(String name, String password, Member[] arr) {
        boolean isFlag = false;
        for (Member mem : arr) {
            if (name.equals(mem.name) && password.equals(mem.password)) {
                isFlag = true;
                break;
            }
        }
        return isFlag;

    }

    // 抽奖系统
    private void lottery() {
        int[] numArr = new int[5];
        boolean isLuckMember = false;
        System.out.println("生成的5个幸运会员ID是：");
        for (int i = 0; i < numArr.length; i++) {
            numArr[i] = (int) (Math.random() * 9000 + 1000);
            System.out.print(numArr[i] + "\t");
            if (trueNo == numArr[i]) {
                isLuckMember = true;
            }
        }
        System.out.println();
        if (isLuckMember) {
            System.out.println("恭喜您成为本日幸运会员！");
        } else {
            System.out.println("抱歉！您不是今日幸运会员！");
        }
    }

    // 显示菜单
    public void viewMenu() {
        System.out.println("*****欢迎进入幸运抽奖系统*****");
        System.out.println("             1.注册           ");
        System.out.println("             2.登录           ");
        System.out.println("             3.抽奖           ");
        System.out.println("******************************");
    }
}

class Member {
    int no;
    String name, password;
}
