package java;

import java.text.SimpleDateFormat;
import java.util.Arrays;
import java.util.Date;
import java.util.Scanner;

/**
 * @author SunHang
 * @className: SharedBikeDao
 * @description:
 * @createTime 2021/3/20 16:09
 */
public class SharedBikeDao {

    private Scanner scanner = new Scanner(System.in);

    public void addBike(SharedBikeCompany sharedBikeCompany, int index) {
        System.out.println("请录入需要投放的单车数量");
        int num = scanner.nextInt();
        // 扩大数组的程序
        // 思路新建一个现在数组长度+num的新数组
        // 将原来的数组copy到新的数组中
        int oldArrayLength = sharedBikeCompany.sharedBikes.length;
        SharedBike[] newBikeCompany = new SharedBike[oldArrayLength + num];
        for (int i = 0; i < newBikeCompany.length; i++) {
            if (i < oldArrayLength) {
                newBikeCompany[i] = sharedBikeCompany.sharedBikes[i];
            } else {
                newBikeCompany[i] = new SharedBike(sharedBikeCompany.bikeCompanyName, index);
            }
        }
        sharedBikeCompany.sharedBikes = newBikeCompany;
        sharedBikeCompany.bikeSum = newBikeCompany.length;
        System.out.println("投放" + num + "辆" + sharedBikeCompany.bikeCompanyName + "单车成功!");
        System.out.println("-----------------------------------");
    }

    public void deleteBike(SharedBikeCompany sharedBikeCompany) {
        // 删除的元素后面的依次前移
        System.out.println("此" + sharedBikeCompany.bikeCompanyName + "单车有共享单车如下: ");
        sharedBikeCompany.bikeCompanyBikeInfo();
        System.out.print("请输入要删除单车的编号: ");
        int bikeCount = sharedBikeCompany.sharedBikes.length;
        int deleteBikeNo = scanner.nextInt();
        if (sharedBikeCompany.sharedBikes[deleteBikeNo - 1].statusToString.equals("借出")) {
            System.out.println("单车已借出,无法删除!");
            return;
        }
        if (deleteBikeNo > bikeCount) {
            System.out.println("指定的单车不存在!");
            return;
        }
        SharedBike[] newBikeCompany = new SharedBike[bikeCount - 1];
        String deleteName = sharedBikeCompany.sharedBikes[deleteBikeNo - 1].bikeName;
        for (int i = deleteBikeNo - 1; i < bikeCount - 1; i++) {
            sharedBikeCompany.sharedBikes[i] = sharedBikeCompany.sharedBikes[i + 1];
        }
        // 将最后一个元素变为null  将旧数组的bikecount-1赋值给新数组
        sharedBikeCompany.sharedBikes[bikeCount - 1] = null;
        for (int i = 0; i < bikeCount - 1; i++) {
            newBikeCompany[i] = sharedBikeCompany.sharedBikes[i];
        }
        // 将新数组赋值给旧数组
        sharedBikeCompany.sharedBikes = newBikeCompany;
        sharedBikeCompany.bikeSum--;
        System.out.println("删除" + sharedBikeCompany.bikeCompanyName + "单车公司的" + deleteName + "成功!");
        System.out.println("-----------------------------------");
    }

    public void borrowBike(SharedBikeCompany sharedBikeCompany) {
        System.out.println("此" + sharedBikeCompany.bikeCompanyName + "单车有共享单车如下: ");
        sharedBikeCompany.bikeCompanyBikeInfo();
        System.out.print("请录入要借出的单车编号: ");
        int bikeNo = scanner.nextInt();
        if (sharedBikeCompany.sharedBikes[bikeNo - 1].statusToString.equals("借出")) {
            System.out.println("单车已借出!");
            return;
        }
        if (bikeNo > sharedBikeCompany.sharedBikes.length) {
            System.out.println("指定的单车不存在!");
            return;
        }
        sharedBikeCompany.sharedBikes[bikeNo - 1].statusToString = "借出";
        sharedBikeCompany.borrowCount++;
        System.out.println("请输入要借出的单车日期: ");
        sharedBikeCompany.sharedBikes[bikeNo - 1].borrowTime = scanner.next();
        System.out.println("借出" + sharedBikeCompany.bikeCompanyName + "单车公司的<" + sharedBikeCompany.sharedBikes[bikeNo - 1].bikeName + ">成功!");
    }

    public void returnBike(SharedBikeCompany sharedBikeCompany) {
        System.out.print("请录入需要归还的单车编号: ");
        int bikeNo = scanner.nextInt();
        if (bikeNo > sharedBikeCompany.sharedBikes.length) {
            System.out.println("指定的单车不存在!");
            return;
        }
        if (sharedBikeCompany.sharedBikes[bikeNo - 1].statusToString.equals("可借")) {
            System.out.println("该单车未借出!");
            return;
        }
        String borrowTime = sharedBikeCompany.sharedBikes[bikeNo - 1].borrowTime;
        System.out.println("请输入归还的单车日期: ");
        String returnTime = scanner.next();
        sharedBikeCompany.sharedBikes[bikeNo - 1].borrowTime = null;
        sharedBikeCompany.sharedBikes[bikeNo - 1].statusToString = "可借";
        int time = calculateTime(borrowTime, returnTime);
        System.out.println("用车时间为<<" + time + ">>小时, 需要支付: " + 2 * time + "元");
        System.out.println("-----------------------------------");
    }

    public int calculateTime(String borrowTime, String returnTime) {
        SimpleDateFormat sd = new SimpleDateFormat("yyyy-MM-dd");
//        Date d1 = sd.parse(borrowTime);
        return 2;
    }

    public void rankList(SharedBikeCompany[] companiesArray) {
        int len = companiesArray.length;
        SharedBikeCompany[] companiesArrayCopy = Arrays.copyOf(companiesArray, companiesArray.length);
        for (int i = 0; i < len - 1; i++) {
            for (int j = 0; j < len - 1 - i; j++) {
                if (companiesArrayCopy[j].borrowCount > companiesArrayCopy[j + 1].borrowCount) {
                    SharedBikeCompany temp;
                    temp = companiesArrayCopy[j];
                    companiesArrayCopy[j] = companiesArrayCopy[j + 1];
                    companiesArrayCopy[j + 1] = temp;
                }
            }
        }
        System.out.println("*******************************");
        System.out.println("| 公司名称   | 公司单车借出次数 |");
        System.out.println("*******************************");
        for (int i = 0; i < companiesArrayCopy.length; i++) {
            String companiesName = companiesArrayCopy[i].bikeCompanyName;
            int borrowNo = companiesArrayCopy[i].borrowCount;
            System.out.println("| " + companiesName + "\t|       " + borrowNo + "       |");
            System.out.println("*******************************");
        }
    }

    public void showBike(SharedBikeCompany[] companiesArray) {
        for (int i = 0; i < companiesArray.length; i++) {
            companiesArray[i].bikeCompanyBikeInfo();
        }
        System.out.println("*******************************************");
    }

}
