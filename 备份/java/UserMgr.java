package com.test.java;

import java.util.Scanner;

/**
 * @author SunHang
 * @className: UserMgr
 * @description:
 * @createTime 2021/3/21 11:42
 */


public class UserMgr {
    private static Scanner scanner = new Scanner(System.in);
    // 程序退出的标记
    private static boolean isLogin;
    private static boolean isFlag = true;
    static Views views = new Views();
    static UserDao userDao = new UserDao();
    static BikeMgr bikeMgr = new BikeMgr();
    static CompanyDao companyDao = new CompanyDao();

    public static void registerViewMenu(CompanyMgr companyMgr) {
        while (true) {
            views.registerView();
            System.out.print("请输入操作编号：");
            String num = scanner.next();
            int companyIndex;
            String companyName;
            switch (num) {
                case ("1"):
                    System.out.println("[单车管理系统> 注册]");
                    // 注册账户类别
                    System.out.print("请输入注册账号类别(1:公司员工 2:用户):");
                    int cls = scanner.nextInt();
                    // 根据公司名称进行查找到公司在公司对象中的索引int
                    System.out.println("请输入需要注册的公司名称: ");
                    companyName = scanner.next();
                    // 一个查找的方法 根据公司名称找到公司的索引
                    companyIndex = companyDao.findIndex(companyName);
                    if(companyIndex==-1){
                        System.out.println("公司名字输入有误!");
                        break;
                    }
                    User[] temp;
                    if (cls == 1) {
                        temp = Utils.register(companyMgr.companiesArr[companyIndex].staff, companyIndex, cls);
                        companyMgr.companiesArr[companyIndex].staff = temp;
                    } else {
                        temp = Utils.register(companyMgr.companiesArr[companyIndex].users, companyIndex, cls);
                        companyMgr.companiesArr[companyIndex].users = temp;
                    }
                    break;
                case ("2"):
                    System.out.println("[单车管理系统> 登录]");
                    System.out.print("请输入账号类别(1:公司员工 2:用户 3: 超级管理员):");
                    int loginCls = scanner.nextInt();
                    if (loginCls == 1) {
                        System.out.print("请输入你所在公司名称:");
                        companyName = scanner.next();
                        companyIndex = companyDao.findIndex(companyName);
                        if(companyIndex==-1){
                            System.out.println("公司名字输入有误!");
                            break;
                        }
                        Utils.login(companyMgr.companiesArr[companyIndex].staff);
                        // 进入单车管理系统
                        bikeMgr.bikeMgrMenu(companyMgr.companiesArr[companyIndex]);
                    } else if (loginCls == 2) {
                        System.out.print("请输入你想使用的单车公司(名字):");
                        companyName = scanner.next();
                        companyIndex = companyDao.findIndex(companyName);
                        if(companyIndex==-1){
                            System.out.println("公司名字输入有误!");
                            break;
                        }
                        Utils.login(companyMgr.companiesArr[companyIndex].users);
                        // 显示借车系统逻辑代码
                        Views.userView();
                        System.out.print("请输入你的操作: ");
                        int opt = scanner.nextInt();
                        if (opt == 1) {
                            BikeDao.borrowBike(companyMgr.companiesArr[companyIndex]);
                        } else if (opt == 2) {
                            BikeDao.returnBike(companyMgr.companiesArr[companyIndex]);
                        } else {
                            Utils.exitSystem();
                        }
                    } else if (loginCls == 3) {
                        Utils.login(CompanyMgr.adminArry);
                        companyMgr.cmpMgrMenu();
                    } else {
                        System.out.println("输入错误!");
                    }
                case ("3"):
                    Utils.exitSystem();
                    break;
                default:
                    System.out.println("你输入的信息有误！");
            }
        }
    }

    // 属于二级菜单,在主菜单中进入该界面
    public void userMgrMenu(BikeCompany bikecompany) {
        boolean isFlag = true;
        while (isFlag) {
            Views.userMgrView();
            System.out.print("请输入操作编号：");
            String num = scanner.next();
            switch (num) {
                // 添加用户
                case ("1"):
                    int tmp = userDao.addUserView();
                    // 查找公司索引
                    int bikeCompanyIndex = companyDao.findIndex(bikecompany.bikeCompanyName);
                    if(bikeCompanyIndex==-1){
                        System.out.println("公司名字输入有误!");
                        break;
                    }
                    if (tmp == 1) {
                        bikecompany.staff = Utils.register(bikecompany.staff, bikeCompanyIndex,tmp);
                    } else {
                        bikecompany.users = Utils.register(bikecompany.users, bikeCompanyIndex,tmp);
                    }
                    break;
                case ("2"):
                    userDao.showMember(bikecompany);
                    break;
                case ("3"):
                    userDao.deleteUser(bikecompany);
                    break;
                case ("4"):
                    userDao.editUser(bikecompany);
                    break;
                case ("5"):
                    System.out.println("确认注销账号吗?(y/n)");
                    char temp = scanner.next().charAt(0);
                    if (temp == 'y') {
                        isLogin = false;
                        System.out.println("注销成功!");
                        registerViewMenu(MainFuc.companyMgr);
                    }
                    break;
                case ("6"):
                    isFlag = false;
                    break;
                case ("7"):
                    Utils.exitSystem();
                    break;
                default:
                    System.out.println("你输入的信息有误！");
            }
        }
    }
}
