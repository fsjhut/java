package com.test.java;

import lombok.Getter;
import lombok.Setter;

/**
 * @className: User
 * @description:   
 * @author SunHang
 * @createTime 2021/4/4 21:11
 */
@Getter
@Setter
public class User {
    private int id;
    private int age;
    private String name;
    public User(int id, int age, String name) {

        this.id = id;
        this.age = age;
        this.name = name;
    }
    public User() {

    }

    @Override
    public String toString() {
        return "User{" +
                "id=" + id +
                ", age=" + age +
                ", name='" + name + '\'' +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        User user = (User) o;

        if (id != user.id) return false;
        if (age != user.age) return false;
        return name != null ? name.equals(user.name) : user.name == null;
    }

    @Override
    public int hashCode() {
        int result = id;
        result = 31 * result + age;
        result = 31 * result + (name != null ? name.hashCode() : 0);
        return result;
    }
}
