package com.test.java;
/**
 * @className: SharedBike
 * @description:   
 * @author SunHang
 * @createTime 2021/3/20 16:05
 */
public class SharedBike {
    private int bikeId;
    public String bikeName;
//    private int status;
    String borrowTime; // 借出时间
    String statusToString = "可借";
    // 单车所属区域
//    public String aera;
//    public  static int ofoCurrNum = 1; // ofo当前的单车数量
//    public  static int helloCurrNum = 1; // hello当前的单车数量
//    public  static int mobiCurrNum = 1; // mobi当前的单车数量
    public static int[] currNum = new int[CompanyMgr.COMPANY_COUNTS];

    public SharedBike(String bikeName, int num) {
        this.bikeId = currNum[num];
        this.bikeName = bikeName + bikeId;
        currNum[num]++;
    }

    public void bikeInfo(int num){
        System.out.printf("|    %-5d|\t%s |\t%s   |   %s   |", num,bikeName,statusToString,borrowTime);
        System.out.println();
    }
}
