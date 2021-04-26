package com.test.newbike;

import lombok.Getter;
import lombok.Setter;

/**
 * @author SunHang
 * @className: SharedBike
 * @description:
 * @createTime 2021/3/20 16:05
 */
@Getter
@Setter
public class SharedBike {
    private int bikeId;
    private String bikeName;
    private int borrowNum;
    private String borrowTime; // 借出时间
    private String status = "可借";
    public static int[] bikeArr = new int[Constant.CompanyConst.CMP_COUNTS];

    public SharedBike(String bikeName, int num) {
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
