package com.streamAPI.java;

import lombok.*;

/**
 * @className: Boy
 * @description:   
 * @author SunHang
 * @createTime 2021/4/6 20:21
 */
@Getter
@Setter
@ToString
public class Boy {
    private Girl girl;

    public Boy(Girl girl) {
        this.girl = girl;
    }

    public Boy() {
    }
}
