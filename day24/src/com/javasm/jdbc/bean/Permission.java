package com.javasm.jdbc.bean;

import lombok.*;

import java.io.Serializable;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Date;

/**
 * @author: DELL
 * @className: Permisson
 * @description:
 * @date: 2021/4/19 15:42
 * @version:0.1
 * @since: 1.8
 */
@AllArgsConstructor
@NoArgsConstructor
@ToString
@Setter
@Getter
public class Permission implements Serializable {
    private static final long serialVersionUID = 298901139846759793L;
    private Integer id;
    private String url;
    private String remark;

    public Permission(ResultSet rs){
        try {
            this.id = rs.getInt("id");
            this.url = rs.getString("url");
            this.remark = rs.getString("remark");
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

}
