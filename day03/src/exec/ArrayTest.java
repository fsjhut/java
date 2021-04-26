package exec;

import java.util.Arrays;
import java.util.Scanner;

/**
 * @author : SunHang
 * @className: Test
 * @description:
 * @date: 2021/3/16 22:26
 * @version: 0.1
 * @since: 1.8
 */
public class ArrayTest {
    public static Scanner scanner = new Scanner(System.in);

    public static void main(String[] args) {
        ArrayTest test = new ArrayTest();
//        test.test1();
//        test.test2();
//        test.test3();
//        test.test4();
//        test.test5();
//        test.test6();
//        test.test7();
        test.deleteRepeatElementTest();
        // 给一个数组，可以删除指定重复元素
        // 同时数组的长度变小

    }

    /**
     * 有一列乱序的字符，‘a’,‘c’,‘u’,‘b’,‘e’,‘p’,‘f’,‘z’，
     * 排序并按照英文字母表的逆序输出
     * 从大到小逆序排序
     * 采用冒泡排序
     */
    public void test1() {
        char[] arr = {'a', 'c', 'u', 'b', 'e', 'p', 'f', 'z'};
        System.out.println("逆序排列：");
        for (int i = 0; i < arr.length - 1; i++) {
            for (int j = 0; j < arr.length - 1 - i; j++) {
                char tmp;
                if (arr[j] < arr[j + 1]) {
                    tmp = arr[j];
                    arr[j] = arr[j + 1];
                    arr[j + 1] = tmp;
                }
            }
        }
        for (int i = 0; i < arr.length; i++) {
            System.out.print(arr[i] + "\t");
        }
        System.out.println();
    }

    /**
     * 有一个数列：8，4，2，1，23，344，12
     * 1）循环输出数列的值
     * 2）求数列中所有数值的和
     * 3）猜数游戏：从键盘中任意输入一个数据，判断数列中是否包含此数
     */
    public void test2() {
        int[] arr = {8, 4, 2, 1, 2, 23, 344, 12};
        int sum = 0;
        int num = 1;
        for (int i : arr) {
            System.out.println("第" + num + "个数是：" + i);
            sum += i;
            num++;
        }
        System.out.println("数组元素的和是：" + sum);
        System.out.print("请输入需要查找的元素：");
        int searchNum = scanner.nextInt();
        // 定义一个标记
        boolean isFlag = false;
        // 记录找到元素的索引,最多10个重复的数字
        int[] index = new int[10];
        // 定义一个记录索引的值
        int colum = 0;
        for (int i = 0; i < arr.length; i++) {
            if (arr[i] == searchNum) {
                index[colum] = i;
                isFlag = true;
                colum++;
            }
        }
        if (isFlag) {
            System.out.print("已找到，该数字的索引为：");
            for (int i = 0; i < colum; i++) {
                System.out.print(index[i] + "\t\t");
            }
            System.out.println();
        } else {
            System.out.println("很抱歉，没有找到该数字！");
        }
    }

    /**
     * 李雷要去买一部手机，他询问了4家店的价格，
     * 分别是2899元，2950元，2850元和3100元，显示输出最低价
     */
    public void test3() {
        int[] arr = {2899, 2950, 2850, 3100};
        int min = arr[0];
        for (int i : arr) {
            if (i < min) {
                min = i;
            }
        }
        System.out.println("最低的价格是：" + min);
    }

    /**
     * 比较两个数组变量是否一致
     */
    public void test4() {
        int[] num = {1, 2, 3};
        int[] num2 = {1, 2, 5, 3};
        // 先判断地址是否相同
        if (num == num2) {
            System.out.println("元素一致");
        } else if (num.length != num2.length) {
            System.out.println("数据不一致");
        } else {
            for (int i = 0; i < num.length; i++) {
                if (num[i] != num2[i]) {
                    System.out.println("数据不一致");
                    return;
                }
            }
            System.out.println("元素一致");
        }
    }

