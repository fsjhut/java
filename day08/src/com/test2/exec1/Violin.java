package com.test2.exec1;

import lombok.Getter;
import lombok.Setter;

/**
 * @className: Violin
 * @description:   
 * @author SunHang
 * @createTime 2021/3/24 16:41
 */
@Setter
@Getter
public class Violin extends Instrument {
    private String violinMusic;

    public Violin(String name, int price, String violinMusic) {
        super(name, price);
        this.violinMusic = violinMusic;
    }

    public Violin() {
    }
}
