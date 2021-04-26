package service;

import bean.MemberInfo;
import com.alibaba.fastjson.JSON;
import common.ServerResponse;
import imple.MemberDAOImpl;
import util.Utils;
import view.Views;

import java.math.BigDecimal;
import java.util.Scanner;

/**
 * @author SunHang
 * @className: MemberServer
 * @description:
 * @createTime 2021/4/21 20:24
 */
public class MemberServer {
    private static MemberDAOImpl memberDAO;
    private static Scanner scanner = new Scanner(System.in);

    static {
        memberDAO = new MemberDAOImpl();
    }

    public void memberMgr() {
        while (true) {
            Views.MemberMgrView();
            System.out.print("请输入您的选择：");
            String choice = scanner.next();
            switch (choice) {
                case ("1"):
                    addMember();
                    break;
                case ("2"):
                    editMember();
                    break;
                case ("3"):
                    selectMember();
                    break;
                case ("4"):
                    deleteMember();
                    break;
                case ("5"):
                    addMoney();
                    break;
                case ("6"):
                    return;
                case ("7"):
                    Utils.exitSystem();
                default:
                    System.out.println("输入错误，请重新输入");
                    break;
            }
        }
    }

    private void addMoney() {
        System.out.print("请输入你要充值的会员ID:");
        int memberId = scanner.nextInt();
        ServerResponse serverResponse = memberDAO.selectMemberById(memberId);
        if (serverResponse.getCode() == 400) {
            System.out.println("该ID对应的会员不存在");
            return;
        } else {
            System.out.println("该会员信息如下：");
            MemberInfo memberInfo = (MemberInfo) serverResponse.getData();
            System.out.println(memberInfo);
        }
        System.out.print("请输入你要充值的金额：");
        BigDecimal addMoney = scanner.nextBigDecimal();
        int i = memberDAO.addMoney(memberId, addMoney);
        if (i == 0) {
            System.out.println("充值失败");
        } else {
            System.out.println("充值成功");
        }
    }

    private void deleteMember() {
        System.out.print("请输入你要删除的会员Id：");
        int memberId = scanner.nextInt();
        ServerResponse serverResponse = memberDAO.selectMemberById(memberId);
        if (serverResponse.getCode() == 400) {
            System.out.println("会员不存在！");
            return;
        }
        MemberInfo memberInfo = (MemberInfo) serverResponse.getData();
        System.out.println("该会员的信息是：" + memberInfo);
        System.out.print("确认删除(y/n)?");
        String verify = scanner.next();
        if ("y".equals(verify)) {
            ServerResponse serverResponse1 = memberDAO.deleteMemberById(memberId);
            if (serverResponse1.getCode() == 200) {
                System.out.println("删除成功");
            } else {
                System.out.println("删除失败");
            }
        }
    }

    private void selectMember() {
        System.out.println("1：查询单个会员信息 2：查询所有会员信息");
        int choice = scanner.nextInt();
        if (choice == 1) {
            System.out.print("请输入会员的ID：");
            int memberId = scanner.nextInt();
            ServerResponse serverResponse = memberDAO.selectMemberById(memberId);
            if (serverResponse.getCode() == 400) {
                System.out.println("查询失败");
            } else {
                System.out.println("查询成功，会员信息如下：");
                MemberInfo memberInfo = (MemberInfo) serverResponse.getData();
                String image = Utils.getImage(memberInfo.getPhoto());
                System.out.println(memberInfo);
                System.out.println("会员图像路径：" + image);
            }
        } else if (choice == 2) {
            ServerResponse serverResponse = memberDAO.selectAllMember();
            String jsonString = JSON.toJSONString(serverResponse);
            System.out.println(jsonString);
        } else {
            System.out.println("输入错误");
        }
    }

    private void editMember() {
        System.out.print("要修改会员的Id:");
        int id = scanner.nextInt();
        ServerResponse serverResponse1 = memberDAO.selectMemberById(id);
        if (serverResponse1.getCode() == 400) {
            System.out.println("该会员不存在");
            return;
        }
        MemberInfo memberInfo = (MemberInfo) serverResponse1.getData();
        System.out.println("会员信息如下:" + memberInfo);
        System.out.println("请选择要修改的字段: 1.name 2.pass 3.photo 4.phone 5.score 6.balance");
        String choice = scanner.next();//1,2,3
        String[] array = choice.split(",");
        for (String s : array) {
            int num = Integer.parseInt(s);
            switch (num) {
                case 1:
                    System.out.print("请录入修改后会员名字：");
                    String newName = scanner.next();
                    memberInfo.setName(newName);
                    break;
                case 2:
                    System.out.print("请录入新的密码:");
                    String newPass = scanner.next();
                    memberInfo.setPass(newPass);
                    break;
                case 3:
                    System.out.print("请录入新的会员头像路径:");
                    String newImagePath = scanner.nextLine();
                    int isFlag = Utils.uploadImage(newImagePath);
                    if (isFlag == 0) {
                        System.out.println("上传失败，请重试");
                        return;
                    }
                    memberInfo.setPhoto(isFlag);
                    break;
                case 4:
                    System.out.print("请录入新的手机号码:");
                    memberInfo.setPhone(scanner.next());
                    break;
                case 5:
                    System.out.print("请录入新的会员积分:");
                    memberInfo.setScore(scanner.nextFloat());
                    break;
                case 6:
                    System.out.print("请录入新的余额:");
                    memberInfo.setBalance(scanner.nextBigDecimal());
                    break;
            }
        }
        System.out.println("修改之后的商品类型信息:" + memberInfo);
        ServerResponse serverResponse = memberDAO.updateMemberById(memberInfo);
        if (serverResponse.getCode() == 200) {
            System.out.println("修改成功！");
            System.out.println(serverResponse);
        } else {
            System.out.println("修改失败！");
        }
    }

    private void addMember() {
        System.out.print("请输入你要添加的会员名字：");
        String memberName = scanner.next();
        System.out.print("请输入你要设置的会员密码：");
        String memberPass = scanner.next();
        System.out.print("请重新输入密码：");
        String memberPass2 = scanner.next();
        if (!memberPass.equals(memberPass2)) {
            System.out.println("两次输入不一致");
            return;
        }
        System.out.print("请输入你要添加的会员头像(路径)：");
        scanner.nextLine();
        String imagePath = scanner.nextLine();
        int isFlag = Utils.uploadImage(imagePath);
        if (isFlag == 0) {
            System.out.println("会员头像添加失败！");
            return;
        }
        System.out.print("请输入你要添加的会员手机号：");
        String memberPhone = scanner.next();
        System.out.print("请输入你要添加的会员积分：");
        float score = scanner.nextFloat();
        System.out.print("请输入你要添加的会员余额：");
        BigDecimal balance = scanner.nextBigDecimal();
        String md5Pass = Utils.encodeStr(memberPass);
        MemberInfo memberInfo = new MemberInfo(memberName, md5Pass, memberPhone, isFlag, score, balance);
        ServerResponse serverResponse1 = memberDAO.addMember(memberInfo);
        if (serverResponse1.getCode() == 200) {
            System.out.println("添加成功！");
        } else {
            System.out.println("添加失败！");
        }
    }
}
