package com.test.exec;

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
        User[] newUserArr = new User[usersArr.length + 1];
        for (int i = 0; i < usersArr.length; i++) {
            newUserArr[i] = usersArr[i];
        }
        newUserArr[usersArr.length] = user;
        // 将新的赋值给旧的
        usersArr = newUserArr;
        System.out.println("恭喜您注册成功!");
        return usersArr;
    }

    public int addUserView() {
        System.out.println("*******1.公司员工*******");
        System.out.println("*******2.公司用户*******");
        System.out.print("请选择类型: ");
        int temp = scanner.nextInt();
        return temp;

    }

    public void deleteUser(BikeCompany bikeCompany) {
        int cls = addUserView();
        if (cls != 1 && cls != 2) {
            System.out.println("输入错误!");
            return;
        }
        if (cls == 1) {
            int userLen = bikeCompany.staff.length;
            for (int i = 0; i < userLen; i++) {
                System.out.println(bikeCompany.staff[i]);
            }
            System.out.print("请输入要删除的员工名字: ");
            String deleteStaffName = scanner.next();
            int delUserIndex = Utils.findIndex(deleteStaffName, bikeCompany.staff);
            if (delUserIndex == -1) {
                System.out.println("删除的员工不存在!");
                return;
            }
            User[] newUserArr = new User[userLen - 1];
            for (int i = delUserIndex; i < userLen - 1; i++) {
                bikeCompany.staff[i] = bikeCompany.staff[i + 1];
            }
            for (int i = 0; i < userLen - 1; i++) {
                newUserArr[i] = bikeCompany.staff[i];
            }
            bikeCompany.staff = newUserArr;
            bikeCompany.staffSum--;
        } else {
            int userLen = bikeCompany.users.length;
            for (int i = 0; i < userLen; i++) {
                System.out.println(bikeCompany.users[i]);
            }
            System.out.print("请输入要删除的用户名字: ");
            String deleteStaffName = scanner.next();
            int delUserIndex = Utils.findIndex(deleteStaffName, bikeCompany.users);
            if (delUserIndex == -1) {
                System.out.println("删除的用户不存在!");
                return;
            }
            User[] newUserArr = new User[userLen - 1];
            for (int i = delUserIndex; i < userLen - 1; i++) {
                bikeCompany.users[i] = bikeCompany.users[i + 1];
            }
            for (int i = 0; i < userLen - 1; i++) {
                newUserArr[i] = bikeCompany.users[i];
            }
            bikeCompany.users = newUserArr;
            bikeCompany.userSum--;
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
            int userLen = bikeCompany.staff.length;
            for (int i = 0; i < userLen; i++) {
                System.out.println(bikeCompany.staff[i]);
            }
            System.out.print("请输入要修改的员工名字: ");
            String deleteStaffNo = scanner.next();
            int editUserIndex = Utils.findIndex(deleteStaffNo, bikeCompany.staff);
            if (editUserIndex == -1) {
                System.out.println("修改的员工不存在!");
                return;
            }
            boolean isFlag = true;
            String editName = "";
            while (isFlag) {
                System.out.print("新的用户名：");
                editName = scanner.next();
                if (Utils.isRegisterArray(editName, bikeCompany.staff)) { // 已经注册
                    System.out.println("该用户名已注册，请重新输入用户名");
                } else {
                    isFlag = false;
                }
            }
            System.out.print("密码：");
            String editPassword = scanner.next();
            bikeCompany.staff[editUserIndex].name = editName;
            bikeCompany.staff[editUserIndex].passWord = editPassword;
        } else {
            int userLen = bikeCompany.users.length;
            for (int i = 0; i < userLen; i++) {
                System.out.println(bikeCompany.users[i]);
            }
            System.out.print("请输入要修改的员工名字: ");
            String deleteStaffNo = scanner.next();
            int editUserIndex = Utils.findIndex(deleteStaffNo, bikeCompany.users);
            if (editUserIndex == -1) {
                System.out.println("修改的员工不存在!");
                return;
            }
            boolean isFlag = true;
            String editName = "";
            while (isFlag) {
                System.out.print("新的用户名：");
                editName = scanner.next();
                if (Utils.isRegisterArray(editName, bikeCompany.users)) { // 已经注册
                    System.out.println("该用户名已注册，请重新输入用户名");
                } else {
                    isFlag = false;
                }
            }
            System.out.print("密码：");
            String editPassword = scanner.next();
            System.out.print("地址：");
            String editArea = scanner.next();
            bikeCompany.users[editUserIndex].name = editName;
            bikeCompany.users[editUserIndex].passWord = editPassword;
            bikeCompany.users[editUserIndex].aera = editArea;
            System.out.println("更新成功!");
        }
    }

    // 显示用户信息
    public void showMember(BikeCompany bikeCompany) {
        int temp = addUserView();
        if (temp == 1) {
            for (int i = 0; i < bikeCompany.staff.length; i++) {
                System.out.println(bikeCompany.staff[i]);
            }
        } else {
            for (int i = 0; i < bikeCompany.users.length; i++) {
                System.out.println(bikeCompany.users[i]);
            }
        }
    }
}
