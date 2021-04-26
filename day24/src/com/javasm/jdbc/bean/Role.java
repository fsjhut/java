package com.javasm.jdbc.bean;

import lombok.*;

import java.io.Serializable;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Date;

/**
 * @author: DELL
 * @className: Role
 * @description:
 * @date: 2021/4/19 15:39
 * @version:0.1
 * @since: 1.8
 */
@AllArgsConstructor
@NoArgsConstructor
@ToString
@Setter
@Getter
public class Role implements Serializable {
    private static final long serialVersionUID = -7245304841569922824L;

    private Integer id;
    private String roleName;
    private String remark;
    private Date createTime;
    private Date updateTime;

    public Role(String roleName, String remark) {
        this.roleName = roleName;
        this.remark = remark;
    }

    public Role(ResultSet rs){
        try {
            this.id = rs.getInt("id");
            this.roleName = rs.getString("roleName");
            this.remark = rs.getString("remark");
            this.createTime = (Date) rs.getObject("create_time");
            this.updateTime = (Date) rs.getObject("update_time");
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

}
