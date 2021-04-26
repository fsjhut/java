import service.*;
import util.Utils;
import view.Views;

import java.util.Scanner;

/**
 * @author SunHang
 * @className: MainFunction
 * @description:
 * @createTime 2021/4/21 19:22
 */
public class MainFunction {
    private static Scanner scanner;
    private static GoodsTypeServer goodsTypeServer;
    private static GoodsServer goodsServer;
    private static MemberServer memberServer;
    private static BuyServer buyServer;
    private static OrderServer orderServer;
    private static RankServer rankServer;

    static {
        scanner = new Scanner(System.in);
        goodsTypeServer = new GoodsTypeServer();
        goodsServer = new GoodsServer();
        memberServer = new MemberServer();
        buyServer = new BuyServer();
        orderServer = new OrderServer();
        rankServer = new RankServer();
    }

    public static void main(String[] args) {
        MainFunction mainFunction = new MainFunction();
        mainFunction.loginMgr();
    }

    private void loginMgr() {
        int num = 3;
        System.out.println("欢迎您使用超市管理系统！");
        while (true) {
            System.out.print("请输入你的用户名：");
            String userName = scanner.next();
            System.out.print("请输入你的密码：");
            String password = scanner.next();
            int isFlag = Utils.loginVerify(userName, password);
            if (isFlag == 1) {
                System.out.println("管理员，欢迎您！");
                adminMgrFunc();
            } else if (isFlag == 2) {
                System.out.println("收银员，欢迎您！");
                staffMgrFunc();
            } else {
                System.out.println("你输入的用户名或密码错误！");
                if (num != 1) {
                    num--;
                    System.out.println("您还有" + num + "次机会！");
                } else {
                    System.out.println("机会使用完毕，程序结束!");
                    System.exit(-1);
                }
            }
        }
    }


    private void adminMgrFunc() {
        while (true) {
            Views.adminMgrView();
            System.out.print("请输入你的选择：");
            String choice = scanner.next();
            switch (choice) {
                case ("1"):
                    goodsTypeServer.goodsTypeMgr();
                    break;
                case ("2"):
                    goodsServer.goodsMgr();
                    break;
                case ("3"):
                    memberServer.memberMgr();
                    break;
                case ("4"):
                    return;
                case ("5"):
                    Utils.exitSystem();
                default:
                    System.out.println("输入错误！");
                    break;
            }
        }
    }

    private void staffMgrFunc() {
        while (true) {
            Views.staffMgrView();
            System.out.print("请输入你的选择：");
            String choice = scanner.next();
            switch (choice) {
                case ("1"):
                    buyServer.buyCartMgr();
                    break;
                case ("2"):
                    orderServer.orderMgr();
                    break;
                case ("3"):
                    rankServer.rankMgr();
                    break;
                case ("4"):
                    return;
                case ("5"):
                    Utils.exitSystem();
                default:
                    System.out.println("输入错误！");
                    break;
            }
        }
    }
}
