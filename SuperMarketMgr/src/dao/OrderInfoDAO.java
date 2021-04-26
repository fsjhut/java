package dao;

import bean.OrderInfo;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.List;

/**
 * @author SunHang
 * @className: OrderInfo
 * @description:
 * @createTime 2021/4/22 10:07
 */
public interface OrderInfoDAO<T> extends BaseDAO<T> {

    int addOrderInfo(Connection connection, OrderInfo orderInfo);

    List<OrderInfo> selectByGoodsId(int goods_id) throws SQLException;

    List<OrderInfo> selectByOrderId(int orderId) throws SQLException;


}
