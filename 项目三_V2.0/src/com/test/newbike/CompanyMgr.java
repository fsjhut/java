package com.test.newbike;

import lombok.Getter;
import lombok.Setter;

import java.util.Objects;

/**
 * @author SunHang
 * @className: SharedBikeMgr
 * @description:
 * @createTime 2021/3/20 16:08
 */
@Setter
@Getter
public class CompanyMgr {
    private User[] adminArr = Constant.adminArr;
    private BikeCompany[] companiesArr = Constant.companiesArr;
    private BikeMgr bikeMgr = Constant.bikeMgr;
    private CompanyDao cmpDao = Constant.companyDao;
    public void cmpMgrMenu() {
        do {
            Views.adminView();
            System.out.print("请输入你的操作: ");
            int opt = Constant.scanner.nextInt();
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
                    Objects.requireNonNull(companiesArr);
                    cmpDao.showBike(companiesArr);
                    break;
                case (6):
                    cmpDao.rankList(companiesArr);
                    break;
                case (7):
                    // 进入公司管理系统
                    System.out.print("请输入公司名字: ");
                    String cmpName = Constant.scanner.next();
                    int cmpIndex = Utils.findIndex(cmpName);
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
        adminArr[0] = new User("admin", "sun12345");
        // 公司数组的初始化
        SharedBike[] ofoBikeArr = new SharedBike[Constant.BikeConst.OFO_BIKE_COUNTS];
        SharedBike[] helloBikeArr = new SharedBike[Constant.BikeConst.HELLO_BIKE_COUNTS];
        SharedBike[] mobikeArr = new SharedBike[Constant.BikeConst.MO_BIKE_COUNTS];
        User[] ofoStaffArr = new User[Constant.UserConst.OFO_STAFF_COUNTS];
        User[] helloStaffArr = new User[Constant.UserConst.HELLO_STAFF_COUNTS];
        User[] moStaffArr = new User[Constant.UserConst.MO_STAFF_COUNTS];
        User[] ofoUserArr = new User[Constant.UserConst.OFO_USER_COUNTS];
        User[] helloUserArr = new User[Constant.UserConst.HELLO_USER_COUNTS];
        User[] moUserArr = new User[Constant.UserConst.MO_USER_COUNTS];
        companiesArr[0] = new BikeCompany(1, "ofo单车", ofoBikeArr, ofoStaffArr, ofoUserArr, 100);
        companiesArr[1] = new BikeCompany(2, "哈喽单车", helloBikeArr, helloStaffArr, helloUserArr, 400);
        companiesArr[2] = new BikeCompany(3, "摩拜单车", mobikeArr, moStaffArr, moUserArr, 200);
        // 如果需要添加公司?
        //单车数组的初始化
        for (int j = 0; j < companiesArr.length; j++) {
            for (int i = 0; i < companiesArr[j].getSharedBikes().length; i++) {
                companiesArr[j].getSharedBikes()[i] = new SharedBike(companiesArr[j].getCompanyName(), j);
            }
        }
        // 公司员工和用户初始化
        for (int j = 0; j < companiesArr.length; j++) {
            for (int i = 0; i < companiesArr[j].getStaff().length; i++) {
                companiesArr[j].getStaff()[i] = new User(companiesArr[j].getCompanyName() + "员工" + i, "123", j);
            }
        }

        for (int j = 0; j < companiesArr.length; j++) {
            for (int i = 0; i < companiesArr[j].getUsers().length; i++) {
                companiesArr[j].getUsers()[i] = new User(companiesArr[j].getCompanyName() + "用户" + i, "1235", "上海", j);
            }
        }
    }
}


