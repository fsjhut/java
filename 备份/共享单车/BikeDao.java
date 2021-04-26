package com.test.exec;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Arrays;
import java.util.Date;
import java.util.Scanner;

/**
 * @author SunHang
 * @className: bikeDao
 * @description:
 * @createTime 2021/3/20 16:09
 */
public class BikeDao {

    private static  Scanner scanner = new Scanner(System.in);

    void addBike(BikeCompany bikeCompany) {
        System.out.print("请录入需要投放的单车数量:");
        int num = scanner.nextInt();
        // 扩大数组的程序
        // 思路新建一个现在数组长度+num的新数组
        // 将原来的数组copy到新的数组中
        int oldArrayLength = bikeCompany.sharedBikes.length;
        SharedBike[] newBikeCompany = new SharedBike[oldArrayLength + num];
        for (int i = 0; i < newBikeCompany.length; i++) {
            if (i < oldArrayLength) {
                newBikeCompany[i] = bikeCompany.sharedBikes[i];
            } else {
                newBikeCompany[i] = new SharedBike(bikeCompany.bikeCompanyName, i);
            }
        }
        bikeCompany.sharedBikes = newBikeCompany;
        bikeCompany.bikeSum = newBikeCompany.length;
        System.out.println("投放" + num + "辆" + bikeCompany.bikeCompanyName + "单车成功!");
        System.out.println("-----------------------------------");
    }

    void deleteBike(BikeCompany bikeCompany) {
        // 删除的元素后面的依次前移
        System.out.println("此" + bikeCompany.bikeCompanyName + "单车有共享单车如下: ");
        bikeCompany.bikeCompanyBikeInfo();
        System.out.print("请输入要删除单车的编号: ");
        int bikeCount = bikeCompany.sharedBikes.length;
        int deleteBikeNo = scanner.nextInt();
        if (bikeCompany.sharedBikes[deleteBikeNo - 1].statusToString.equals("借出")) {
            System.out.println("单车已借出,无法删除!");
            return;
        }
        if (deleteBikeNo > bikeCount) {
            System.out.println("指定的单车不存在!");
            return;
        }
        SharedBike[] newBikeCompany = new SharedBike[bikeCount - 1];
        String deleteName = bikeCompany.sharedBikes[deleteBikeNo - 1].bikeName;
        for (int i = deleteBikeNo - 1; i < bikeCount - 1; i++) {
            bikeCompany.sharedBikes[i] = bikeCompany.sharedBikes[i + 1];
        }
        // 将最后一个元素变为null  将旧数组的bikecount-1赋值给新数组
        bikeCompany.sharedBikes[bikeCount - 1] = null;
        for (int i = 0; i < bikeCount - 1; i++) {
            newBikeCompany[i] = bikeCompany.sharedBikes[i];
        }
        // 将新数组赋值给旧数组
        bikeCompany.sharedBikes = newBikeCompany;
        bikeCompany.bikeSum--;
        System.out.println("删除" + bikeCompany.bikeCompanyName + "单车公司的" + deleteName + "成功!");
        System.out.println("-----------------------------------");
    }

    public static void borrowBike(BikeCompany bikeCompany) {
        System.out.println("此" + bikeCompany.bikeCompanyName + "单车有共享单车如下: ");
        bikeCompany.bikeCompanyBikeInfo();
        System.out.print("请录入要借出的单车编号: ");
        int bikeNo = scanner.nextInt();
        if (bikeCompany.sharedBikes[bikeNo - 1].statusToString.equals("借出")) {
            System.out.println("单车已借出!");
            return;
        }
        if (bikeNo > bikeCompany.sharedBikes.length) {
            System.out.println("指定的单车不存在!");
            return;
        }
        bikeCompany.sharedBikes[bikeNo - 1].statusToString = "借出";
        bikeCompany.borrowCount++;
        bikeCompany.sharedBikes[bikeNo - 1].borrowNum++;
        System.out.println("请输入要借出的单车时间(HH:mm): ");
        bikeCompany.sharedBikes[bikeNo - 1].borrowTime = scanner.next();
        System.out.println("借出" + bikeCompany.bikeCompanyName + "单车公司的<" + bikeCompany.sharedBikes[bikeNo - 1].bikeName + ">成功!");
    }

    public static void returnBike(BikeCompany bikeCompany) {
        System.out.print("请录入需要归还的单车编号: ");
        int bikeNo = scanner.nextInt();
        if (bikeNo > bikeCompany.sharedBikes.length) {
            System.out.println("指定的单车不存在!");
            return;
        }
        if (bikeCompany.sharedBikes[bikeNo - 1].statusToString.equals("可借")) {
            System.out.println("该单车未借出!");
            return;
        }
        String borrowTime = bikeCompany.sharedBikes[bikeNo - 1].borrowTime;
        System.out.println("请输入归还的单车日期: ");
        String returnTime = scanner.next();
        bikeCompany.sharedBikes[bikeNo - 1].borrowTime = null;
        bikeCompany.sharedBikes[bikeNo - 1].statusToString = "可借";
        int hour = calculateTime(borrowTime, returnTime);
        System.out.println("用车时间为<<" + hour + ">>小时, 需要支付: " + hour*2 + "元");
        System.out.println("-----------------------------------");
    }

    static int calculateTime(String borrowTime, String returnTime) {
//        SimpleDateFormat sd = new SimpleDateFormat("yyyy-MM-dd");
        SimpleDateFormat formatter=new SimpleDateFormat("yyyy-MM-dd HH:mm");
        int hour = 0;
        try {
            borrowTime = "2021-3-22 "+borrowTime;
            returnTime = "2021-3-22 "+returnTime;
            Date borrowTm = formatter.parse(borrowTime);
            Date returnTm = formatter.parse(returnTime);
            Long starTime=borrowTm.getTime();
            Long endTime=returnTm.getTime();
            Long num=endTime-starTime;//时间戳相差的毫秒数
            hour = (int)(num/1000/60/60)+1;
            return hour;
        } catch (ParseException e) {
            e.printStackTrace();
        }
        return hour;
    }

    public void showBike(BikeCompany bikeCompany) {
        bikeCompany.bikeCompanyBikeInfo();
    }


    public void rankList(BikeCompany bikeCompany) {
        int len = bikeCompany.sharedBikes.length;
        SharedBike[] sharedBikesCopy = Arrays.copyOf(bikeCompany.sharedBikes,len);
        for (int i = 0; i < len - 1; i++) {
            for (int j = 0; j < len - 1 - i; j++) {
                if (sharedBikesCopy[j].borrowNum > sharedBikesCopy[j + 1].borrowNum) {
                    SharedBike temp;
                    temp = sharedBikesCopy[j];
                    sharedBikesCopy[j] = sharedBikesCopy[j + 1];
                    sharedBikesCopy[j + 1] = temp;
                }
            }
        }
        System.out.println("*******************************");
        System.out.println("|  单车名称   |  单车借出次数   |");
        System.out.println("*******************************");
        for (int i = sharedBikesCopy.length-1; i>=0; i--) {
            String bikeName = sharedBikesCopy[i].bikeName;
            int borrowNo = sharedBikesCopy[i].borrowNum;
            System.out.println("| " + bikeName + "\t  |       " + borrowNo + "       |");
            System.out.println("*******************************");
        }
    }
}
