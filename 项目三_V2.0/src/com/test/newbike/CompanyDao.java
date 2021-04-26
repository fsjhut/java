package com.test.newbike;

import java.util.Arrays;
import java.util.Scanner;

/**
 * @author SunHang
 * @className: companyDao
 * @description:
 * @createTime 2021/3/22 9:35
 */
public class CompanyDao {
    public static Scanner scanner = new Scanner(System.in);

    public void addCompany(BikeCompany[] companiesArray) {
        // 扩大数组的程序
        // 思路新建一个现在数组长度+num的新数组
        // 将原来的数组copy到新的数组中
        String cmpName;
        boolean isExist;
        do {
            isExist = false;
            System.out.print("请输入需要新建的公司名字: ");
            cmpName = scanner.next();
            for (int i = 0; i < companiesArray.length; i++) {
                if (cmpName.equals(companiesArray[i].getCompanyName())) {
                    System.out.println("该公司已存在,请重新输入!");
                    isExist = true;
                }
            }
        } while (isExist);
        BikeCompany company = new BikeCompany(cmpName);
        int companiesLen = companiesArray.length;
        // 记录新加入的单车公司的单车数量
        int[] newBikeArr = new int[companiesLen + 1];
        int[] newStaffArr = new int[companiesLen + 1];
        int[] newUserArr = new int[companiesLen + 1];
        BikeCompany[] newCmpArray = new BikeCompany[companiesLen + 1];
        for (int i = 0; i < companiesLen; i++) {
            newCmpArray[i] = companiesArray[i];
            newBikeArr[i] = SharedBike.bikeArr[i];
            newStaffArr[i] = User.staffArr[i];
            newUserArr[i] = User.userArr[i];
        }
        newCmpArray[companiesLen] = company;
        newBikeArr[companiesLen] = 0;
        newStaffArr[companiesLen] = 0;
        newUserArr[companiesLen] = 0;
        SharedBike.bikeArr = newBikeArr;
        User.staffArr = newStaffArr;
        User.userArr = newUserArr;
        // 将新的赋值给旧的
        MainFuc.companyMgr.setCompaniesArr(newCmpArray);
        System.out.println("增加公司成功!");
    }

    public void deleteCompany(BikeCompany[] companiesArray) {
        showCompany(companiesArray);
        System.out.print("请输入要删除公司的名字: ");
        int cmpCount = companiesArray.length;
        String deleteCmpName = scanner.next();
        int deleteCmpNo = Utils.findIndex(deleteCmpName);
        if (deleteCmpNo == -1) {
            System.out.println("要删除的公司不存在!");
            return;
        }
        BikeCompany[] newCmpArr = new BikeCompany[cmpCount - 1];
        int[] newBikeArr = new int[cmpCount - 1];
        int[] newStaffArr = new int[cmpCount - 1];
        int[] newUserArr = new int[cmpCount - 1];
        for (int i = deleteCmpNo; i < cmpCount - 1; i++) {
            companiesArray[i] = companiesArray[i + 1];
            SharedBike.bikeArr[i] = SharedBike.bikeArr[i + 1];
            User.userArr[i] = User.userArr[i + 1];
            User.staffArr[i] = User.userArr[i + 1];
        }
        for (int i = 0; i < cmpCount - 1; i++) {
            newCmpArr[i] = companiesArray[i];
            newBikeArr[i] = SharedBike.bikeArr[i];
            newStaffArr[i] = User.staffArr[i];
            newUserArr[i] = User.userArr[i];
        }
        MainFuc.companyMgr.setCompaniesArr(newCmpArr);
        SharedBike.bikeArr = newBikeArr;
        User.staffArr = newStaffArr;
        User.userArr = newUserArr;
    }

    public void updateCompany(BikeCompany[] companiesArray) {
        showCompany(companiesArray);
        System.out.print("请输入要修改的公司名字: ");
        String editCmpName = scanner.next();
        int cmpIndex = Utils.findIndex(editCmpName);
        if (cmpIndex == -1) {
            System.out.println("无法找到该公司!");
            return;
        }
        String newCmpName;
        do {
            System.out.print("请输入修改后的公司名字:");
            newCmpName = scanner.next();
            for (int i = 0; i < companiesArray.length; i++) {
                if (newCmpName.equals(companiesArray[i].getCompanyName())) {
                    System.out.println("该公司已存在,请重新输入!");
                }
            }
            break;
        } while (true);
        companiesArray[cmpIndex].setCompanyName(newCmpName);
        System.out.println("修改成功!");
    }


    public void showCompany(BikeCompany[] companiesArray) {
        Views.companyView();
        for (int i = 0; i < companiesArray.length; i++) {
            System.out.println(companiesArray[i]);
        }
    }

    public void rankList(BikeCompany[] companiesArray) {
        int len = companiesArray.length;
        BikeCompany[] companiesArrayCopy = Arrays.copyOf(companiesArray, companiesArray.length);
        for (int i = 0; i < len - 1; i++) {
            for (int j = 0; j < len - 1 - i; j++) {
                if (companiesArrayCopy[j].getBorrowCount() > companiesArrayCopy[j + 1].getBorrowCount()) {
                    BikeCompany temp;
                    temp = companiesArrayCopy[j];
                    companiesArrayCopy[j] = companiesArrayCopy[j + 1];
                    companiesArrayCopy[j + 1] = temp;
                }
            }
        }
        System.out.println("*******************************");
        System.out.println("| 公司名称   | 公司单车借出次数 |");
        System.out.println("*******************************");
        for (int i = companiesArrayCopy.length - 1; i >= 0; i--) {
            String companiesName = companiesArrayCopy[i].getCompanyName();
            int borrowNo = companiesArrayCopy[i].getBorrowCount();
            System.out.println("| " + companiesName + "\t|       " + borrowNo + "       |");
            System.out.println("*******************************");
        }
    }

    public void showBike(BikeCompany[] companiesArr) {
        for (int i = 0; i < companiesArr.length; i++) {
            Views.companyView();
            System.out.println(companiesArr[i]);
//            Objects.requireNonNull(companiesArr[i].getSharedBikes());
            if (!Utils.isNull(companiesArr[i].getSharedBikes())) {
                Views.bikeView();
                for (int j = 0; j < companiesArr[i].getSharedBikes().length; j++) {
                    System.out.print("   " + (j + 1));
                    System.out.println(companiesArr[i].getSharedBikes()[j]);
                }
            }
        }
    }
}

