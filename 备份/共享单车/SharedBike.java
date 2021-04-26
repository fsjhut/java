package com.test.exec;

/**
 * @author SunHang
 * @className: SharedBike
 * @description:
 * @createTime 2021/3/20 16:05
 */
public class SharedBike {
    private int bikeId;
    public String bikeName;
    //    private int status;
    public int borrowNum;
    String borrowTime; // 借出时间
    String statusToString = "可借";
    public static int[] currNum = new int[CompanyMgr.COMPANY_COUNTS];

    public SharedBike(String bikeName, int num) {
        this.bikeId = currNum[num];
        this.bikeName = bikeName + bikeId;
        currNum[num]++;
    }

    public void bikeInfo(int num) {
        System.out.printf("|    %-5d|\t%s |\t%s   |   %s   |", num, bikeName, statusToString, borrowTime);
        System.out.println();
    }
}
