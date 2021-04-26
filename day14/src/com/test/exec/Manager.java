package com.test.exec;

import lombok.Getter;
import lombok.Setter;

/**
 * @className: Manager
 * @description:   
 * @author SunHang
 * @createTime 2021/4/3 13:52
 */
@Getter
@Setter
public class Manager extends Staff {
    private Double salary;
    private Double bonus;

    public Manager(String name, Integer id, Character gender, Double salary, Double bonus) {
        super(name, id, gender);
        this.salary = salary;
        this.bonus = bonus;
    }

    public Manager() {
    }

    @Override
    public String toString() {
        return super.toString() +
                "salary=" + salary +
                ", bonus=" + bonus +
                '}';
    }
}
