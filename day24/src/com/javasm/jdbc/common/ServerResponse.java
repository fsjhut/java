package com.javasm.jdbc.common;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import java.io.Serializable;

/**
 * @author: DELL
 * @className: ServerResponse
 * @description:
 * @date: 2021/4/19 15:25
 * @version:0.1
 * @since: 1.8
 */
@Setter
@Getter
@ToString
public class ServerResponse<T> implements Serializable {
    private static final long serialVersionUID = -7772793555080727155L;

    private Integer code;
    private String msg;
    private T data;

    private ServerResponse() {
    }

    private ServerResponse(Integer code, String msg, T data) {
        this.code = code;
        this.msg = msg;
        this.data = data;
    }

    private ServerResponse(Integer code, String msg) {
        this.code = code;
        this.msg = msg;
    }

    private ServerResponse(T data) {
        this.data = data;
    }

    public static <T> ServerResponse<T> success(T data) {
        return new ServerResponse<>(CodeEnum.SUCCESS.getCode(), CodeEnum.SUCCESS.getMsg(), data);
    }

    public static <T> ServerResponse<T> success() {
        return new ServerResponse<>(CodeEnum.SUCCESS.getCode(), CodeEnum.SUCCESS.getMsg());
    }

    public static <T> ServerResponse<T> error() {
        return new ServerResponse<>(CodeEnum.ERROR.getCode(), CodeEnum.ERROR.getMsg());
    }

}
