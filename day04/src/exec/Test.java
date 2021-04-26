package exec;

import java.util.Arrays;
import java.util.Scanner;

/**
 * @author SunHang
 * @className: Test
 * @description 第四天的作业
 * @createTime 2021/3/18 13:02
 */
public class Test {
    public static Scanner scanner = new Scanner(System.in);

    public static void main(String[] args) {
        Test test = new Test();
        System.out.println("-----------------------");
        test.test1();
        System.out.println("-----------------------");
        test.test2();
        System.out.println("-----------------------");
        test.test3();
        System.out.println("-----------------------");
        test.test4();
        System.out.println("-----------------------");
        test.test5();
        System.out.println("-----------------------");
        test.test6();
        System.out.println("-----------------------");
        test.test7();
        System.out.println("-----------------------");

    }

    /**
     * 在歌唱比赛中，共有10位评委进行打分，在计算歌手得分时，
     * 去掉一个最高分，去掉一个最低分，然后剩余的8位评委的分数进行平均，
     * 就是该选手的最终得分。输入每个评委的评分，求某选手的得分
     */
    public void test1() {
        // 利用随机函数生成一个60-100之间的分数
        int[] score = new int[10];
        for (int i = 0; i < score.length; i++) {
            score[i] = (int) (Math.random() * 41 + 60);
        }
        // 判断最高分，最低分，总分
        int min = score[0], max = score[0];
        double sum = 0;
        System.out.print("该选手的评委打分为：");
        for (int j = 0; j < score.length; j++) {
            if (score[j] < min) {
                min = score[j];
            }
            if (score[j] > max) {
                max = score[j];
            }
            System.out.print(score[j] + "\t");
            sum += score[j];
        }
        System.out.println("\n该选手的最终成绩为：" + (sum - min - max) / 8);
    }

    /**
     * 声明一个字符串的数组，空间为5个。
     * 使用循环接收五个学生的姓名。再使用循环输出这五个学生的姓名
     */
    public void test2() {
        String[] str = new String[5];
        for (int i = 0; i < str.length; i++) {
            System.out.print("请输入第" + (i + 1) + "个学生的姓名：");
            str[i] = scanner.next();
        }
        for (int i = 0; i < str.length; i++) {
            System.out.println("第" + (i + 1) + "个学生的姓名是：" + str[i]);
        }
    }

    /**
     * 声明一个int型的数组，循环接收8个学生的成绩，
     * 计算这8个学生的总分、平均分、最高分、最低分
     */
    public void test3() {
        int[] arr = new int[8];
        int min, max, sum = 0;
        double avg;
        for (int i = 0; i < arr.length; i++) {
            System.out.print("请输入第" + (i + 1) + "个学生的成绩：");
            arr[i] = scanner.nextInt();
        }
        min = arr[0];
        max = arr[0];
        for (int score : arr) {
            if (score > max) {
                max = score;
            }
            if (score < min) {
                min = score;
            }
            sum += score;
        }
        avg = sum / 8;
        System.out.println("所有学生的总分为：" + sum);
        System.out.println("所有学生的最高分为：" + max);
        System.out.println("所有学生的最低分为：" + min);
        System.out.println("所有学生的平均分为：" + avg);
    }

    /**
     * 现在有如下一个数组： int[] oldArr={1,3,4,5,0,0,6,6,0,5,4,7,6,7,0,5};
     * 要求将以上数组中的0项去掉，将不为0的值存入一个新的数组，生成新的数组为
     * Int[] newArr={1,3,4,5,6,6,5,4,7,6,7,5};
     */

    /**
     *@Title: 
     *@description TODO
     *@Version: 1.0
     *@author SunHang
     *@Date:  2021/3/18 18:49
     */
    public void test4() {
        int[] oldArr = {1, 3, 4, 5, 0, 0, 6, 6, 0, 5, 4, 7, 6, 7, 0, 5};
        // 计算非0的个数
        int num = 0;
        for (int i = 0; i < oldArr.length; i++) {
            if (oldArr[i] != 0) {
                num++;
            }
        }
        // 建立新的数组
        int[] newArr = new int[num];
        // 建立一个索引index代表新数组的位置
        int index = 0;
        for (int i = 0; i < oldArr.length; i++) {
            if (oldArr[i] != 0) {
                newArr[index] = oldArr[i];
                index++;
            }
        }
        System.out.println("老的数组为：" + Arrays.toString(oldArr));
        System.out.println("新的数组为：" + Arrays.toString(newArr));
    }

