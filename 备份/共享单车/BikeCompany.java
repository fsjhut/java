package com.test.exec;

import lombok.Getter;
import lombok.Setter;

/**
 * @author SunHang
 * @className: bikeCompany
 * @description:
 * @createTime 2021/3/20 16:04
 */
@Getter
@Setter
public class BikeCompany {
    private int bikeCompanyId;   // 单车公司ID
    public String bikeCompanyName; // 单车公司名字
    public SharedBike[] sharedBikes; // 拥有的单车数量
    public User[] staff; // 公司员工数组
    public User[] users; // 普通用户数组
    public int staffSum; // 拥有的员工数量
    public int userSum;  // 拥有的普通用户数量
    public int bikeSum;
    public int borrowCount;
    private static int cmpNum;
    static final int OFO_BIKE_COUNTS = 3;
    static final int HELLO_BIKE_COUNTS = 3;
    static final int MO_BIKE_COUNTS = 3;

    public BikeCompany(int bikeCompanyId, String bikeCompanyName, SharedBike[] sharedBikes, User[] staff,
                       User[] users, int borrowCount) {
        this.bikeCompanyId = bikeCompanyId;
        this.bikeCompanyName = bikeCompanyName;
        this.sharedBikes = sharedBikes;
        this.users = users;
        this.staff = staff;
        this.bikeSum = sharedBikes.length;
        this.staffSum = staff.length;
        this.userSum = users.length;
        this.borrowCount = borrowCount;
        cmpNum++;
    }
    public BikeCompany(String bikeCompanyName) {
        this.bikeCompanyId = cmpNum+1;
        this.bikeCompanyName = bikeCompanyName;
        cmpNum++;
    }

    public BikeCompany() {
        cmpNum++;
    }

    void bikeCompanyBikeInfo() {
        bikeCompanyView();
        System.out.println("| 单车序号 | 单车品牌 | 单车状态 | 借出时间 | ");
        for (int i = 0; i < bikeSum; i++) {
            System.out.println("*******************************************");
            sharedBikes[i].bikeInfo(i + 1);
        }
    }
    void bikeCompanyView() {
        System.out.println("******************************************************");
        System.out.println("| 公司序号 |  公司名称 | 公司单车数量 | 公司单车借出次数| ");
        System.out.println("******************************************************");
        companyView();
    }
    void companyView() {
        System.out.println("|    " + bikeCompanyId + "    |  " + bikeCompanyName + " |      "
                + bikeSum + "      |       " + borrowCount + "      |");
        System.out.println("******************************************************");
    }
}
