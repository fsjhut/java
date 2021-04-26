package com.test.exec;

import lombok.Getter;
import lombok.Setter;

/**
 * @className: Programmer
 * @description:   
 * @author SunHang
 * @createTime 2021/4/3 13:46
 */
@Setter
@Getter
public class Programmer extends Staff {
    private Double salary;

    public Programmer(String name, Integer id, Character gender, Double salary) {
        super(name, id, gender);
        this.salary = salary;
    }

    public Programmer() {
    }

    @Override
    public String toString() {
        return super.toString() +
                "salary=" + salary +
                '}';
    }
}