    /**
     * 有一整数数组，{1,3,-1,5,-2}; 将数据复制到新数组中 ，
     * 要求逆序输出新数组中的数，同时并将小于0的元素按0存储
     */
    public void test5() {
        int[] oldArr = {1, 3, -1, 5, -2};
        int[] newArr = new int[oldArr.length];
        for (int i = 0, j = oldArr.length - 1; i < oldArr.length; i++, j--) {
            if (oldArr[i] < 0) {
                newArr[j] = 0;
            } else {
                newArr[j] = oldArr[i];
            }
        }
        System.out.println("老的数组为：" + Arrays.toString(oldArr));
        System.out.println("新的数组为：" + Arrays.toString(newArr));
    }

    /**
     * Int[] array = {2,432,5221,235,2,5352,1,53,5,3,
     * 5364,2,2,63,3533,2,53,532,532};
     * 求所有元素和。
     * 输出所有奇数下标元素。如：array[1]
     * 输出所有元素中，值为奇数的。
     * 将所有元素乘二。
     * 将所有元素加到第一个元素中。
     * 将奇数位置元素存到B数组中
     * 偶数元素存到C数组中
     * 分别逆序输出B数组和C数组
     */
    public void test6() {
        int[] array = {2, 432, 5221, 235, 2, 5352, 1, 53, 5, 3, 5364, 2, 2, 63, 3533, 2, 53, 532, 532};
        int sum = 0;
        for (int i = 0; i < array.length; i++) {
            sum += array[i];
        }
        for (int i = 1; i < array.length; i += 2) {
            System.out.println("array[" + i + "] = " + array[i] + "\t");
        }
        for (int i = 0; i < array.length; i++) {
            if (array[i] % 2 != 0) {
                System.out.println("array[" + i + "]= " + array[i] + "\t");
            }
        }
        for (int i = 0; i < array.length; i++) {
            array[i] = 2*array[i];
        }
        //将所有元素加到第一个元素中。
        array[0] = 2*sum;
//      将奇数位置元素存到B数组中  偶数元素存到C数组中
        int [] arrayB = new int[array.length/2];
        int [] arrayC = new int[array.length-array.length/2];
        int indexB=0,indexC=0;
        for (int i = 0; i < array.length; i++) {
            if(i%2!=0){
                arrayB[indexB] = array[i];
                indexB++;
            }else{
                arrayC[indexC] = array[i];
                indexC++;
            }
        }
//        Arrays.sort;
        System.out.println("原数组为：" + Arrays.toString(array));
        System.out.println("奇数数组为：" + Arrays.toString(arrayB));
        System.out.println("偶数组为：" + Arrays.toString(arrayC));

    }

    /**
     * 从键盘接受10个整数保存在数组中，将10个数中最大的与第一个元素交换，
     * 最小的与最后一个元素交换，其余元素位置不变，输出数组元素的值
     */
    public void test7() {
        int[] arr = new int[10];
        for (int i = 0; i < arr.length; i++) {
            System.out.print("请输入第" + (i + 1) + "个元素: ");
            arr[i] = scanner.nextInt();
        }
        System.out.println("原数组的元素为：" + Arrays.toString(arr));
        int maxIndex = 0, minIndex = 0;
        // 定义中间变量用于交换
        int temp;
        for (int i = 0; i < arr.length; i++) {
            if (arr[i] > arr[maxIndex]) {
                maxIndex = i;
                // 是最大值的同时，肯定不是最小值了
                continue;
            }
            if (arr[i] < arr[minIndex]) {
                minIndex = i;
            }
        }
        // 进行最大的交换操作
        temp = arr[0];
        arr[0] = arr[maxIndex];
        arr[maxIndex] = temp;
        // 进行最小的交换
        temp = arr[9];
        arr[9] = arr[minIndex];
        arr[minIndex] = temp;
        System.out.println("新数组的元素为：" + Arrays.toString(arr));
    }
}

