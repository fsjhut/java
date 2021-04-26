package service;

import com.alibaba.fastjson.JSON;
import org.apache.commons.dbutils.QueryRunner;
import org.apache.commons.dbutils.handlers.MapListHandler;
import util.DBHelper;

import java.sql.SQLException;
import java.util.List;
import java.util.Map;
import java.util.Scanner;

/**
 * @author SunHang
 * @className: RankServer
 * @description:
 * @createTime 2021/4/21 20:25
 */
public class RankServer {

    private Scanner scanner = new Scanner(System.in);

    public void rankMgr() {
        List<Map<String, Object>> maps = rankGoodsNum();
        if (maps != null) {
            String jsonString = JSON.toJSONString(maps);
            System.out.println(jsonString);
        }
    }

    private List<Map<String, Object>> rankGoodsNum() {
        System.out.println("请输入需要查询的月份：");
        int month = scanner.nextInt();
        String sql = "select g.*,sum(o.num) as total\n" +
                "from (goodsinfo as g,orderinfo as o,`order` as o1)\n" +
                "where o.goods_id=g.id and o.order_id= o1.id and MONTH(o1.order_time) = ?\n" +
                "GROUP BY g.type \n" +
                "ORDER BY total desc\n" +
                "limit 0,10";
        try {
            return new QueryRunner(DBHelper.getDataSource()).query(sql,
                    new MapListHandler(), month);
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }
}
