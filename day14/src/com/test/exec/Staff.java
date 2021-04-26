package com.test.exec;

import lombok.Getter;
import lombok.Setter;

/**
 * @className: Staff
 * @description:   
 * @author SunHang
 * @createTime 2021/4/3 13:44
 */
@Getter
@Setter
public abstract class Staff {
    private String name;
    private Integer id;
    private Character gender;

    public Staff(String name, Integer id, Character gender) {
        this.name = name;
        this.id = id;
        this.gender = gender;
    }

    public Staff() {
    }

    @Override
    public String toString() {
        return "{" +
                "name='" + name + '\'' +
                ", id=" + id +
                ", gender=" + gender+", ";
    }
}
