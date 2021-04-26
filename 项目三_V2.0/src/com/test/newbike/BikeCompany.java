package com.test.newbike;

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
    private int companyId;   // 单车公司ID
    private String companyName; // 单车公司名字
    private SharedBike[] sharedBikes; // 拥有的单车数量
    private User[] staff; // 公司员工数组
    private User[] users; // 普通用户数组
    private BikeCompany[] companiesArr =
            new BikeCompany[Constant.CompanyConst.CMP_COUNTS];
    private int staffSum; // 拥有的员工数量
    private int userSum;  // 拥有的普通用户数量
    private int bikeSum;
    private int borrowCount;
    private static int cmpNum;

    public BikeCompany(int bikeCompanyId, String bikeCompanyName, SharedBike[] sharedBikes, User[] staff,
                       User[] users, int borrowCount) {
        this.companyId = bikeCompanyId;
        this.companyName = bikeCompanyName;
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
        this.companyId = cmpNum + 1;
        this.companyName = bikeCompanyName;
        cmpNum++;
    }

    @Override
    public String toString() {
        String str = "|    " + companyId + "    |  " + companyName + " |      "
                + bikeSum + "      |       " + borrowCount + "      |\n";
        String str2 = "******************************************************";
        return str + str2;
    }
}
