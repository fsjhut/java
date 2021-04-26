package com.test.exec5;
/**
 *@className: Manager
 *@description 
 *@author SunHang
 *@createTime 2021/3/20 9:49
 */

/**
 * 显示管理员信息的方法：返回管理员用户名和密码的信息；
 * 验证用户密码的方法：
 * 传入用户名和密码的参数，返回布尔类型，用户名和密码是否正确。
 */
public class Manager {
    String name;
    String password;

    public Manager(String name, String password) {
        this.name = name;
        this.password = password;
    }

    public String  managerInfo(){
        String str;
        str = "管理员的名字: "+ name+"\t密码是: "+password;
        return str;
    }

    public boolean proffMember(String name, String password){
        return name.equals(this.name)&&password.equals(this.password);
    }
}
