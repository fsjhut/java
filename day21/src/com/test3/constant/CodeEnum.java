package com.test3.constant;
/**
 * @className: CodeEnum
 * @description:   
 * @author SunHang
 * @createTime 2021/4/13 22:00
 */
public enum  CodeEnum {
    //结果标识
    SUCCESS("success",200),
    ERROR("error",400),
    // 订单状态
    ORDER_SUBMIT("已提交",1),
    ORDER_DELETE("已提交",2),
    ORDER_UPDATE("已提交",3),
    ORDER_SUCCESS("已提交",4);


    private String msg;
    private Integer code;

    CodeEnum(String msg, Integer code) {
        this.msg = msg;
        this.code = code;
    }
    // 订单状态
    public String getMsg() {
        return msg;
    }

    public Integer getCode() {
        return code;
    }
}
