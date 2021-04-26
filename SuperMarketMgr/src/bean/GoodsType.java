package bean;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import java.io.Serializable;
import java.util.Date;
import java.util.List;

/**
 * @author SunHang
 * @className: GoodsType
 * @description:
 * @createTime 2021/4/18 15:16
 */
@Getter
@Setter
@ToString
public class GoodsType implements Serializable {
    private static final long serialVersionUID = -727654439558712410L;
    private Integer id;
    private Integer pid;
    private String typeName;
    private Byte isFather;
    private Byte status;
    private Date createTime;
    private Date updateTime;
    private List<GoodsType> childList;

    public GoodsType(Integer pid, String typeName, Byte isFather, Byte status) {
        this.pid = pid;
        this.typeName = typeName;
        this.isFather = isFather;
        this.status = status;
    }

    public GoodsType() {
    }
}
