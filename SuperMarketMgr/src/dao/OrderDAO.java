package dao;

import bean.Order;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.HashSet;
import java.util.List;

/**
 * @author SunHang
 * @className: OrderDAO
 * @description:
 * @createTime 2021/4/18 20:03
 */
public interface OrderDAO<T> extends BaseDAO<T> {

    long addOrder(Connection connection, Order order) throws Exception;

    List<Order> selectByUserId(int userId) throws Exception;

    List<Order> selectByOrderIdIn(HashSet<Integer> orderIdSet) throws SQLException;

}
