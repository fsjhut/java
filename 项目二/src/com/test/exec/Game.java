package com.test.exec;


import java.util.Objects;
import java.util.Scanner;

/**
 * @author SunHang
 * @className: Game
 * @description:
 * @createTime 2021/3/23 17:05
 */
public class Game {
    private static UserInfo user;
    private static Computer computer;
    static Scanner scanner = new Scanner(System.in);

    public static void main(String[] args) {
        Game game = new Game();
        game.start();
    }

    static {
        user = new UserInfo();
        computer = new Computer();
    }

    private void start() {
        String nextJu;
        do {
            showViews();
            chooseRole();
            System.out.println("要开始吗?y/n");
            String temp = scanner.next();
            if (temp.equals("n")) {
                System.out.println("程序结束!");
                return;
            }
            String next;
            int count = 0;
            do {
                int userFist = user.showFist();
                int computerFist = computer.showFist();
                calculate(userFist, computerFist);
                System.out.println("是否开始下一轮?y/n");
                next = scanner.next();
            } while (Objects.equals("y", next));
            calResult(count);

            System.out.println("----------------------------");
            System.out.println("是否开始下一局?y/n");
            nextJu = scanner.next();
            user.setScore(0);
            computer.setScore(0);
        } while (Objects.equals(nextJu, "y"));

    }

    private void calResult(int count) {
        String userInfoName = user.getName();
        String computerName = computer.getComputerName();
        int userScore = user.getScore();
        int comScore = computer.getScore();

        System.out.println(userInfoName + "  VS  " + computerName);
        System.out.println("对战次数:" + count);
        System.out.println(" 姓名 \t  得分");
        System.out.println(userInfoName + "  VS " + userScore);
        System.out.println(computerName + "  VS " + comScore);

        if (userScore == comScore) {
            System.out.println(userInfoName + "与" + computerName + "平手");
        } else if (userScore > comScore) {
            System.out.println(userInfoName + "赢了");
        } else
            System.out.println(computerName + "赢了");
    }

    private void calculate(int userFist, int computerFist) {
        if (userFist == computerFist) {
            System.out.println("和局!");
        } else if ((userFist - computerFist) == 1 || (userFist - computerFist) == -2) {
            System.out.println("结果" + user.getName() + "赢!");
            user.setScore(user.getScore() + 1);
        } else {
            System.out.println("结果" + computer.getComputerName() + "赢!");
            computer.setScore(computer.getScore() + 1);
        }
    }

    private void chooseRole() {
        System.out.print("请选择对方角色(1.刘备 2.孙权 3.曹操):");
        int roleNum = scanner.nextInt();
        switch (roleNum) {
            case 1:
                System.out.println("你选择了刘备对战");
                computer.setComputerName("刘备");
                break;
            case 2:
                System.out.println("你选择了孙权对战");
                computer.setComputerName("孙权");
                break;
            case 3:
                System.out.println("你选择了曹操对战");
                computer.setComputerName("曹操");
                break;
        }
        System.out.print("请输入你的名字:");
        String userName = scanner.next();
        user.setName(userName);
    }

    private void showViews() {
        System.out.println("**************************");
        System.out.println("*****    猜拳开始    *****");
        System.out.println("**************************");
        System.out.println("出拳规则:1.剪刀 2.石头 3.布");
    }
}
