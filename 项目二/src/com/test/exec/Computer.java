package com.test.exec;

import lombok.Getter;
import lombok.Setter;

/**
 * @className: Computer
 * @description:   
 * @author SunHang
 * @createTime 2021/3/23 17:05
 */

@Setter
@Getter
public class Computer {
    private String computerName;
    private int score;

    public int showFist(){
        int computerNum = (int)(Math.random()*3+1);
        Utils.showFist(computerName, computerNum);
        return computerNum;
    }
}
