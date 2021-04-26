package com.test3;
/**
 * @className: NormalStudent
 * @description:   
 * @author SunHang
 * @createTime 2021/3/25 16:39
 */
public class NormalStudent extends StudentBase implements Study {

    public NormalStudent(String name, int Id) {
        super(name, Id);
    }

    @Override
    public void study() {
        System.out.println(getName()+"我爱学习-----");
    }
}
