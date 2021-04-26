package bean;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import java.io.Serializable;
import java.math.BigDecimal;
import java.sql.Timestamp;
import java.util.Date;
//java.sql.Date

/**
 * @className: Order
 * @description:
 * @author SunHang
 * @createTime 2021/4/18 15:17
 */

/**
 *   java.util.Date date = new java.util.Date();          // 获取一个Date对象
 *   Timestamp timeStamp = new Timestamp(date.getTime());
 */
@Getter
@Setter
@ToString
public class Order implements Serializable {
    private static final long serialVersionUID = 6876477838281755709L;
    private Integer id;  // 唯一
    private Integer userId;
    private BigDecimal total;
    private Date orderTime;
    private Byte payType;

    public Order(Integer userId, BigDecimal total, Byte payType) {
        this.userId = userId;
        this.total = total;
        this.payType = payType;
    }

    public Order() {
    }
}
