package java;

import java.util.Scanner;

/**
 * @author SunHang
 * @className: BikeMgr
 * @description:
 * @createTime 2021/3/21 22:01
 */
public class BikeMgr {
    static private Scanner scanner = new Scanner(System.in);
    static BikeDao sharedBikeDao = new BikeDao();
    UserMgr userMgr = new UserMgr();


    public void bikeMgrMenu(BikeCompany bikeCompany) {
//        boolean isFlag = true;
        while (true) {
            Views.bikeMgrView();
            System.out.print("请选择编号: ");
            int oprNum = scanner.nextInt();
            switch (oprNum) {
                case 1:
                    System.out.println("-------------->1.投放单车");
                    sharedBikeDao.addBike(bikeCompany);
                    break;
                case 2:
                    System.out.println("-------------->2.查看单车");
                    sharedBikeDao.showBike(bikeCompany);
                    break;
                case 3:
                    System.out.println("-------------->3.删除单车");
                    sharedBikeDao.deleteBike(bikeCompany);
                    break;
                case 4:
                    System.out.println("单车排行榜:");
                    break;
                case 5:
                    // 转到管理系统
                    userMgr.userMgrMenu(bikeCompany);
                    break;
                case 6:
                    // 返回最初注册或登录界面
                    UserMgr.registerViewMenu(MainFuc.companyMgr);
                    break;
                case 7:
                    // 若本界面处于循环
                    Utils.exitSystem();
                    break;
                default:
                    System.out.println("输入操作符有误!");
                    break;
            }
        }
    }
}
