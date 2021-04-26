package java;
/**
 * @className: MainFuc
 * @description:   
 * @author SunHang
 * @createTime 2021/3/22 13:00
 */
// 程序入口
public class MainFuc {
    // 建立所有的类对象
    UserMgr userMgr  =  new UserMgr();
    static BikeMgr bikeMgr = new BikeMgr();
    static CompanyMgr companyMgr = new CompanyMgr();
    // 初始化 公司信息 和 单车信息 和 员工 用户 管理员
    public static void main(String[] args) {
        MainFuc mainFuc = new MainFuc();
        companyMgr.initial();
        // 调用register注册程序
        mainFuc.userMgr.registerViewMenu(companyMgr);
    }
}
