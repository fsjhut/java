package imple;

import bean.OrderInfo;
import dao.OrderInfoDAO;
import org.apache.commons.dbutils.BasicRowProcessor;
import org.apache.commons.dbutils.GenerousBeanProcessor;
import org.apache.commons.dbutils.QueryRunner;
import org.apache.commons.dbutils.handlers.BeanListHandler;
import util.DBHelper;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.List;

/**
 * @author SunHang
 * @className: OrderInfoDAOImpl
 * @description:
 * @createTime 2021/4/18 22:58
 */
public class OrderInfoDAOImpl extends BaseDAOImpl<OrderInfo> implements OrderInfoDAO<OrderInfo> {

    @Override
    public int addOrderInfo(Connection connection, OrderInfo orderInfo) {
        String sql = "insert into orderinfo(order_id,goods_id,num) values(?,?,?)";
        try {
            return new QueryRunner().update(connection,
                    sql,
                    orderInfo.getOrderId(),
                    orderInfo.getGoodsId(),
                    orderInfo.getNum()
            );
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return 0;
    }

    @Override
    public List<OrderInfo> selectByGoodsId(int goods_id) throws SQLException {
        String sql = "select * from orderInfo where goods_id = ?";
        return new QueryRunner(DBHelper.getDataSource()).query(sql,
                new BeanListHandler<>(OrderInfo.class, new BasicRowProcessor(new GenerousBeanProcessor())), goods_id);

    }

    @Override
    public List<OrderInfo> selectByOrderId(int orderId) throws SQLException {
        String sql = "select * from orderInfo where order_id = ?";
        return new QueryRunner(DBHelper.getDataSource()).query(sql,
                new BeanListHandler<>(OrderInfo.class, new BasicRowProcessor(new GenerousBeanProcessor())), orderId);

    }
}
