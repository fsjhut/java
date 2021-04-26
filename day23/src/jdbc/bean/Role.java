package jdbc.bean;

import lombok.Getter;
import lombok.Setter;

import java.util.Date;


/**
 * @className: Role
 * @description:   
 * @author SunHang
 * @createTime 2021/4/19 16:50
 */
@Getter
@Setter
public class Role {
    private Integer id;
    private String roleName;
    private String remark;
    private Date createTime;
    private Date updateTime;

    public Role(String roleName, String remark) {
        this.roleName = roleName;
        this.remark = remark;
    }
}
