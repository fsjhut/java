package java;

import java.util.Arrays;

/**
 * @author SunHang
 * @className: ArrayTest2
 * @description 插入排序
 * @createTime 2021/3/18 18:49
 */
public class ArrayTest2 {
    public static void main(String[] args) {
        int[] arr = {20,100,1,9,34,33,55,11,9};
        System.out.println("原始数组为："+Arrays.toString(arr));
        for(int i = 1; i<arr.length;i++){
            int leftIndex = i-1;
            int temp = arr[i];
            while (leftIndex>=0&&temp<arr[leftIndex]){
                arr[leftIndex+1]=arr[leftIndex];
                leftIndex--;
            }
            arr[leftIndex+1] = temp;
        }
        System.out.println("排序后的数组为："+Arrays.toString(arr));
    }

}
