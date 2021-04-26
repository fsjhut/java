package com.test.exe;

import java.util.Scanner;

/**
 * @Title:
 * @Description: 登录界面和注册界面分开
 * @date: 2021/3/19 17:00
 * @version: 2.0
 */
public class LuckyDraw {
    private static Scanner scanner = new Scanner(System.in);
    private static boolean isRegister;
    private static boolean isLogin;
    // NUM为数组的最大长度
    private static final int NUM = 10;
    // 保存当前已保存数组的索引值
    private static int index = 0;
    // 程序退出的标记
    private static boolean isFlag = true;
    // 标记当前已登录对象所对应的数组索引
    private static int loggedIdex = -1;
    // 抽奖对象
    private static LuckyDraw luckyDraw = new LuckyDraw();
    // 成员对象
    private static Member[] arr = new Member[NUM];

    public static void main(String[] args) {
        registerViewMenu();
    }

    //

    // 注册界面入口
    private static void registerViewMenu() {
        while (isFlag && !isLogin) {
            luckyDraw.registerView();
            System.out.print("请输入操作编号：");
            String num = scanner.next();
            switch (num) {
                case ("1"):
                    System.out.println("[幸运抽奖系统> 注册]");
                    if(index>=NUM){
                        System.out.println("非常抱歉,会员已满暂时无法注册!");
                    }else{
                        luckyDraw.register();
                    }
                    break;
                case ("2"):
                    System.out.println("[幸运抽奖系统> 登录]");
                    if (isRegister) {
                        if (luckyDraw.login()) {
                            loginViewMenu();
                        }
                    } else {
                        System.out.println("请先注册本抽奖系统！");
                    }
                    break;
                case ("3"):
                    luckyDraw.exitSystem();
                    break;
                default:
                    System.out.println("你输入的信息有误！");
            }
        }
    }

    // 登录以后的界面
    private static void loginViewMenu() {
        while (isFlag && isLogin) {
            luckyDraw.loginView();
            System.out.print("请输入操作编号：");
            String num = scanner.next();
            switch (num) {
                case ("1"):
                    System.out.println("[幸运抽奖系统> 抽奖]");
                    luckyDraw.lottery();
                    break;
                case ("2"):
                    System.out.println("[幸运抽奖系统> 显示所有用户]");
                    luckyDraw.viewMember(arr);
                    break;
                case ("3"):
                    System.out.println("确认注销账号吗?(y/n)");
                    char temp = scanner.next().charAt(0);
                    if(temp=='y'){
                        isLogin = false;
                        System.out.println("注销成功!");
                        registerViewMenu();
                    }
                    break;
                case ("4"):
                    luckyDraw.exitSystem();
                    break;
                default:
                    System.out.println("你输入的信息有误！");
            }

        }
    }

    private void register() {
        boolean isFlag = true;
        String trueName = "";
        System.out.println("请填写个人注册信息：");
        while (isFlag) {
            System.out.print("用户名：");
            trueName = scanner.next();
            if (isRegisterArray(trueName)) { // 已经注册
                System.out.println("该用户名已注册，请重新输入用户名");
            } else {
                isFlag = false;
            }
        }
        System.out.print("密码：");
        String truePassword = scanner.next();
        int trueNo = (int) (Math.random() * 9000 + 1000);
        System.out.println("注册成功，请牢记您的会员卡号");
//        System.out.println(trueName + "\t\t\t" + truePassword + "\t" + trueNo);
        arr[index] = new Member(trueNo, trueName, truePassword);
        System.out.println("用户名\t\t密码\t\t\t会员卡号");
        System.out.println(arr[index].info());
        index++;
        isRegister = true;
    }

    // 判断是否已经注册
    // 判断输入的账号密码是否在数组中？

    private boolean isRegisterArray(String name) {
        boolean isFlag = false;
        for (int i = 0; i < index; i++) {
            if (name.equals(arr[i].name)) {
                isFlag = true;
                break;
            }
        }
        return isFlag;
    }

    // 用户登陆程序
    private boolean login() {
        int num = 3;
        while (true) {
            System.out.print("请输入用户名：");
            String name = scanner.next();
            System.out.print("请输入密码：");
            String password = scanner.next();
            // 判断输入的用户名和密码是否存在于数组当中
            if (isInArray(name, password)) {
                System.out.println("登录成功，欢迎您：" + name);
                isLogin = true;
                return true;
            } else {
                if (num != 1) {
                    num--;
                    System.out.println("请重新输入！");
                    System.out.println("您还有" + num + "次机会！");
                } else {
                    System.out.println("机会使用完毕，流程结束.");
                    break;
                }
            }
        }
        return false;
    }

    // 判断输入的账号密码是否在数组中？
    private boolean isInArray(String name, String password) {
        boolean isFlag = false;
        for (int i = 0; i < index; i++) {
            if (name.equals(arr[i].name) && password.equals(arr[i].password)) {
                isFlag = true;
                loggedIdex = i;
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
            if (arr[loggedIdex].no == numArr[i]) {
                isLuckMember = true;
            }
        }
        System.out.println();
        System.out.println("您的会员ID是: " + arr[loggedIdex].no);
        if (isLuckMember) {
            System.out.println("恭喜您("+arr[loggedIdex].name+")成为本日幸运会员！");
        } else {
            System.out.println("抱歉！您不是今日幸运会员！");
        }
    }

    // 显示注册界面菜单
    private void registerView() {
        System.out.println("*****欢迎进入幸运抽奖系统*********");
        System.out.println("             1.注册              ");
        System.out.println("             2.登录              ");
        System.out.println("             3.退出系统          ");
        System.out.println("*********************************");
    }

    // 显示登录界面
    private void loginView() {
        System.out.println("*******欢迎进入幸运抽奖系统**********");
        System.out.println("             1.抽奖                 ");
        System.out.println("             2.显示所有会员         ");
        System.out.println("             3.注销账号             ");
        System.out.println("             4.退出系统             ");
        System.out.println("***********************************");
    }

    // 显示所有会员的信息
    private void viewMember(Member[] arr) {
        System.out.println("用户名\t\t密码\t\t\t会员卡号");
        for (int i = 0; i < index; i++) {
            System.out.println(arr[i].info());
        }
    }

    // 退出系统的操作

    private void exitSystem() {
        System.out.println("确认退出系统吗？(y/n)");
        char ch = scanner.next().charAt(0);
        if (ch == 'y') {
            System.out.println("系统退出，谢谢使用！");
            isFlag = false;
        }
    }
}


class Member {
    int no;
    String name, password;

    Member(int no, String name, String password) {
        this.no = no;
        this.name = name;
        this.password = password;
    }

    String info() {
        return name + "\t\t\t" + password + "\t\t\t" + no;
    }
}
