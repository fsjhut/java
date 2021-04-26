package com.test.exec1;

import sun.security.ec.point.ProjectivePoint;

/**
 *@className: Salary
 *@description 
 *@author SunHang
 *@createTime 2021/3/20 9:47
 */
public class Salary {

    public void pay(Programmer programmer){
        System.out.println("应付给程序员的工资是:" + programmer.getSalary());
    }
    public void pay(ProjectManager projectManager){
        System.out.println("应付给项目经理的工资是:" + projectManager.getSalary());
    }
}
