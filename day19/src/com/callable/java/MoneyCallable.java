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
public class MoneyCallable implements Callable<Integer> {
    // 可以返回一个结果
    private static Integer balance = 2000;
    private final String NAME = "张三";
    private Integer money;
    private boolean flag;

    public MoneyCallable(Integer money, boolean flag) {
        this.money = money;
        this.flag = flag;
    }

    public MoneyCallable() {
    }

    @Override
    public Integer call() throws Exception {
        if(flag){
            balance -= money;
            System.out.println(Thread.currentThread().getName() + "取了" + money + "还有：" + balance);
        }else {
            // 存钱
            balance += money;
            System.out.println(Thread.currentThread().getName() + "给" + NAME + "存入" + money + "，账户还有:" + balance);
        }
        return balance;

    }
}
