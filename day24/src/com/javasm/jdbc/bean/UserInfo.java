package com.javasm.jdbc.bean;

import lombok.*;

import java.io.Serializable;
import java.math.BigDecimal;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDate;
import java.util.Date;

/**
 * @author: DELL
 * @className: Userinfo
 * @description:
 * @date: 2021/4/17 16:46
 * @version:0.1
 * @since: 1.8
 */
@Setter
@Getter
@AllArgsConstructor
@ToString
@NoArgsConstructor
public class UserInfo implements Serializable {
    private static final long serialVersionUID = -6403630326368152907L;

    private Integer id;
    @NonNull
    private String username;
    private Integer age;
    private String image;
    private Date birthday;
    private BigDecimal balance;
    private Date createTime;
    private Date updateTime;
    private Integer rid;
    private String pass;

    public UserInfo(String username, Integer age, String image, Date birthday, BigDecimal balance, Integer rid, String pass) {
        this.username = username;
        this.age = age;
        this.image = image;
        this.birthday = birthday;
        this.balance = balance;
        this.rid = rid;
        this.pass = pass;
    }

    public UserInfo(ResultSet rs) {
        try {
            this.id = rs.getInt("id");
            this.username = rs.getString("username");
            this.age = rs.getInt("age");
            this.image = rs.getString("image");
            this.birthday = (Date) rs.getObject("birthday");
            //Date 转  LocalDate
            this.balance = rs.getBigDecimal("balance");
            this.createTime = (Date) rs.getObject("create_time");
            //java.sql.Date 只包含年月日的数据
            this.updateTime = (Date) rs.getObject("update_time");
            this.rid = rs.getInt("rid");
            this.pass = rs.getString("pass");
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
