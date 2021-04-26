package com.test.exec;

import lombok.Getter;
import lombok.Setter;

import java.util.Scanner;

/**
 * @className: UserInfo
 * @description:   
 * @author SunHang
 * @createTime 2021/3/23 17:05
 */
@Getter
@Setter
public class UserInfo {
    private String name;
    private int score;
    Scanner scanner = new Scanner(System.in);
    public int showFist(){
        System.out.print("请出拳:1.剪刀 2. 石头 3. 布(输入相应数字):");
        int userFist = scanner.nextInt();
        Utils.showFist(name, userFist);
        return userFist;
    }
}
