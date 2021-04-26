package com.test.exec3;
/**
 * @className: CarTest
 * @description:   
 * @author SunHang
 * @createTime 2021/3/24 16:24
 */
public class CarTest {
    public static void main(String[] args) {
        BaoMa baoMa = new BaoMa("红", "宝马", 4);
        BaoMa baoMa2 = new BaoMa();
        BenChi benChi = new BenChi("蓝","奔驰", 4);
        BenChi benChi2 = new BenChi();
        System.out.println(baoMa);
        System.out.println(baoMa2);
        System.out.println(benChi);
        System.out.println(benChi2);

    }
}
