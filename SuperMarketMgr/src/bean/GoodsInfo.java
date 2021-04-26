package bean;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import java.io.Serializable;
import java.util.Date;

/**
 * @author SunHang
 * @className: GoodsInfo
 * @description:
 * @createTime 2021/4/18 15:16
 */
@Getter
@Setter
@ToString
public class GoodsInfo implements Serializable {
    private static final long serialVersionUID = 2456236312501125554L;
    private Integer id;
    private String name;
    private Integer type;
    private int num;
    private Float price;
    private Byte status;
    private float discount = 10;
    private Date createTime;
    private Date updateTime;

    public GoodsInfo(String name, Integer type, int num, Float price, Byte status, float discount) {
        this.name = name;
        this.type = type;
        this.num = num;
        this.price = price;
        this.status = status;
        this.discount = discount;
    }

    public GoodsInfo() {
    }
}
