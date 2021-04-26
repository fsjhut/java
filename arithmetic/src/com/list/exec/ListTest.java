package com.list.exec;

import java.util.ArrayList;
import java.util.Scanner;

/**
 * s0 = 1
 * s1 = 1 2 1
 * s2 = 1 2 1 3 1 2 1
 * s3 = 1 2 1 3 1 2 1 4 1 2 1 3 1 2 1
 * <p>
 * 最开始的思路： 利用递归和ArrayList直接计算出n的集合
 * 根据k值直接查找，算法时间复杂度和空间复杂度较高
 * <p>
 * 基础的改进：利用数字直接的规律进行查找。可能用到归并算法
 */
/**
 * 最开始的思路： 利用递归和ArrayList直接计算出n的集合
 *               根据k值直接查找，算法时间复杂度和空间复杂度较高
 *
 * 基础的改进：利用数字直接的规律进行查找。利用数字的对称性，每次折半
 *             进行递归操作。
 */

/**
 * @className: ListTest
 * @description:
 * @author SunHang
 * @createTime 2021/4/11 11:02
 */
public class ListTest {
    private static Scanner scanner = new Scanner(System.in);

    public static void main(String[] args) {
        System.out.print("请输入一整数n：");
        int n = scanner.nextInt();
        System.out.print("请输入要查找数字的索引：");
        int k = scanner.nextInt();
        ArrayList<Integer> numList = test1(n);
        numList.forEach((i) -> {
            System.out.print(i + "\t");
        });
        System.out.println();
        System.out.println("---------------------------");
        int result = test2(n, k);
        System.out.println(result);
        System.out.println(numList.get(k));
        if (result == numList.get(k)) {
            System.out.println("true");
        } else {
            System.out.println("false");
        }
    }

    private static int test2(int n, int k) {
        // 判断输入是否合法
        int result;
        if ((k > (2 << n + 1) - 2)) {
            throw new RuntimeException("输入的k超过了最大索引");
        }
        result = test3(n, k);

        // 找到k所属的合法的最小的n的值
        return result;

    }

    private static int test3(int n, int k) {
        int maxIndex = (1 << n + 1) - 2;
        for (int i = 0; i <= n; i++) {
            int temp = (1 << i) - 1;
            if (k == temp) {
                return i + 1;
            }
        }
        if(k>maxIndex/2){
            k = maxIndex - k;
        }
        n = n - 1;
        return test3(n, k);
    }

    private static ArrayList<Integer> test1(int n) {
        ArrayList<Integer> list = new ArrayList<>(10);
        ArrayList<Integer> tempList;
        if (n < 0) {
            System.out.println("输入的n有误");
            throw new RuntimeException("非法的输入!");
        }
        if (n == 0) {
            list.add(1);
        } else {
            tempList = test1(n - 1);
            list.addAll(tempList);
            list.add(n + 1);
            list.addAll(tempList);
        }
        return list;
    }
}
