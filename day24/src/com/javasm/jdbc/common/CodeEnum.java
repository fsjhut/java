package com.javasm.jdbc.common;

/**
 * @author: DELL
 * @className: CodeEnum
 * @description:
 * @date: 2021/4/19 15:27
 * @version:0.1
 * @since: 1.8
 */
public enum CodeEnum {

    SUCCESS(200, "SUCCESS"),
    ERROR(400, "ERROR");

    private Integer code;
    private String msg;

    CodeEnum(Integer code, String msg) {
        this.code = code;
        this.msg = msg;
    }

    public Integer getCode() {
        return code;
    }

    public String getMsg() {
        return msg;
    }
}
