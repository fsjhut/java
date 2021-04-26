package com.test.newbike;

import java.util.Scanner;

/**
 * @author SunHang
 * @className: UserDao
 * @description:
 * @createTime 2021/3/21 18:45
 */
// 用户增删改查
public class UserDao {
    private Scanner scanner = new Scanner(System.in);
//    static CompanyMgr cmpMgr = new CompanyMgr();

    // 数组增加一个元素的方法
    public User[] addUser(User user, User[] usersArr) {
        // 公司数组 数组增加一个元素
        // 将user复制到数组的最后一个元素
        int usersArrLen;
        if(Utils.isNull(usersArr)){
            usersArrLen = 0;
        }else{
            usersArrLen = usersArr.length;
        }
        User[] newUserArr = new User[usersArrLen + 1];
        for (int i = 0; i < usersArrLen; i++) {
            newUserArr[i] = usersArr[i];
        }
        newUserArr[usersArrLen] = user;
        // 将新的赋值给旧的
        usersArr = newUserArr;
        System.out.println("恭喜您注册成功!");
        return usersArr;
    }

    public int addUserView() {
        Views.addUserView();
        return scanner.nextInt();
    }

    public void deleteUser(BikeCompany bikeCompany) {
        int cls = addUserView();
        if (cls != 1 && cls != 2) {
            System.out.println("输入错误!");
            return;
        }
        if (cls == 1) {
            if(Utils.isNull(bikeCompany.getStaff())){
                System.out.println("该公司没有员工!");
                return;
            }
            int userLen = bikeCompany.getStaff().length;
            for (int i = 0; i < userLen; i++) {
                System.out.println(bikeCompany.getStaff()[i]);
            }
            System.out.print("请输入要删除的员工名字: ");
            String deleteStaffName = scanner.next();
            int delUserIndex = Utils.findIndex(deleteStaffName, bikeCompany.getStaff());
            if (delUserIndex == -1) {
                System.out.println("删除的员工不存在!");
                return;
            }
            User[] newUserArr = new User[userLen - 1];
            for (int i = delUserIndex; i < userLen - 1; i++) {
                bikeCompany.getStaff()[i] = bikeCompany.getStaff()[i + 1];
            }
            for (int i = 0; i < userLen - 1; i++) {
                newUserArr[i] = bikeCompany.getStaff()[i];
            }
            bikeCompany.setStaff(newUserArr);
            bikeCompany.setStaffSum(bikeCompany.getStaffSum() - 1);
        } else {
            if(Utils.isNull(bikeCompany.getStaff())){
                System.out.println("该公司没有用户!");
                return;
            }
            int userLen = bikeCompany.getUsers().length;
            for (int i = 0; i < userLen; i++) {
                System.out.println(bikeCompany.getUsers()[i]);
            }
            System.out.print("请输入要删除的用户名字: ");
            String deleteStaffName = scanner.next();
            int delUserIndex = Utils.findIndex(deleteStaffName, bikeCompany.getUsers());
            if (delUserIndex == -1) {
                System.out.println("删除的用户不存在!");
                return;
            }
            User[] newUserArr = new User[userLen - 1];
            for (int i = delUserIndex; i < userLen - 1; i++) {
                bikeCompany.getUsers()[i] = bikeCompany.getUsers()[i + 1];
            }
            for (int i = 0; i < userLen - 1; i++) {
                newUserArr[i] = bikeCompany.getUsers()[i];
            }
            bikeCompany.setUsers(newUserArr);
            bikeCompany.setUserSum(bikeCompany.getUserSum() - 1);
            System.out.println("删除成功!");
        }
    }

    public void editUser(BikeCompany bikeCompany) {
        int cls = addUserView();
        if (cls != 1 && cls != 2) {
            System.out.println("输入错误!");
            return;
        }
        if (cls == 1) {
            if(Utils.isNull(bikeCompany.getStaff())){
                System.out.println("该公司没有员工!");
                return;
            }
            int userLen = bikeCompany.getStaff().length;
            for (int i = 0; i < userLen; i++) {
                System.out.println(bikeCompany.getStaff()[i]);
            }
            System.out.print("请输入要修改的员工名字: ");
            String deleteStaffNo = scanner.next();
            int editUserIndex = Utils.findIndex(deleteStaffNo, bikeCompany.getStaff());
            if (editUserIndex == -1) {
                System.out.println("修改的员工不存在!");
                return;
            }
            boolean isFlag = true;
            String editName = "";
            while (isFlag) {
                System.out.print("新的用户名：");
                editName = scanner.next();
                if (Utils.isRegisterArray(editName, bikeCompany.getStaff())) { // 已经注册
                    System.out.println("该用户名已注册，请重新输入用户名");
                } else {
                    isFlag = false;
                }
            }
            System.out.print("密码：");
            String editPassword = scanner.next();
            bikeCompany.getStaff()[editUserIndex].setName(editName);
            bikeCompany.getStaff()[editUserIndex].setPassWord(editPassword);
        } else {
            if(Utils.isNull(bikeCompany.getStaff())){
                System.out.println("该公司没有用户!");
                return;
            }
            int userLen = bikeCompany.getUsers().length;
            for (int i = 0; i < userLen; i++) {
                System.out.println(bikeCompany.getUsers()[i]);
            }
            System.out.print("请输入要修改的员工名字: ");
            String deleteStaffNo = scanner.next();
            int editUserIndex = Utils.findIndex(deleteStaffNo, bikeCompany.getUsers());
            if (editUserIndex == -1) {
                System.out.println("修改的员工不存在!");
                return;
            }
            boolean isFlag = true;
            String editName = "";
            while (isFlag) {
                System.out.print("新的用户名：");
                editName = scanner.next();
                if (Utils.isRegisterArray(editName, bikeCompany.getUsers())) { // 已经注册
                    System.out.println("该用户名已注册，请重新输入用户名");
                } else {
                    isFlag = false;
                }
            }
            System.out.print("密码：");
            String editPassword = scanner.next();
            System.out.print("地址：");
            String editArea = scanner.next();
            bikeCompany.getUsers()[editUserIndex].setName(editName);
            bikeCompany.getUsers()[editUserIndex].setPassWord(editPassword);
            bikeCompany.getUsers()[editUserIndex].setArea(editArea);
            System.out.println("更新成功!");
        }
    }

    // 显示用户信息
    public void showMember(BikeCompany bikeCompany) {
        int temp = addUserView();
        if (temp == 1) {
            if (!Utils.isNull(bikeCompany.getStaff())) {
                for (int i = 0; i < bikeCompany.getStaff().length; i++) {
                    System.out.println(bikeCompany.getStaff()[i]);
                }
            }else{
                System.out.println("该公司没有员工!");
            }
        } else if (temp == 2){
            if (!Utils.isNull(bikeCompany.getUsers())) {
                for (int i = 0; i < bikeCompany.getUsers().length; i++) {
                    System.out.println(bikeCompany.getUsers()[i]);
                }
            }else{
                System.out.println("该公司没有用户!");
            }
        }else{
            System.out.println("输入错误!");
        }
    }
}
