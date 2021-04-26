package com.test.exec;

import lombok.Getter;
import lombok.Setter;

/**
 * @className: Account
 * @description:   
 * @author SunHang
 * @createTime 2021/4/3 15:35
 */
@Getter
@Setter
public class Account {
    public static long num = 1001;
    private long id;
    private double balance;
    private String password;

    public Account(double balance, String password) {
        this.id = num;
        this.balance = balance;
        this.password = password;
        num++;
    }

    public Account() {
        num++;
    }

    @Override
    public String toString() {
        return balance + "}";
    }
}
