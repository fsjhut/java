package com.test.Bike;
/**
 * @className: Test
 * @description:   
 * @author SunHang
 * @createTime 2021/3/26 9:10
 */
public class Test {
    public static void main(String[] args) {
        BaseDaoImpl baseDao = new BaseDaoImpl();
        int[] arr = new int[]{1,4,6,3,1,2,5,6,8,44,22,0,6,22,10};
        int[] arr2 = baseDao.rankList(arr);
        for(int i=0;i<arr.length;i++){
            System.out.println(arr[arr2[i]]);
        }
    }
}
