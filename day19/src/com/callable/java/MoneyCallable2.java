package com.callable.java;

import lombok.Getter;
import lombok.Setter;

import java.util.concurrent.Callable;

/**
 * @className: MoneyCallable
 * @description:   
 * @author SunHang
 * @createTime 2021/4/8 21:48
 */
@Getter
@Setter
public class MoneyCallable2 {
    // 可以返回一个结果
    public static Integer balance = 2000;
    private final String NAME = "张三";
    private Integer money;
    private boolean flag;

    public MoneyCallable2(Integer money, boolean flag) {
        this.money = money;
        this.flag = flag;
    }

    public MoneyCallable2() {
    }

    public void money(Integer money,boolean flag) {
        if(flag){
            balance -= money;
            System.out.println(Thread.currentThread().getName() + "取了" + money + "还有：" + balance);
        }else {
            // 存钱
            balance += money;
            System.out.println(Thread.currentThread().getName() + "给" + NAME + "存入" + money + "，账户还有:" + balance);
        }
    }
}
