package bean;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import java.io.Serializable;
import java.util.Date;

/**
 * @className: Student
 * @description:   
 * @author SunHang
 * @createTime 2021/4/27 22:00
 */
@Getter
@Setter
@ToString
public class Student implements Serializable {
    private static final long serialVersionUID = -4897687701584156505L;
    private String id;
    private String pass;
    private String name;
    private String gender;
    private Integer age;
    private String phone;
    private Integer group;
    private Date birthday;

    public Student(String id, String pass, String name, String gender, Integer age, String phone,
                   Integer group, Date birthday) {
        this.id = id;
        this.pass = pass;
        this.name = name;
        this.gender = gender;
        this.age = age;
        this.phone = phone;
        this.group = group;
        this.birthday = birthday;
    }

    public Student() {
    }
}
