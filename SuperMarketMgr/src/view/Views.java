package view;

/**
 * @author SunHang
 * @className: Views
 * @description:
 * @createTime 2021/4/21 18:54
 */
public class Views {
    private Views() {

    }

    public static void adminMgrView() {
        System.out.println("************欢迎使用超市管理系统*************");
        System.out.println(" *             1. 商品类型管理            * ");
        System.out.println(" *             2. 商品管理                * ");
        System.out.println(" *             3. 会员管理                * ");
        System.out.println(" *             4. 注销登录                * ");
        System.out.println(" *             5. 退出系统                * ");
        System.out.println("*******************************************");
    }

    public static void staffMgrView() {
        System.out.println("************欢迎使用超市管理系统*************");
        System.out.println(" *             1. 购买管理                * ");
        System.out.println(" *             2. 订单查询                * ");
        System.out.println(" *             3. 排行统计                * ");
        System.out.println(" *             4. 注销登录                * ");
        System.out.println(" *             5. 退出系统                * ");
        System.out.println("*******************************************");
    }

    public static void goodsTypeMgrView() {
        // 超级用户系统界面
        System.out.println("**********欢迎使用商品类型管理系统************");
        System.out.println(" *             1. 添加商品类型             * ");
        System.out.println(" *             2. 修改商品类型             * ");
        System.out.println(" *             3. 查询商品类型             * ");
        System.out.println(" *             4. 删除商品类型             * ");
        System.out.println(" *             5. 返回上一级               * ");
        System.out.println(" *             6. 退出系统                 * ");
        System.out.println("********************************************");
    }

    public static void goodsMgrView() {
        System.out.println("************欢迎使用商品管理系统*************");
        System.out.println(" *             1. 添加商品                 * ");
        System.out.println(" *             2. 修改商品                 * ");
        System.out.println(" *             3. 查询商品                 * ");
        System.out.println(" *             4. 删除商品                 * ");
        System.out.println(" *             5. 返回上一级               * ");
        System.out.println(" *             6. 退出系统                 * ");
        System.out.println("********************************************");

    }

    public static void MemberMgrView() {
        System.out.println("************欢迎使用会员管理系统*************");
        System.out.println(" *             1. 添加会员                * ");
        System.out.println(" *             2. 修改会员                * ");
        System.out.println(" *             3. 查询会员                * ");
        System.out.println(" *             4. 删除会员                * ");
        System.out.println(" *             5. 会员充值                * ");
        System.out.println(" *             6. 返回上一级              * ");
        System.out.println(" *             7. 退出系统                * ");
        System.out.println("********************************************");
    }

    public static void buyCartMgrView() {
        System.out.println("*************欢迎使用购物车管理系统*************");
        System.out.println(" *             1. 添加商品                   * ");
        System.out.println(" *             2. 修改商品数量               * ");
        System.out.println(" *             3. 删除指定商品               * ");
        System.out.println(" *             4. 显示购物车商品             * ");
        System.out.println(" *             5. 游览过的商品               * ");
        System.out.println(" *             6. 返回上一级                 * ");
        System.out.println(" *             7. 确认购买                   * ");
        System.out.println("**********************************************");
    }
}
