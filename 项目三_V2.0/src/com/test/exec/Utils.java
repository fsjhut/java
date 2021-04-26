package com.test.exec;

import lombok.Setter;

import java.util.Scanner;

/**
 * @author SunHang
 * @className: Utils
 * @description:
 * @createTime 2021/3/21 19:43
 */

public class Utils {
    private static Scanner scanner = new Scanner(System.in);
    static UserDao userDao = new UserDao();
    // 标记当前已登录对象所对应的数组索引
    static int loggedIdex = -1;

    public  static void exitSystem() {
        System.out.println("确认退出系统吗？(y/n)");
        char ch = scanner.next().charAt(0);
        if (ch == 'y') {
            System.out.println("系统退出，谢谢使用！");
            System.exit(-1);
        }
    }


    // 注册时需要传给一个User的数组
    // index 是公司索引
    public static User[] register(User[] userArr, int index, int cls) {
        String trueName = "";
        System.out.println("欢迎您进行注册, 请填写个人信息：");
        boolean isFlag = true;
        while (isFlag) {
            System.out.print("用户名：");
            trueName = scanner.next();
            if (isRegisterArray(trueName, userArr)) { // 已经注册
                System.out.println("该用户名已注册，请重新输入用户名");
            } else {
                isFlag = false;
            }
        }
        System.out.print("密码：");
        String truePassword = scanner.next();
        // 判断是员工还是用户
        User user;
        if(cls == 1){
            user = new User(trueName, truePassword,index);
        }else{
            System.out.print("地址:");
            String area = scanner.next();
            user = new User(trueName, truePassword,area,index);
        }
        return userDao.addUser(user,userArr);
    }

    public static boolean isRegisterArray(String name, User[] userArr) {
        boolean isFlag = false;
        for (int i = 0; i < userArr.length; i++) {
            if (name.equals(userArr[i].name)) {
                isFlag = true;
                break;
            }
        }
        return isFlag;
    }


    public static void login(User[] userArr) {
        int num = 3;
        while (true) {
            System.out.print("请输入用户名：");
            String name = scanner.next();
            System.out.print("请输入密码：");
            String password = scanner.next();

            // 判断输入的用户名和密码是否存在于数组当中
            if (isInArray(name, password, userArr)) {
                System.out.println("登录成功，欢迎您：" + name);
                return;
            } else {
                if (num != 1) {
                    num--;
                    System.out.println("请重新输入！");
                    System.out.println("您还有" + num + "次机会！");
                } else {
                    System.out.println("机会使用完毕，程序结束!");
                    System.exit(-1);
                }
            }
        }
    }

    public  static boolean isInArray(String name, String password, User[] arr) {
        boolean isFlag = false;
        for (int i = 0; i < arr.length; i++) {
            if (name.equals(arr[i].name) && password.equals(arr[i].passWord)) {
                isFlag = true;
                loggedIdex = i;
                break;
            }
        }
        return isFlag;
    }

    public static int findIndex(String userName, User[] userArr) {
        for(int i = 0; i<userArr.length;i++){
            if(userName.equals(userArr[i].name)){
                return i;
            }
        }
        return -1;
    }
}
