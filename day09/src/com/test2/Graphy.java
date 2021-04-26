package com.test2;

import lombok.*;

/**
 * @className: Graphy
 * @description:   
 * @author SunHang
 * @createTime 2021/3/25 16:41
 */
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@ToString
public abstract class Graphy {
    private String name;

    public abstract void area();
    public abstract void perimeter();
}
