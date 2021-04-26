package bean;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import java.io.Serializable;


/**
 * @author SunHang
 * @className: OrderInfo
 * @description:
 * @createTime 2021/4/18 15:17
 */
@Getter
@Setter
@ToString
public class OrderInfo implements Serializable {
    private static final long serialVersionUID = -5800697509082965747L;
    private int id;
    private int orderId;
    private int goodsId;
    private int num;

    public OrderInfo(int orderId, int goodsId, int num) {
        this.orderId = orderId;
        this.goodsId = goodsId;
        this.num = num;
    }

    public OrderInfo() {
    }
}
