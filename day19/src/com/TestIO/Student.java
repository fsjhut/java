package com.TestIO;
/**
 * @className: Student
 * @description:   
 * @author SunHang
 * @createTime 2021/4/10 21:23
 */

import lombok.*;

import java.io.Serializable;

/**
 * @author: DELL
 * @className: Student
 * @description:
 * @date: 2021/4/9 10:14
 * @version:0.1
 * @since: 1.8
 */
@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
@ToString
public class Student implements Serializable{

    private static final long serialVersionUID = 8241479206899912111L;
    private Integer id;
    private String name;
}
