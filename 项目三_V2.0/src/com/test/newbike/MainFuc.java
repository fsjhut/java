package com.test.newbike;

/**
 * @author SunHang
 * @className: MainFuc
 * @description:
 * @createTime 2021/3/22 13:00
 */
// 程序入口
public class MainFuc {
    // 建立所有的类对象
    private static UserMgr userMgr = new UserMgr();
    static CompanyMgr companyMgr = new CompanyMgr();

    // 初始化 公司信息 和 单车信息 和 员工 用户 管理员
    public static void main(String[] args) {
        companyMgr.initial();
        // 调用register注册程序
        userMgr.registerViewMenu(companyMgr);
    }
}
