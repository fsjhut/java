package java;

/**
 * @author SunHang
 * @className: Views
 * @description:
 * @createTime 2021/3/21 19:35
 */
public class Views {

    public static void registerView() {
        System.out.println("*****欢迎进入单车管理系统*********");
        System.out.println("             1.注册              ");
        System.out.println("             2.登录              ");
        System.out.println("             3.退出系统          ");
        System.out.println("*********************************");
    }

    public static void userMgrView() {
        System.out.println("*******欢迎进入用户管理系统************");
        System.out.println("             1.增加用户               ");
        System.out.println("             2.显示所有用户           ");
        System.out.println("             3.删除指定用户           ");
        System.out.println("             4.编辑指定用户           ");
        System.out.println("             5.注销账号               ");
        System.out.println("             6.返回上一级             ");
        System.out.println("             7.退出系统               ");
        System.out.println("**************************************");
    }

    public static void bikeMgrView() {
        System.out.println("*********欢迎使用共享单车管理系统************");
        System.out.println(" *             1. 投放单车               * ");
        System.out.println(" *             2. 查看单车               * ");
        System.out.println(" *             3. 删除单车               * ");
        System.out.println(" *             4. 排 行 榜               * ");
        System.out.println(" *             5. 人员管理               * ");
        System.out.println(" *             6. 注销账号               * ");
        System.out.println(" *             7. 退出系统               * ");
        System.out.println("*******************************************");
    }

    public static void adminView() {
        // 超级用户系统界面
        System.out.println("***********欢迎使用超级管理系统!************");
        System.out.println(" *             1. 增加公司               * ");
        System.out.println(" *             2. 删除公司               * ");
        System.out.println(" *             3. 修改公司               * ");
        System.out.println(" *             4. 查看公司               * ");
        System.out.println(" *             5. 查看单车               * ");
        System.out.println(" *             6. 删除单车               * ");
        System.out.println(" *             7. 公司排行榜             * ");
        System.out.println(" *             8. 进入公司系统            * ");
        System.out.println(" *             9. 注销账号                * ");
        System.out.println(" *            10. 退出系统                * ");
        System.out.println("*******************************************");
    }

    public static void userView() {
        System.out.println("*****欢迎进入单车借用系统*********");
        System.out.println("             1.借出              ");
        System.out.println("             2.归还              ");
        System.out.println("             3.退出系统          ");
        System.out.println("*********************************");
    }

    public static void companyView() {
        System.out.println("****************************");
        System.out.println("*      1:  ofo 单车        *");
        System.out.println("*      2:  hello单车       *");
        System.out.println("*      3:  摩拜 单车       *");
        System.out.println("****************************");
        System.out.println("请输入需要操作的单车品牌编号: ");
    }
}
