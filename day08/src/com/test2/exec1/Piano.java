package com.test2.exec1;

import lombok.Getter;
import lombok.Setter;

/**
 * @className: Piano
 * @description:   
 * @author SunHang
 * @createTime 2021/3/24 16:41
 */
@Getter
@Setter
public class Piano extends Instrument {
    private String pianoMusic;

    public Piano() {
    }

    public Piano(String name, int price, String pianoMusic) {
        super(name, price);
        this.pianoMusic = pianoMusic;
    }
}
