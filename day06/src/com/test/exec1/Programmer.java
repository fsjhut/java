package com.test.exec1;

/**
 * @author SunHang
 * @className: Programmer
 * @description
 * @createTime 2021/3/20 9:45
 */
public class Programmer {
    private int id;
    private String name;
    private char gender;
    private double salary;
    private String hot;

    public Programmer() {

    }

    public Programmer(int id, String name, char gender,
                      double salary, String hot) {
        this.id = id;
        this.name = name;
        this.gender = gender;
        this.salary = salary;
        this.hot = hot;
    }

    public void setId(int id) {
        this.id = id;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setGender(char gender) {
        this.gender = gender;
    }

    public void setSalary(double salary) {
        this.salary = salary;
    }

    public void setHot(String hot) {
        this.hot = hot;
    }

    public int getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public char getGender() {
        return gender;
    }

    public double getSalary() {
        return salary;
    }

    public String getHot() {
        return hot;
    }

    public void showInfo() {
        System.out.println("员工编号:" + this.id + "\t姓名:" + name + "\t性别:" + gender + "\t工资:" + salary + "\t关注度: " + hot);
    }
}
