package com.test.exec;

import java.util.Scanner;

/**
 * @author SunHang
 * @className: SharedBikeMgr
 * @description:
 * @createTime 2021/3/20 16:08
 */
public class CompanyMgr {
    static int COMPANY_COUNTS = 3;
    static User[] adminArry = new User[1];
    BikeCompany[] companiesArr = new BikeCompany[COMPANY_COUNTS];
    BikeMgr bikeMgr = new BikeMgr();
    static Scanner scanner = new Scanner(System.in);
    CompanyDao cmpDao = new CompanyDao();

    public void cmpMgrMenu() {
        do {
            Views.adminView();
            System.out.print("请输入你的操作: ");
            int opt = scanner.nextInt();
            switch (opt) {
                case (1):
                    cmpDao.addCompany(companiesArr);
                    break;
                case (2):
                    cmpDao.deleteCompany(companiesArr);
                    break;
                case (3):
                    cmpDao.updateCompany(companiesArr);
                    break;
                case (4):
                    cmpDao.showCompany(companiesArr);
                    break;
                case (5):
                    cmpDao.showBike(companiesArr);
                    break;
                case (6):
                    cmpDao.rankList(companiesArr);
                    break;
                case (7):
                    // 进入公司管理系统
                    System.out.println("请输入公司名字: ");
                    String cmpName = scanner.next();
                    int cmpIndex = cmpDao.findIndex(cmpName);
                    if (cmpIndex == -1) {
                        System.out.println("公司名字有误!");
                        break;
                    }
                    bikeMgr.bikeMgrMenu(companiesArr[cmpIndex]);
                    break;
                case (8):
                    UserMgr.registerViewMenu(MainFuc.companyMgr);
                    break;
                case (9):
                    Utils.exitSystem();
                    break;
                default:
                    break;
            }
        } while (true);
    }

    // 初始化公司的对象
    public void initial() {
        // 初始化超级管理员信息
        adminArry[0] = new User("admin", "sun12345");
        // 公司数组的初始化
        SharedBike[] ofoBikeArr = new SharedBike[BikeCompany.OFO_BIKE_COUNTS];
        SharedBike[] helloBikeArr = new SharedBike[BikeCompany.HELLO_BIKE_COUNTS];
        SharedBike[] mobikeArr = new SharedBike[BikeCompany.MO_BIKE_COUNTS];
        User[] ofoStaffArr = new User[1];
        User[] helloStaffArr = new User[4];
        User[] moStaffArr = new User[3];
        User[] ofoUserArr = new User[2];
        User[] helloUserArr = new User[4];
        User[] moUserArr = new User[5];
        companiesArr[0] = new BikeCompany(1, "ofo单车", ofoBikeArr, ofoStaffArr, ofoUserArr, 100);
        companiesArr[1] = new BikeCompany(2, "哈喽单车", helloBikeArr, helloStaffArr, helloUserArr, 400);
        companiesArr[2] = new BikeCompany(3, "摩拜单车", mobikeArr, moStaffArr, moUserArr, 200);
        // 如果需要添加公司?
        //单车数组的初始化
        for (int j = 0; j < companiesArr.length; j++) {
            for (int i = 0; i < companiesArr[j].sharedBikes.length; i++) {
                companiesArr[j].sharedBikes[i] = new SharedBike(companiesArr[j].bikeCompanyName, j);
            }
        }
        // 公司员工和用户初始化
        for (int j = 0; j < companiesArr.length; j++) {
            for (int i = 0; i < companiesArr[j].staff.length; i++) {
                companiesArr[j].staff[i] = new User(companiesArr[j].bikeCompanyName + "员工" + i, "123", j);
            }
        }

        for (int j = 0; j < companiesArr.length; j++) {
            for (int i = 0; i < companiesArr[j].users.length; i++) {
                companiesArr[j].users[i] = new User(companiesArr[j].bikeCompanyName + "用户" + i, "1235", "上海", j);
            }
        }
    }
}


