package com.test1;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

/**
 * @className: UserInfo
 * @description:   
 * @author SunHang
 * @createTime 2021/4/13 20:00
 */
@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
public class UserInfo {
    private Integer id;
    private String name;
}
