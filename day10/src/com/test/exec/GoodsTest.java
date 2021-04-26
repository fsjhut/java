package com.test.exec;

import java.util.Scanner;

/**
 * @className: GoodsTest
 * @description:   
 * @author SunHang
 * @createTime 2021/3/28 16:17
 */
public class GoodsTest {
    public static void main(String[] args) {
        try {
            buyGoods();
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }
    public static void buyGoods() throws Exception {
        Scanner scanner = new Scanner(System.in);
        String[] goods = {"saf","safe","sdd"};
        int[] num = {2,0,4};
        System.out.println("请输入你需要购买的商品:");
        String str = scanner.next();

    }
}
