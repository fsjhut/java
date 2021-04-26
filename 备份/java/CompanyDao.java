package com.test.java;

import java.awt.*;
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
        do {
            System.out.print("请输入需要新建的公司名字: ");
            cmpName = scanner.next();
            for (int i = 0; i < companiesArray.length; i++) {
                if (cmpName.equals(companiesArray[i].bikeCompanyName)) {
                    System.out.println("该公司已存在,请重新输入!");
                }
            }
            break;
        } while (true);
        BikeCompany company = new BikeCompany(cmpName);
        BikeCompany[] newCmpArray = new BikeCompany[companiesArray.length + 1];
        for (int i = 0; i < companiesArray.length; i++) {
            newCmpArray[i] = companiesArray[i];
        }
        newCmpArray[companiesArray.length] = company;
        // 将新的赋值给旧的
        MainFuc.companyMgr.companiesArr = newCmpArray;
        System.out.println("增加公司成功!");
    }

    public void deleteCompany(BikeCompany[] companiesArray) {
        showCompany(companiesArray);
        System.out.print("请输入要删除公司的名字: ");
        int cmpCount = companiesArray.length;
        String deleteCmpName = scanner.next();
        int deleteCmpNo = findIndex(deleteCmpName);
        if(deleteCmpNo==-1){
            System.out.println("要删除的公司不存在!");
            return;
        }
        BikeCompany[] newCmpArr = new BikeCompany[cmpCount-1];
        for(int i = deleteCmpNo;i<cmpCount-1;i++){
            companiesArray[i] = companiesArray[i+1];
        }
        for(int i = 0; i<cmpCount-1;i++){
            newCmpArr[i] = companiesArray[i];
        }
        MainFuc.companyMgr.companiesArr =newCmpArr;
    }

    public void updateCompany(BikeCompany[] companiesArray) {
        showCompany(companiesArray);
        System.out.print("请输入要修改的公司名字: ");
        String editCmpName = scanner.next();
        int cmpIndex = findIndex(editCmpName);
        if(cmpIndex==-1){
            System.out.println("无法找到该公司!");
            return;
        }
        String newCmpName;
        do {
            System.out.print("请输入修改后的公司名字:");
            newCmpName = scanner.next();
            for (int i = 0; i < companiesArray.length; i++) {
                if (newCmpName.equals(companiesArray[i].bikeCompanyName)) {
                    System.out.println("该公司已存在,请重新输入!");
                }
            }
            break;
        } while (true);
        companiesArray[cmpIndex].bikeCompanyName = newCmpName;
        System.out.println("修改成功!");
    }

    // 根据公司名字找到公司的索引
    public int findIndex(String cmpName) {
        for(int i = 0; i<MainFuc.companyMgr.companiesArr.length;i++){
            if(cmpName.equals(MainFuc.companyMgr.companiesArr[i].bikeCompanyName)){
                return i;
            }
        }
        return -1;
    }

    public void showCompany(BikeCompany[] companiesArray) {
        for (int i = 0; i < companiesArray.length; i++) {
            companiesArray[i].bikeCompanyView();
        }
    }

    public void rankList(BikeCompany[] companiesArray) {
        int len = companiesArray.length;
        BikeCompany[] companiesArrayCopy = Arrays.copyOf(companiesArray, companiesArray.length);
        for (int i = 0; i < len - 1; i++) {
            for (int j = 0; j < len - 1 - i; j++) {
                if (companiesArrayCopy[j].borrowCount > companiesArrayCopy[j + 1].borrowCount) {
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
        for (int i = 0; i < companiesArrayCopy.length; i++) {
            String companiesName = companiesArrayCopy[i].bikeCompanyName;
            int borrowNo = companiesArrayCopy[i].borrowCount;
            System.out.println("| " + companiesName + "\t|       " + borrowNo + "       |");
            System.out.println("*******************************");
        }
    }

    public void showBike(BikeCompany[] companiesArr) {
        for(int i = 0; i<companiesArr.length;i++){
            companiesArr[i].bikeCompanyBikeInfo();
        }
    }

}

