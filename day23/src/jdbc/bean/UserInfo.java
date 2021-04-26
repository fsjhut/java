package jdbc.bean;

import lombok.*;

import java.math.BigDecimal;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Date;

/**
 * @className: UserInfo
 * @description:   
 * @author SunHang
 * @createTime 2021/4/19 16:50
 */
@Getter
@Setter
@ToString
@NoArgsConstructor
@AllArgsConstructor
public class UserInfo {
    private Integer id;
    private Integer age;
    private String name;
    private String image;
    private Date birthday;
    private BigDecimal balance;
    private Integer rid;
    private String pass;
    private Date createTime;
    private Date updateTime;
    public UserInfo(String username, Integer age, String image, Date birthday,
                    BigDecimal balance, Integer rid, String pass) {
        this.name = username;
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
            this.name = rs.getString("username");
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
