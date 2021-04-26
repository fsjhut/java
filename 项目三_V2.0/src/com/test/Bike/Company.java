package com.test.Bike;

import lombok.Getter;
import lombok.Setter;

import java.util.Arrays;

/**
 * @className: Company
 * @description:   
 * @author SunHang
 * @createTime 2021/3/25 20:11
 */
@Setter
@Getter
public class Company {
    private int companyId;
    private String companyName;
    private Bike[] bikes;
    private User[] staff;
    private User[] users;
    private int bikeNum;
    private int staffNum;
    private int UserNum;
    private int borrowNum;
    private static int companyNum;

    public Company(int companyId, String companyName, Bike[] bikes,
                   User[] staff, User[] users,int borrowNum) {
        this.companyId = companyId;
        this.companyName = companyName;
        this.bikes = bikes;
        this.staff = staff;
        this.users = users;
        this.bikeNum = bikes.length;
        this.staffNum = staff.length;
        this.UserNum = users.length;
        this.borrowNum = borrowNum;
        companyNum++;
    }
    public Company(String bikeCompanyName) {
        this.companyId = companyNum+1;
        this.companyName = bikeCompanyName;
        companyNum++;
    }

    @Override
    public String toString() {
        String str = "|    " + companyId + "    |  " + companyName + " |      "
                + bikeNum + "      |       " + borrowNum + "      |\n";
        String str2 = "******************************************************";
        return str+str2;
    }
}
