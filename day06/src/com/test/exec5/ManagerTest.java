package com.test.exec5;
/**
 * @className: ManagerTest
 * @description
 * @author SunHang
 * @createTime 2021/3/20 9:50
 */

import java.util.Scanner;

/**
 * 编写一个测试类，创建一个管理员类，初始化设置用户名和密码，
 * 控制台接收用户输入的用户名和密码，调用验证的方法，
 * 显示是否登录成功。
 */
public class ManagerTest {
    public static void main(String[] args) {
        Manager manager = new Manager("sun", "123");
        Scanner scanner = new Scanner(System.in);
        int num = 3;
        while (true) {
            System.out.print("请输入用户名: ");
            String name = scanner.next();
            System.out.print("请输入密码: ");
            String password = scanner.next();
            if (manager.proffMember(name, password)) {
                System.out.println("登陆成功,欢迎您!");
                System.out.println(manager.managerInfo());
                break;
            } else {
                System.out.println("用户名或密码错误!");
                num--;
                if (num != 0) {
                    System.out.println("您还有" + num + "次机会");
                } else {
                    System.out.println("机会已用完,系统退出!");
                    break;
                }
            }
        }
    }
}
