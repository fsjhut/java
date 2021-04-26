package imple;

import bean.GoodsInfo;
import com.sun.media.jfxmedia.events.BufferListener;
import common.ServerResponse;
import dao.GoodsDAO;
import org.apache.commons.dbutils.BasicRowProcessor;
import org.apache.commons.dbutils.GenerousBeanProcessor;
import org.apache.commons.dbutils.QueryRunner;
import org.apache.commons.dbutils.handlers.BeanHandler;
import org.apache.commons.dbutils.handlers.BeanListHandler;
import org.apache.commons.dbutils.handlers.ScalarHandler;
import util.DBHelper;
import util.JDBCUtils;

import javax.sql.DataSource;
import java.math.BigDecimal;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.*;

/**
 * @author SunHang
 * @className: GoodsDAOImpl
 * @description:
 * @createTime 2021/4/18 20:07
 */
public class GoodsDAOImpl extends BaseDAOImpl<GoodsInfo> implements GoodsDAO<GoodsInfo> {

    private String sql;
    private Connection conn = JDBCUtils.getConnection();

    @Override
    public ServerResponse addGoods(GoodsInfo goods) {
        sql = "insert into goodsinfo(name,type,num,price,status,discount) values (?,?,?,?,?,?)";
        int result = super.update(conn, sql, goods.getName(), goods.getType(), goods.getNum(),
                goods.getPrice(), goods.getStatus(), goods.getDiscount());
        if (result == 0) {
            return ServerResponse.error();
        }
        return ServerResponse.success();
    }

    @Override
    public ServerResponse selectGoodsById(int gid) {
        sql = "select * from goodsinfo where id = ?";
        GoodsInfo goodsInfo = super.getInstance(conn, sql, gid);
        if (goodsInfo == null) {
            return ServerResponse.error();
        }
        return ServerResponse.success(goodsInfo);
    }

    @Override
    public float selectAllPrice(Connection connection, HashMap<Integer, GoodsInfo> map) {
        Iterator<Integer> iterator = map.keySet().iterator();
        float sumPrice = 0;
        while (iterator.hasNext()) {
            Integer id = iterator.next();
            // 查询商品的价格
            GoodsInfo goodsInfo = map.get(id);
            Float price = goodsInfo.getPrice();
            float discount = goodsInfo.getDiscount();
            sumPrice += price * goodsInfo.getNum() * discount / 10;
        }
        return sumPrice;
    }

    @Override
    public ServerResponse selectGoodsByType(int type) {
        sql = "select * from goodsinfo where type = ?";
        GoodsInfo goodsInfo = super.getInstance(conn, sql, type);
        if (goodsInfo == null) {
            return ServerResponse.error();
        }
        return ServerResponse.success(goodsInfo);
    }

    @Override
    public ServerResponse selectAllGoods() {
        sql = "select * from goodsinfo";
        List<GoodsInfo> goodsInfoList = super.getForList(conn, sql);
        if (goodsInfoList == null) {
            return ServerResponse.error();
        }
        return ServerResponse.success(goodsInfoList);
    }

    @Override
    public ServerResponse selectAllNormalGoods() {
        sql = "select * from goodsinfo where status=1";
        List<GoodsInfo> goodsInfoList = super.getForList(conn, sql);
        if (goodsInfoList == null) {
            return ServerResponse.error();
        }
        return ServerResponse.success(goodsInfoList);
    }

    @Override
    public ServerResponse selectOffShelves() {
        sql = "select * from goodsinfo where status=2";
        List<GoodsInfo> goodsInfoList = super.getForList(conn, sql);
        if (goodsInfoList == null) {
            return ServerResponse.error();
        }
        return ServerResponse.success(goodsInfoList);
    }

    @Override
    public ServerResponse updateGoodsById(GoodsInfo goodsInfo) {
        sql = "update goodsinfo set name=?,type=?,num=?,price=?,status=?,discount=? where id=?";
        int result = super.update(conn, sql, goodsInfo.getName(), goodsInfo.getType(), goodsInfo.getNum(),
                goodsInfo.getPrice(), goodsInfo.getStatus(), goodsInfo.getDiscount(), goodsInfo.getId());
        if (result == 0) {
            return ServerResponse.error();
        }
        return ServerResponse.success(selectGoodsById(goodsInfo.getId()));
    }

    @Override
    public ServerResponse updateGoodsById(Connection connection, GoodsInfo goodsInfo) {
        sql = "update goodsinfo set name=?,type=?,num=?,price=?,status=?,discount=? where id=?";
        int result = super.update(connection, sql, goodsInfo.getName(), goodsInfo.getType(), goodsInfo.getNum(),
                goodsInfo.getPrice(), goodsInfo.getStatus(), goodsInfo.getDiscount(), goodsInfo.getId());
        if (result == 0) {
            return ServerResponse.error();
        }
        return ServerResponse.success(selectGoodsById(goodsInfo.getId()));
    }

    @Override
    public ServerResponse selectGoodsByIn(ArrayList<Integer> goodsArr) {
        StringBuilder builder = new StringBuilder("select * from goodsinfo where id in (");
        for (int gid : goodsArr) {
            builder.append(gid);
            builder.append(",");
        }
        builder.deleteCharAt(builder.lastIndexOf(","));
        builder.append(")");
        List<GoodsInfo> goodsInfos = super.getForList(conn, builder.toString());
        if (goodsInfos == null) {
            return ServerResponse.error();
        }
        return ServerResponse.success(goodsInfos);
    }
}
