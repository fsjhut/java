package com.test.exec1;/**
 *@className: ProgectManager
 *@description 
 *@author SunHang
 *@createTime 2021/3/20 9:46
 */
public class ProjectManager {
    private int id;
    private String name;
    private char gender;
    private double salary;
    private String exp;
    private String bonus;

    public ProjectManager() {
    }

    public ProjectManager(int id, String name, char gender, double salary, String exp, String bonus) {
        this.id = id;
        this.name = name;
        this.gender = gender;
        this.salary = salary;
        this.exp = exp;
        this.bonus = bonus;
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

    public void setExp(String exp) {
        this.exp = exp;
    }

    public void setBonus(String bonus) {
        this.bonus = bonus;
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

    public String getExp() {
        return exp;
    }

    public String getBonus() {
        return bonus;
    }

    public void showInfo() {
        System.out.println("员工编号:" + this.id + "\t姓名:" + name + "\t性别:"
                + gender + "\t工资:" + salary + "\t项目经验: " + exp+"\t项目分红:"+bonus);
    }
}
