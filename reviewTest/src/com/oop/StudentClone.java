package com.oop;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

/**
 * @className: OOP
 * @description:   
 * @author SunHang
 * @createTime 2021/4/24 9:33
 */
@ToString
@Getter
@Setter
public class StudentClone implements Cloneable {
    private Integer id;
    private String name;
    private String[] hobby;
    @Override
    public StudentClone clone(){
        StudentClone oop = null;
        try {
            oop = (StudentClone)super.clone();
            oop.hobby = oop.hobby.clone();
        } catch (CloneNotSupportedException e) {
            e.printStackTrace();
        }
        return oop;
    }

    public StudentClone(Integer id, String name, String[] hobby) {
        this.id = id;
        this.name = name;
        this.hobby = hobby;
    }
}
