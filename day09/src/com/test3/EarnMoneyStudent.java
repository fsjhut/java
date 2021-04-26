package com.test3;
/**
 * @className: EarnMoneyStudent
 * @description:   
 * @author SunHang
 * @createTime 2021/3/25 16:40
 */
public class EarnMoneyStudent extends StudentBase implements Study,EarnMoney {
    public EarnMoneyStudent(String name, int Id) {
        super(name, Id);
    }

    @Override
    public void earnMoney() {
        System.out.println(getName()+"我爱赚钱---");
    }

    @Override
    public void study() {
        System.out.println(getName()+"我爱学习------");
    }
}
