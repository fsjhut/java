package java;

import java.util.Scanner;

/**
 * @author : SunHang
 * @className: Test
 * @description:
 * @date: 2021/3/16 12:24
 * @version: 0.1
 * @since: 1.8
 */
public class Test {
    public static void main(String[] args){
        Scanner scanner = new Scanner(System.in);
        System.out.print("请录入学生的id：");
        int id = scanner.nextInt();
        System.out.println("学生ID：" + id);
        scanner.nextLine(); // 读取回车后的空
        System.out.println("请输入需要录入的时间：");
        String date = scanner.nextLine(); // date存在于堆空间中是new的对象
        System.out.println("时间： " + date);
    }
}
