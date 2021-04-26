package com.test.exec;
/**
 * @className: Utils
 * @description:   
 * @author SunHang
 * @createTime 2021/3/23 17:07
 */
public class Utils {
    public static void showFist(String name , int num){
        switch (num){
            case 1:
                System.out.println(name+"出拳: 剪刀");
                break;
            case 2:
                System.out.println(name+"出拳: 石头");
                break;
            case 3:
                System.out.println(name+"出拳: 布");
                break;
        }
    }
}