    /**
     * 动态录入数组的元素
     * 求数组元素的最大值和最小值
     */
    public void test5() {
        System.out.println("请输入需要录入的学生的人数：");
        int studentNum = scanner.nextInt();
        int[] arr = new int[studentNum];
        // 定义最高分，最低分
        int min = 200, max = -1;
        double total = 0;
        for (int i = 0; i < arr.length; i++) {
            System.out.print("请输入第" + (i + 1) + "学生的成绩：");
            arr[i] = scanner.nextInt();
            total += arr[i];
            if (arr[i] < min) {
                min = arr[i];
            }
            if (arr[i] > max) {
                max = arr[i];
            }
        }
        System.out.println("学生成绩最大值为：" + max);
        System.out.println("学生成绩的最小值为：" + min);
        System.out.println("学生成绩的平均成绩是：" + total / arr.length);
    }

    /**
     * 数组增加元素
     * 将两个数组合并
     */
    public void test6() {
        int[] num1 = {1, 2, 3, 4, 0, 0, 1, 0, 0};
        int[] num2 = new int[15];
        num2[0] = 100;
        num2[1] = 0;
        num2[2] = 0;
        num2[3] = 400;
        num2[4] = 0;
        num2[5] = 0;
        num2[6] = 0;
        num2[7] = 900;
        // 将num1的所有元素都存入num2中
        // 连续为0 则代表数组结束
        int num2Length = deleteRedundancy(num2);
        int num1Length = deleteRedundancy(num1);
        int[] num3 = new int[num1Length + num2Length];
        for (int i = 0; i < num1Length; i++) {
            num3[i] = num1[i];
        }
        int colum = 0;
        for (int i = num1Length; i < num3.length; i++) {
            num3[i] = num2[colum];
            colum++;
        }
        System.out.println(Arrays.toString(num1));
        System.out.println(Arrays.toString(num2));
        System.out.println(Arrays.toString(num3));
    }

    public void deleteRepeatElementTest() {
        System.out.println("请输入字符串数组的长度：");
        int strLength = scanner.nextInt();
        String[] stringsArray = new String[strLength];
        for (int i = 0; i < stringsArray.length; i++) {
            System.out.print("请输入第" + (i + 1) + "个字符串：");
            stringsArray[i] = scanner.next();
        }
        System.out.print("请输入需要删除的元素：");
        String element = scanner.next();
        System.out.println("----------------------------------------------");
        System.out.println("原来的字符串数组为：" + Arrays.toString(stringsArray));
        String[] newString = deleteRepeatElement(stringsArray, element);
        System.out.println("删除重复字符串后的数组为：" + Arrays.toString(stringsArray));
        System.out.println("去除无用值后的新字符串数组为：" + Arrays.toString(newString));
    }

    // 删除指定元素的功能 数组
    public String[] deleteRepeatElement(String[] str, String element) {
        int index = 0;
        for(int i=0;i<str.length;i++){
            // 找到
            if(element.equals(str[i])){
                // 后续元素依次前移动
                index++;
                // j<str.length-index 可以改为j<str.length-1
                // 减去index，可以减少移动次数，即后续的元素不需要再移动
                for(int j = i;j<str.length-index;j++){
                    str[j] = str[j+1];
                }
                str[str.length-index] = null;
                // 如果有重复元素
                if(element.equals(str[i])){
                    i--;
                }
            }
        }

        int stringLength = deleteNull(str);
        String[] str2 = new String[stringLength];
        for (int i = 0; i < str2.length; i++) {
            str2[i] = str[i];
        }
//        System.out.println(Arrays.toString(str2));
        return str2;
    }

    /**
     * 去除冗余的数组长度
     * 接受值：数组
     * 返回数组的本来长度
     */
    public int deleteRedundancy(int[] arr) {
        int length = -1;
        for (int i = 0; i < arr.length; i++) {
            if (arr[i] != 0) {
                length = arr.length;
            } else {
                boolean isFlag = false;
                for (int j = i + 1; j < arr.length; j++) {
                    if (arr[j] != 0) {
                        isFlag = true;
                    }
                }
                if (!isFlag) {
                    length = i;
                    break;
                }
            }
        }
        return length;
    }

    public int deleteNull(String[] arr) {
        int length = -1;
        for (int i = 0; i < arr.length; i++) {
            if (arr[i] != null) {
                length = arr.length;
            } else {
                boolean isFlag = false;
                for (int j = i + 1; j < arr.length; j++) {
                    if (arr[j] != null) {
                        isFlag = true;
                    }
                }
                if (!isFlag) {
                    length = i;
                    break;
                }
            }
        }
        return length;
    }
}
