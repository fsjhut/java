package java;

import java.util.Scanner;

/**
 * @author SunHang
 * @className: SharedBikeMgr
 * @description:
 * @createTime 2021/3/20 16:08
 */
public class SharedBikeMgr {
    static private Scanner scanner = new Scanner(System.in);
    static final int COMPANY_COUNTS = 3;


    public static void main(String[] args) {
        SharedBikeMgr sharedBikeMgr = new SharedBikeMgr();
        // 定义一个数组保存公司信息
        SharedBikeCompany[] companiesArray = new SharedBikeCompany[COMPANY_COUNTS];
        sharedBikeMgr.initial(companiesArray);
        SharedBikeDao sharedBikeDao = new SharedBikeDao();
        sharedBikeMgr.start(companiesArray, sharedBikeDao);
    }

    private void start(SharedBikeCompany[] companiesArray, SharedBikeDao sharedBikeDao) {
        startMenu();
        System.out.print("请选择编号: ");
        int oprNum = scanner.nextInt();
        switch (oprNum) {
            case 1:
                System.out.println("-------------->1.投放单车");
                int temp = companyView();
                sharedBikeDao.addBike(companiesArray[temp],temp);
                break;
            case 2:
                System.out.println("-------------->2.查看单车");
                sharedBikeDao.showBike(companiesArray);
                break;
            case 3:
                System.out.println("-------------->3.删除单车");
                sharedBikeDao.deleteBike(companiesArray[companyView()]);
                break;
            case 4:
                System.out.println("-------------->4.借出单车");
                sharedBikeDao.borrowBike(companiesArray[companyView()]);
                break;
            case 5:
                System.out.println("-------------->5.归还单车");
                sharedBikeDao.returnBike(companiesArray[companyView()]);
                break;
            case 6:
                System.out.println("-------------->6.排行榜");
                sharedBikeDao.rankList(companiesArray);
                break;
            case 7:
                System.out.println("请确认是否退出系统(y/n)?");
                String exitInfo = scanner.next();
                if ("y".equals(exitInfo)) {
                    System.out.println("欢迎您下次继续使用!");
                    return;
                }
                break;
            default:
                System.out.println("输入操作符有误!");
                break;
        }
        System.out.print("输入0返回: ");
        String returnInfo = scanner.next();
        if ("0".equals(returnInfo)) {
            returnMain(companiesArray, sharedBikeDao);
        } else {
            System.out.println("你输入的信息有误!");
        }
    }

    private void returnMain(SharedBikeCompany[] companiesArray, SharedBikeDao sharedBikeDao) {
        start(companiesArray, sharedBikeDao);
    }

    /**
     * @Title: initial
     * @Description: 初始化公司数组
     * @param: [companiesArray]
     * @return: void
     * @date: 2021/3/20 19:19
     * @version: 1.0
     */
    private void initial(SharedBikeCompany[] companiesArray) {
        SharedBike[] ofoArr = new SharedBike[SharedBikeCompany.OFO_BIKE_COUNTS];
        SharedBike[] helloArr = new SharedBike[SharedBikeCompany.HELLO_BIKE_COUNTS];
        SharedBike[] mobikeArr = new SharedBike[SharedBikeCompany.MO_BIKE_COUNTS];
        // 公司数组的初始化
        companiesArray[0] = new SharedBikeCompany(1, "ofo单车", ofoArr, ofoArr.length, 100);
        companiesArray[1] = new SharedBikeCompany(2, "哈喽单车", helloArr, helloArr.length, 400);
        companiesArray[2] = new SharedBikeCompany(3, "摩拜单车", mobikeArr, mobikeArr.length, 200);
        // 如果需要添加公司?

        //单车数组的初始化
        for(int j = 0; j<companiesArray.length;j++){
            for (int i = 0; i<companiesArray[j].sharedBikes.length;i++) {
                companiesArray[j].sharedBikes[i] = new SharedBike(companiesArray[j].bikeCompanyName, j);
            }
        }
    }

    private void startMenu() {
        System.out.println("欢迎使用迷你共享单车管理系统");
        System.out.println("*******************************************");
        System.out.println(" *             1. 投放单车               * ");
        System.out.println(" *             2. 查看单车               * ");
        System.out.println(" *             3. 删除单车               * ");
        System.out.println(" *             4. 借出单车               * ");
        System.out.println(" *             5. 归还单车               * ");
        System.out.println(" *             6. 排 行 榜               * ");
        System.out.println(" *             7. 退出系统               * ");
        System.out.println("*******************************************");
    }

    private int companyView() {
        // 可以添加异常弹出的功能
        System.out.println("****************************");
        System.out.println("*      1:  ofo 单车        *");
        System.out.println("*      2:  hello单车       *");
        System.out.println("*      3:  摩拜 单车       *");
        System.out.println("****************************");
        System.out.println("请输入需要操作的单车品牌编号: ");
        int temp = scanner.nextInt();
        return temp - 1;
    }
}
