package com.test.java2;

import lombok.Getter;
import lombok.Setter;

/**
 * @className: User
 * @description:   
 * @author SunHang
 * @createTime 2021/4/4 19:03
 */
@Getter
@Setter
public class User implements Comparable{
    private String name;
    private int age;
    public User(){

    }
    public User(String name, int age) {
        this.name = name;
        this.age = age;
    }

    @Override
    public String toString() {
        return "User{" +
                "name='" + name + '\'' +
                ", age=" + age +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        User user = (User) o;

        if (age != user.age) return false;
        return name != null ? name.equals(user.name) : user.name == null;
    }

    @Override
    public int hashCode() {
        int result = name != null ? name.hashCode() : 0;
        result = 31 * result + age;
        return result;
    }

    /**
     * @Title: compareTo
     * @Description: 按照姓名从大到小排列,年龄从小到大排列
     * @param: [o]
     * @return: int
     * @date: 2021/4/4 19:06
     * @version: 1.0
     */
    @Override
    public int compareTo(Object o) {
        if(o instanceof User){
            User user = (User) o ;
            int compare = -this.name.compareTo(user.name);
            if(compare!=0){
                return compare;
            }else{
                return Integer.compare(this.age,user.age);
            }
        }else{
            throw new RuntimeException("输入类型不匹配");
        }
    }
}
