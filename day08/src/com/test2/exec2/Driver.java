package com.test2.exec2;
/**
 * @className: Driver
 * @description:   
 * @author SunHang
 * @createTime 2021/3/24 17:13
 */
public class Driver {
    public static void main(String[] args) {
        Car baoMa = new BaoMa("红色", "宝马", 4);
        Car benChi = new BenChi("蓝色", "奔驰", 4);
        baoMa.run(baoMa);
        System.out.println("------------------------");
        benChi.run(benChi);
    }
}
