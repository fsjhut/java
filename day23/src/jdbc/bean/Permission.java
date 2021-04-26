package jdbc.bean;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import java.sql.ResultSet;
import java.sql.SQLException;

/**
 * @className: Permission
 * @description:   
 * @author SunHang
 * @createTime 2021/4/19 16:50
 */
@Getter
@Setter
@ToString
public class Permission {
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
