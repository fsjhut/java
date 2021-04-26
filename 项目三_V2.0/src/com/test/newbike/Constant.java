package com.test.newbike;

import java.util.Scanner;

/**
 * @author SunHang
 * @className: Constant
 * @description:
 * @createTime 2021/3/25 20:16
 */
public class Constant {

    private Constant() {
    }

    public static final User[] adminArr = new User[1];
    public static  BikeCompany[] companiesArr =
            new BikeCompany[CompanyConst.CMP_COUNTS];
    public static final Scanner scanner = new Scanner(System.in);
    public static final UserDao userDao = new UserDao();
    public static final BikeMgr bikeMgr = new BikeMgr();
    public static final UserMgr userMgr = new UserMgr();
    public static final BikeDao sharedBikeDao = new BikeDao();
    public static final CompanyDao companyDao = new CompanyDao();

    // 定义常量内部类 保存用户内部常量
    public static class UserConst {
        // 默认初始公司员工数量为3
        public static final int OFO_STAFF_COUNTS = 1;
        public static final int HELLO_STAFF_COUNTS = 3;
        public static final int MO_STAFF_COUNTS = 3;
        public static final int OFO_USER_COUNTS = 3;
        public static final int HELLO_USER_COUNTS = 3;
        public static final int MO_USER_COUNTS = 3;
    }

    public static class BikeConst {
        // 默认单车数量为5
        public static final int OFO_BIKE_COUNTS = 3;
        public static final int HELLO_BIKE_COUNTS = 3;
        public static final int MO_BIKE_COUNTS = 3;
    }

    public static class CompanyConst {
        public static final int CMP_COUNTS = 3;
    }
}
