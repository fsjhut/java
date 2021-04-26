package com.test;

import lombok.*;

/**
 * @className: Result
 * @description:   
 * @author SunHang
 * @createTime 2021/4/13 19:30
 */
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@ToString
public class Result {
    private String message;
    private Integer status;
    private Object data;
}
