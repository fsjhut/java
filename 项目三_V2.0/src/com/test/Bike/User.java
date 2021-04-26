package com.test.Bike;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

/**
 * @className: User
 * @description:   
 * @author SunHang
 * @createTime 2021/3/25 20:11
 */
@Getter
@Setter
@ToString
public class User {
    private int id;
    private String name;
    private String pass;
    private String area = "北京";
    // 保存员工数量
    private  static int[] staffArr;
    // 保存用户数量
    private static int[] UserArr;
    // 初始化静态变量
    static{
        staffArr = new int[Constant.CompanyConst.CMP_COUNTS];
        UserArr = new int[Constant.CompanyConst.CMP_COUNTS];
    }
    //构造方法(员工的初始化)
    public User(String name, String passWord, int index) {
        this.id = staffArr[index];
        this.name = name;
        this.pass = passWord;
        staffArr[index]++;
    }
    // 用户的构造器
    public User(String name, String passWord, String area, int index) {
        this.id = UserArr[index];
        this.name = name;
        this.pass = passWord;
        this.area = area;
        UserArr[index]++;
    }
//    // 管理员的构造器
//    public User(String name, String passWord) {
//        this.name = name;
//        this.pass = passWord;
//    }

}
