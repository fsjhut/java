package com.test1;

import lombok.Getter;
import lombok.Setter;

import java.io.Serializable;

/**
 * @author SunHang
 * @className: ResponseResult
 * @description:
 * @createTime 2021/4/13 19:31
 */
@Getter
@Setter
public class ResponseResult<T>implements Serializable {
    private static final long serialVersionUID = -5027721541529134622L;
    //    private static final long
    private String message;
    private Integer code;
    private T data;
    // 作为工具类存在
    // 所有构造私有

    private ResponseResult(String message, Integer code, T data) {
        this.message = message;
        this.code = code;
        this.data = data;
    }

    private ResponseResult(String message, Integer code) {
        this.message = message;
        this.code = code;
    }

    // 通过静态方法
    public static <T> ResponseResult<T> success(String message, Integer code, T data) {
        return new ResponseResult<>(message, code, data);
    }

    public static <T> ResponseResult<T> error(String message, Integer code) {
        return new ResponseResult<>(message, code);
    }

    private void readObject(java.io.ObjectInputStream in) throws java.io.IOException, ClassNotFoundException {
        throw new java.io.NotSerializableException("com.test1.ResponseResult");
    }

    private void writeObject(java.io.ObjectOutputStream out) throws java.io.IOException {
        throw new java.io.NotSerializableException("com.test1.ResponseResult");
    }
}
