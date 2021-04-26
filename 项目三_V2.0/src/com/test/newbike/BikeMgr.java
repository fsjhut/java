package com.test.newbike;

import java.util.Objects;
import java.util.Scanner;

/**
 * @author SunHang
 * @className: BikeMgr
 * @description:
 * @createTime 2021/3/21 22:01
 */
public class BikeMgr {
    private static Scanner scanner = Constant.scanner;

    public void bikeMgrMenu(BikeCompany bikeCompany) {
        while (true) {
            Views.bikeMgrView();
            System.out.print("请选择编号: ");
            int oprNum = scanner.nextInt();
            switch (oprNum) {
                case 1:
                    Constant.sharedBikeDao.addBike(bikeCompany);
                    break;
                case 2:
                    Objects.requireNonNull(bikeCompany);
                    Constant.sharedBikeDao.showBike(bikeCompany);
                    break;
                case 3:
                    Objects.requireNonNull(bikeCompany);
                    Constant.sharedBikeDao.deleteBike(bikeCompany);
                    break;
                case 4:
                    Objects.requireNonNull(bikeCompany);
                    System.out.println("**********单车排行榜***********");
                    Constant.sharedBikeDao.rankList(bikeCompany);
                    break;
                case 5:
                    Constant.userMgr.userMgrMenu(bikeCompany);
                    break;
                case 6:
                    return;
                case 7:
                    Utils.exitSystem();
                    break;
                default:
                    System.out.println("输入操作符有误!");
                    break;
            }
        }
    }
}
