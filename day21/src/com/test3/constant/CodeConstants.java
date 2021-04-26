package com.test3.constant;
/**
 * @className: CodeConstants
 * @description:   
 * @author SunHang
 * @createTime 2021/4/13 21:14
 */
public class CodeConstants {
    private CodeConstants(){}
    public static final String SUCCESS_MSG = "SUCCESS";
    public static final Integer SUCCESS_CODE = 200;
    public static final Integer ERROR_CODE = 400;
    public static final String ERROR_MSG = "ERROR";

    //用户
    //订单 已提交-1 已删除-2 已修改-3 已支付-4 已完成-5
    //code
    // 需要写的常量太多了，因此可以用枚举类进行替代
}
