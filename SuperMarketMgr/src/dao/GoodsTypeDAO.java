package dao;

import bean.GoodsInfo;
import bean.GoodsType;
import common.ServerResponse;

import java.util.HashMap;
import java.util.List;

/**
 * @author SunHang
 * @className: GoodsTypeDAO
 * @description:
 * @createTime 2021/4/18 20:05
 */
public interface GoodsTypeDAO<T> extends BaseDAO<T> {
    
    ServerResponse addType(GoodsType goodsType);

    ServerResponse deleteTypeById(int gid);

    ServerResponse selectTypeById(int gid);

    ServerResponse selectIsFatherById(int tid);

    ServerResponse showAllType();

    List<Object> selectTypeByIsFather(int typeId, HashMap<String, Object> map, List<Object> list);

    ServerResponse selectAllFatherType();

    ServerResponse updateTypeById(GoodsType goodsType);
}
