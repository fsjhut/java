package com.test.Bike;
/**
 * @className: Constant
 * @description:   
 * @author SunHang
 * @createTime 2021/3/25 20:16
 */
public class Constant {
    private Constant(){
    }

    // 定义常量内部类 保存用户内部常量
    public static class UserConst{
        // 默认初始公司员工数量为3
       public static final int USER_COUNTS = 3;
    }

    public static class BikeConst{
        // 默认单车数量为5
        public static final int OFO_BIKE_COUNTS = 3;
        public static final int HELLO_BIKE_COUNTS = 3;
        public static final int MO_BIKE_COUNTS = 3;
    }
    public static class CompanyConst{
        public static final int CMP_COUNTS = 3;
    }
}
