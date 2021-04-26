package com.test2.exec2;

/**
 * @className: BaoMa
 * @description:   
 * @author SunHang
 * @createTime 2021/3/24 16:23
 */
public class BaoMa extends Car {
    public BaoMa(String color, String name, int wheelNum) {
        super(color, name, wheelNum);
    }

    public BaoMa() {
    }

    @Override
    public void run(Car car) {
        super.run(car);
        System.out.println(getName()+"像飞一样跑----");
    }
}
