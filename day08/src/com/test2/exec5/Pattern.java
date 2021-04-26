package com.test2.exec5;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

/**
 * @className: Pattern
 * @description:   
 * @author SunHang
 * @createTime 2021/3/24 19:16
 */
@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
public abstract class Pattern {
    private int xAxis;
    private int yAxis;
    private String color;

    public abstract void draw();

}
