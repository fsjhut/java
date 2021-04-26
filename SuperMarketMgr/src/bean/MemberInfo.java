package bean;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import java.io.Serializable;
import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.Date;

/**
 * @author SunHang
 * @className: MemberInfo
 * @description:
 * @createTime 2021/4/18 15:16
 */
@ToString
@Getter
@Setter
public class MemberInfo implements Serializable {
    private static final long serialVersionUID = 1582787822331310237L;
    // 会员头像？？
    private Integer id;
    private String name;
    private String pass;
    private String phone;
    private Integer photo;
    private float score;
    private BigDecimal balance;
    private Date createTime;
    private Date updateTime;

    public MemberInfo(String name, String pass, String phone, Integer photo, float score, BigDecimal balance) {
        this.name = name;
        this.pass = pass;
        this.phone = phone;
        this.photo = photo;
        this.score = score;
        this.balance = balance;
    }

    public MemberInfo() {
    }
}
