package com.test.exec1;/**
 *@className: FunctionTest
 *@description 
 *@author SunHang
 *@createTime 2021/3/20 9:47
 */
public class FunctionTest {
    public static void main(String[] args) {
        Programmer programmer1 = new Programmer();
        Programmer programmer2 = new Programmer(2,"sun",'男',1111,"0");
        Salary salary = new Salary();
        ProjectManager projectManager1 = new ProjectManager();
        ProjectManager projectManager2 = new ProjectManager(2,"hang",'男',3333,"四年","1000股票");
//        programmer1.showInfo();
        programmer2.showInfo();
//        projectManager1.showInfo();
        projectManager2.showInfo();
        salary.pay(projectManager2);
        salary.pay(programmer2);

    }

}
