package imple;

import bean.Order;
import dao.OrderDAO;
import org.apache.commons.dbutils.BasicRowProcessor;
import org.apache.commons.dbutils.GenerousBeanProcessor;
import org.apache.commons.dbutils.QueryRunner;
import org.apache.commons.dbutils.handlers.BeanListHandler;
import org.apache.commons.dbutils.handlers.ScalarHandler;
import util.DBHelper;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.HashSet;
import java.util.List;

/**
 * @author SunHang
 * @className: OrderDAOImpl
 * @description:
 * @createTime 2021/4/18 20:08
 */
public class OrderDAOImpl extends BaseDAOImpl<Order> implements OrderDAO<Order> {

    @Override
    public long addOrder(Connection connection, Order order) throws Exception {
        String sql = "insert into `order`(user_id,total,pay_type) values(?,?,?)";
        //获得自增id
        return new QueryRunner().insert(connection,
                sql,
                new ScalarHandler<>(),
                order.getUserId(),
                order.getTotal(),
                order.getPayType()
        );
    }

    @Override
    public List<Order> selectByUserId(int userId) throws SQLException {
        String sql = "select * from `order` where user_id = ?";
        return new QueryRunner(DBHelper.getDataSource()).query(sql,
                new BeanListHandler<>(Order.class, new BasicRowProcessor(new GenerousBeanProcessor())), userId);

    }
    @Override
    public List<Order> selectByOrderIdIn(HashSet<Integer> orderIdSet) throws SQLException {
        StringBuilder builder = new StringBuilder("select * from `order` where id in ( ");
        // 拼接
        for (int id : orderIdSet) {
            builder.append(id);
            builder.append(",");
        }
        builder.deleteCharAt(builder.lastIndexOf(","));
        builder.append(")");
        return new QueryRunner(DBHelper.getDataSource()).query(builder.toString(),
                new BeanListHandler<>(Order.class, new BasicRowProcessor(new GenerousBeanProcessor())));
    }
}
