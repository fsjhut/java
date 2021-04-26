package com.test.newbike;

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

    private static Scanner scanner = Constant.scanner;

    void addBike(BikeCompany bikeCompany) {
        System.out.print("请录入需要投放的单车数量:");
        int num = scanner.nextInt();
        // 扩大数组的程序
        // 思路新建一个现在数组长度+num的新数组
        // 将原来的数组copy到新的数组中
        int oldArrayLength;
        if (bikeCompany.getSharedBikes() == null) {
            oldArrayLength = 0;
        } else {
            oldArrayLength = bikeCompany.getSharedBikes().length;
        }
        SharedBike[] newBikeCompany = new SharedBike[oldArrayLength + num];
        int companyIndex = Utils.findIndex(bikeCompany.getCompanyName());
        for (int i = 0; i < newBikeCompany.length; i++) {
            if (i < oldArrayLength) {
                newBikeCompany[i] = bikeCompany.getSharedBikes()[i];
            } else {
                newBikeCompany[i] = new SharedBike(bikeCompany.getCompanyName(), companyIndex);
            }
        }
        bikeCompany.setSharedBikes(newBikeCompany);
        bikeCompany.setBikeSum(newBikeCompany.length);
        System.out.println("投放" + num + "辆" + bikeCompany.getCompanyName() + "单车成功!");
        System.out.println("-----------------------------------");
    }

    void deleteBike(BikeCompany bikeCompany) {
        // 删除的元素后面的依次前移
        if (!Utils.isNull(bikeCompany.getSharedBikes())) {
            System.out.println("此" + bikeCompany.getCompanyName() + "单车有共享单车如下: ");
            Views.bikeView();
            int num = 1;
            for (SharedBike sharedBike : bikeCompany.getSharedBikes()) {
                System.out.print("   " + num);
                System.out.println(sharedBike);
                num++;
            }
        } else {
            System.out.println("该公司没有单车!");
            return;
        }
        System.out.print("请输入要删除单车的编号: ");
        int bikeCount = bikeCompany.getSharedBikes().length;
        int deleteBikeNo = scanner.nextInt();
        if (bikeCompany.getSharedBikes()[deleteBikeNo - 1].getStatus().equals("借出")) {
            System.out.println("单车已借出,无法删除!");
            return;
        }
        if (deleteBikeNo > bikeCount) {
            System.out.println("指定的单车不存在!");
            return;
        }
        SharedBike[] newBikeCompany = new SharedBike[bikeCount - 1];
        String deleteName = bikeCompany.getSharedBikes()[deleteBikeNo - 1].getBikeName();
        for (int i = deleteBikeNo - 1; i < bikeCount - 1; i++) {
            bikeCompany.getSharedBikes()[i] = bikeCompany.getSharedBikes()[i + 1];
        }
        // 将最后一个元素变为null  将旧数组的bikecount-1赋值给新数组
        bikeCompany.getSharedBikes()[bikeCount - 1] = null;
        for (int i = 0; i < bikeCount - 1; i++) {
            newBikeCompany[i] = bikeCompany.getSharedBikes()[i];
        }
        // 将新数组赋值给旧数组
        bikeCompany.setSharedBikes(newBikeCompany);
        bikeCompany.setBikeSum(bikeCompany.getBikeSum() - 1);
        System.out.println("删除" + bikeCompany.getCompanyName() + "单车公司的" + deleteName + "成功!");
        System.out.println("-----------------------------------");
    }

    public static void borrowBike(BikeCompany bikeCompany) {
        if(Utils.isNull(bikeCompany.getSharedBikes())){
            System.out.println("该公司没有单车,无法借用!");
            return;
        }
        System.out.println("此" + bikeCompany.getCompanyName() + "单车有共享单车如下: ");
        Views.bikeView();
        int num = 1;
        for (SharedBike sharedBike : bikeCompany.getSharedBikes()) {
            System.out.print("   " + num);
            System.out.println(sharedBike);
            num++;
        }
        System.out.print("请录入要借出的单车编号: ");
        int bikeNo = scanner.nextInt();
        if (bikeCompany.getSharedBikes()[bikeNo - 1].getStatus().equals("借出")) {
            System.out.println("单车已借出!");
            return;
        }
        if (bikeNo > bikeCompany.getSharedBikes().length) {
            System.out.println("指定的单车不存在!");
            return;
        }
        bikeCompany.getSharedBikes()[bikeNo - 1].setStatus("借出");
        bikeCompany.setBorrowCount(bikeCompany.getBorrowCount() + 1);
        bikeCompany.getSharedBikes()[bikeNo - 1].setBorrowNum
                (bikeCompany.getSharedBikes()[bikeNo - 1].getBorrowNum() + 1);
        System.out.println("请输入要借出的单车时间(HH:mm): ");
        bikeCompany.getSharedBikes()[bikeNo - 1].setBorrowTime(scanner.next());
        System.out.println("借出" + bikeCompany.getCompanyName() + "单车公司的<"
                + bikeCompany.getSharedBikes()[bikeNo - 1].getBikeName() + ">成功!");
    }

    public static void returnBike(BikeCompany bikeCompany) {
        if(Utils.isNull(bikeCompany.getSharedBikes())){
            System.out.println("该公司没有单车,无法归还!");
            return;
        }
        System.out.print("请录入需要归还的单车编号: ");
        int bikeNo = scanner.nextInt();
        if (bikeNo > bikeCompany.getSharedBikes().length) {
            System.out.println("指定的单车不存在!");
            return;
        }
        if (bikeCompany.getSharedBikes()[bikeNo - 1].getStatus().equals("可借")) {
            System.out.println("该单车未借出!");
            return;
        }
        String borrowTime = bikeCompany.getSharedBikes()[bikeNo - 1].getBorrowTime();
        System.out.println("请输入归还的单车日期: ");
        String returnTime = scanner.next();
        bikeCompany.getSharedBikes()[bikeNo - 1].setBorrowTime(null);
        bikeCompany.getSharedBikes()[bikeNo - 1].setStatus("可借");
        int hour = calculateTime(borrowTime, returnTime);
        System.out.println("用车时间为<<" + hour + ">>小时, 需要支付: " + hour * 2 + "元");
        System.out.println("-----------------------------------");
    }

    private static int calculateTime(String borrowTime, String returnTime) {
//        SimpleDateFormat sd = new SimpleDateFormat("yyyy-MM-dd");
        SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd HH:mm");
        int hour = 0;
        try {
            borrowTime = "2021-3-22 " + borrowTime;
            returnTime = "2021-3-22 " + returnTime;
            Date borrowTm = formatter.parse(borrowTime);
            Date returnTm = formatter.parse(returnTime);
            Long starTime = borrowTm.getTime();
            Long endTime = returnTm.getTime();
            Long num = endTime - starTime;//时间戳相差的毫秒数
            hour = (int) (num / 1000 / 60 / 60) + 1;
            return hour;
        } catch (ParseException e) {
            e.printStackTrace();
        }
        return hour;
    }

    public void showBike(BikeCompany bikeCompany) {
        Views.companyView();
        System.out.println(bikeCompany);
        if (!Utils.isNull(bikeCompany.getSharedBikes())) {
            Views.bikeView();
            for (int i = 0; i < bikeCompany.getSharedBikes().length; i++) {
                System.out.print("   " + (i + 1));
                System.out.println(bikeCompany.getSharedBikes()[i]);
            }
        }

    }

    public void rankList(BikeCompany bikeCompany) {
        if(Utils.isNull(bikeCompany.getSharedBikes())){
            System.out.println("该公司没有单车!");
            return;
        }
        int len = bikeCompany.getSharedBikes().length;
        SharedBike[] sharedBikesCopy = Arrays.copyOf(bikeCompany.getSharedBikes(), len);
        for (int i = 0; i < len - 1; i++) {
            for (int j = 0; j < len - 1 - i; j++) {
                if (sharedBikesCopy[j].getBorrowNum() > sharedBikesCopy[j + 1].getBorrowNum()) {
                    SharedBike temp;
                    temp = sharedBikesCopy[j];
                    sharedBikesCopy[j] = sharedBikesCopy[j + 1];
                    sharedBikesCopy[j + 1] = temp;
                }
            }
        }
        Views.bikeRankView();
        for (int i = sharedBikesCopy.length - 1; i >= 0; i--) {
            String bikeName = sharedBikesCopy[i].getBikeName();
            int borrowNo = sharedBikesCopy[i].getBorrowNum();
            System.out.println("| " + bikeName + "\t  |       " + borrowNo + "       |");
            System.out.println("*******************************");
        }
    }
}

