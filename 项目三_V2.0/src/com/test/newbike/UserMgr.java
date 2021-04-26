package com.test.newbike;

import java.util.Scanner;

/**
 * @author SunHang
 * @className: UserMgr
 * @description:
 * @createTime 2021/3/21 11:42
 */


public class UserMgr {
    public static void registerViewMenu(CompanyMgr companyMgr) {
        while (true) {
            Views.registerView();
            System.out.print("请输入操作编号：");
            String num = Constant.scanner.next();
            int companyIndex;
            String companyName;
            switch (num) {
                case ("1"):
                    System.out.println("[单车管理系统> 注册]");
                    // 注册账户类别
                    System.out.print("请输入注册账号类别(1:公司员工 2:用户):");
                    int cls = Constant.scanner.nextInt();
                    // 根据公司名称进行查找到公司在公司对象中的索引int
                    System.out.println("请输入需要注册的单车公司名称: ");
                    companyName = Constant.scanner.next();
                    // 一个查找的方法 根据公司名称找到公司的索引
                    companyIndex = Utils.findIndex(companyName);
                    if (companyIndex == -1) {
                        System.out.println("公司名字输入有误!");
                        break;
                    }
                    User[] temp;
                    if (cls == 1) {
                        temp = Utils.register(companyMgr.getCompaniesArr()[companyIndex].getStaff(), companyIndex, cls);
                        companyMgr.getCompaniesArr()[companyIndex].setStaff(temp);
                    } else if(cls ==2){
                        temp = Utils.register(companyMgr.getCompaniesArr()[companyIndex].getUsers(), companyIndex, cls);
                        companyMgr.getCompaniesArr()[companyIndex].setUsers(temp);
                    }else{
                        System.out.println("输入错误!");
                    }
                    break;
                case ("2"):
                    System.out.println("[单车管理系统> 登录]");
                    System.out.print("请输入账号类别(1:公司员工 2:用户 3: 超级管理员):");
                    String loginCls = Constant.scanner.next();
                    if ("1".equals(loginCls)) {
                        System.out.print("请输入你所在公司名称:");
                        companyName = Constant.scanner.next();
                        companyIndex = Utils.findIndex(companyName);
                        if (companyIndex == -1) {
                            System.out.println("公司名字输入有误!");
                            break;
                        }
                        Utils.login(companyMgr.getCompaniesArr()[companyIndex].getStaff());
                        // 进入单车管理系统
                        Constant.bikeMgr.bikeMgrMenu(companyMgr.getCompaniesArr()[companyIndex]);
                    } else if ("2".equals(loginCls)) {
                        System.out.print("请输入你想使用的单车公司(名字):");
                        companyName = Constant.scanner.next();
                        companyIndex = Utils.findIndex(companyName);
                        if (companyIndex == -1) {
                            System.out.println("公司名字输入有误!");
                            break;
                        }
                        Utils.login(companyMgr.getCompaniesArr()[companyIndex].getUsers());
                        // 显示借车系统逻辑代码
                        do {
                            Views.userView();
                            System.out.print("请输入你的操作: ");
                            String opt = Constant.scanner.next();
                            if ("1".equals(opt)) {
                                BikeDao.borrowBike(companyMgr.getCompaniesArr()[companyIndex]);
                            } else if ("2".equals(opt)) {
                                BikeDao.returnBike(companyMgr.getCompaniesArr()[companyIndex]);
                            } else if ("3".equals(opt)){
                                UserMgr.registerViewMenu(MainFuc.companyMgr);
                            }else if("4".equals(opt)){
                                Utils.exitSystem();
                            }else{
                                System.out.println("输入错误!");
                            }
                        } while (true);
                    } else if ("3".equals(loginCls)) {
                        Utils.login(companyMgr.getAdminArr());
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
            String num = Constant.scanner.next();
            switch (num) {
                // 添加用户
                case ("1"):
                    int tmp = Constant.userDao.addUserView();
                    // 查找公司索引
                    int bikeCompanyIndex = Utils.findIndex(bikecompany.getCompanyName());
                    if (bikeCompanyIndex == -1) {
                        System.out.println("公司名字输入有误!");
                        break;
                    }
                    if (tmp == 1) {
                        bikecompany.setStaff(Utils.register(bikecompany.getStaff(), bikeCompanyIndex, tmp));
                    } else {
                        bikecompany.setUsers(Utils.register(bikecompany.getUsers(), bikeCompanyIndex, tmp));
                    }
                    break;
                case ("2"):
                    Constant.userDao.showMember(bikecompany);
                    break;
                case ("3"):
                    Constant.userDao.deleteUser(bikecompany);
                    break;
                case ("4"):
                    Constant.userDao.editUser(bikecompany);
                    break;
                case ("5"):
                    System.out.println("确认注销账号吗?(y/n)");
                    char temp = Constant.scanner.next().charAt(0);
                    if (temp == 'y') {
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
