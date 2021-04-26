package com.test.Bike;

import lombok.Getter;
import lombok.Setter;

/**
 * @className: Bike
 * @description:   
 * @author SunHang
 * @createTime 2021/3/25 20:11
 */
@Setter
@Getter
public class Bike {
    private int bikeId;
    private String bikeName;
    private int borrowNum;
    private String borrowTime;
    // 默认为可借
    private String status = "可借";
    private static int[] bikeArr = new int[Constant.CompanyConst.CMP_COUNTS];

    public Bike(String bikeName, int num) {
        this.bikeId = bikeArr[num];
        this.bikeName = bikeName + bikeId;
        bikeArr[num]++;
    }

    @Override
    public String toString() {
        return "      "+ bikeId + "      " + bikeName
                +"      "+status+"      "+borrowTime+"      "+borrowNum;
    }
}
