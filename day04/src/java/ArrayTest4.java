package java;

import java.util.Arrays;
import java.util.Scanner;

/**
 * @author SunHang
 * @className: ArrayTest4
 * @description 动态录入班级数，每个班级的学生数，每个学生的成绩
 *              遍历打印每个学生的成绩
 * @createTime 2021/3/18 18:52
 */
public class ArrayTest4 {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        System.out.print("请输入班级数：");
        int classNum = scanner.nextInt();
        // 建立二维数组
        int[][] twoArray = new int[classNum][];
        int classNo = 1;
        for(int[] array:twoArray){
            System.out.print("请输入"+ classNo +"班学生人数：");
            int studentNum = scanner.nextInt();
            // 初始化一维数组
            twoArray[classNo-1] = new int[studentNum];
            // 记录学生的个数信息
            int studentNo = 1;
            Arrays.sort(new int[]{1,2,3,4});
            for(int score:twoArray[classNo-1]){
                System.out.print("请录入第" + classNo + "个班级,第" + studentNo + "个学生的成绩:");
                twoArray[classNo-1][studentNo-1] = scanner.nextInt();
                studentNo++;
            }
            classNo++;
        }
        // 遍历每个学生的成绩
        System.out.println("-------------------------------");
        for(int i = 0; i<twoArray.length;i++){
            for(int j =0;j<twoArray[i].length;j++){
                System.out.println("第" + (i + 1) + "个班级,第" + (j + 1) + "个学生的成绩:" + twoArray[i][j]);
            }
        }
    }
}
