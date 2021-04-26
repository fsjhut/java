package exec;

import java.util.Scanner;
/**
 * 回型数的生成问题
 * @Title: HuiXingTest.tcp
 * @Package com.test
 * @author sun
 * @date 2021年2月7日
 * @version V1.0
 */
public class NumberOfRounds {
    public static void main(String[] args) {
        Scanner scan = new Scanner(System.in);
        System.out.println("请输入一个整数: ");
        int num = scan.nextInt();
        // 构造二维数组
        int[][] arr = new int[num][num];
        arr[0][0] = 1;
//		System.out.println(arr[3][3]);
        int k2=1;
        for(int i=1;i<num;i++) {
            arr[0][i] = arr[0][i-1] +1;
        }
        while(true) {
            int isFlag = 2*num-1;
            for(int i=k2;i<num+1-k2;i++) {
                arr[i][num-k2] = arr[i-1][num-k2]+1;
            }
            for(int i=num-k2;i>k2-1;i--) {
                arr[num-k2][i-1] = arr[num-k2][i]+1;
            }
            for(int i=num-k2;i>k2;i--) {
                arr[i-1][k2-1] = arr[i][k2-1]+1;
            }
            for(int i=k2;i<num-k2;i++) {
                arr[k2][i] = arr[k2][i-1] +1;
            }
            k2++;
            if(k2>(isFlag/4+1))
                break;
        }
        for(int i=0;i<num;i++) {
            for(int j=0;j<arr[i].length;j++) {
                System.out.printf("%5d",arr[i][j]);
            }
            System.out.println();
        }
    }
}
