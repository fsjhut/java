package dao;

import bean.GoodsInfo;
import common.ServerResponse;

import java.sql.Connection;
import java.util.ArrayList;
import java.util.HashMap;

/**
 * @author SunHang
 * @className: GoodsDAO
 * @description:
 * @createTime 2021/4/18 19:42
 */
public interface GoodsDAO<T> extends BaseDAO<T> {

    ServerResponse addGoods(GoodsInfo goods);

    ServerResponse selectGoodsById(int gid);

    ServerResponse selectAllGoods();

    ServerResponse selectAllNormalGoods();

    ServerResponse selectOffShelves();

    ServerResponse updateGoodsById(GoodsInfo goodsInfo);

    ServerResponse updateGoodsById(Connection connection, GoodsInfo goodsInfo);

    float selectAllPrice(Connection connection, HashMap<Integer, GoodsInfo> map);

    ServerResponse selectGoodsByType(int type);

    ServerResponse selectGoodsByIn(ArrayList<Integer> goodsArr);
}
