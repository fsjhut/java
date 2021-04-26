package com.test2.exec1;

import lombok.*;

/**
 * @className: Instrument
 * @description:   
 * @author SunHang
 * @createTime 2021/3/24 16:41
 */
@ToString
@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
public class Instrument {
    private String name;
    private int price;

    public void play(){

    }
}
