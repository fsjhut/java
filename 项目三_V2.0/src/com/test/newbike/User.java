package com.test.newbike;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

/**
 * @author SunHang
 * @className: User
 * @description:
 * @createTime 2021/3/21 11:43
 */
// CompanyMgr.COMPANY_COUNTS: 代表三个公司,后期可以关联公司的个数
@Getter
@Setter
@ToString
public class User {
    private int id; // 每个公司的员工ID唯一
    private String name; // 每个公司的员工名字唯一
    private String passWord;
    // 如果是普通用户则需要保存员工地址
    // 公司用户的地址全为默认值:北京
    private String area = "北京";
    // 保存每个公司的员工人数
    static int[] staffArr = new int[Constant.CompanyConst.CMP_COUNTS];
    // 保存每个公司的用户数量
    static int[] userArr = new int[Constant.CompanyConst.CMP_COUNTS];
    // 0: 管理员 1:公司用户 2: 普通用户 Competence 权限

    // 公司职工的初始化
    public User(String name, String passWord, int index) {
        this.id = staffArr[index];
        this.name = name;
        this.passWord = passWord;
        staffArr[index]++;
    }

    // 用户的初始化
    public User(String name, String passWord, String area, int index) {
        this.id = userArr[index];
        this.name = name;
        this.passWord = passWord;
        this.area = area;
        userArr[index]++;
    }

    public User(String name, String passWord) {
        this.name = name;
        this.passWord = passWord;
    }
}
