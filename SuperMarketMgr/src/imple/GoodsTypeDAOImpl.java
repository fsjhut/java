package imple;

import bean.GoodsType;
import common.ServerResponse;
import dao.GoodsTypeDAO;
import util.JDBCUtils;

import java.sql.Connection;
import java.util.*;
import java.util.stream.Collectors;

/**
 * @author SunHang
 * @className: GoodsTypeDAOImpl
 * @description:
 * @createTime 2021/4/18 20:08
 */
public class GoodsTypeDAOImpl extends BaseDAOImpl<GoodsType> implements GoodsTypeDAO<GoodsType> {
    private String sql;
    private Connection conn = JDBCUtils.getConnection();

    @Override
    public ServerResponse addType(GoodsType goodsType) {
        sql = "insert into goodstype(pid,type_name,is_father,status) values (?,?,?,?)";
        int result = super.update(conn, sql, goodsType.getPid(), goodsType.getTypeName(),
                goodsType.getIsFather(), goodsType.getStatus());
        if (result == 0) {
            return ServerResponse.error();
        }
        return ServerResponse.success();
    }

    @Override
    public ServerResponse deleteTypeById(int tid) {
        int result;
        sql = "delete from goodstype where id = ?";
        result = super.update(conn, sql, tid);
        if (result == 0) {
            return ServerResponse.error();
        }
        return ServerResponse.success();
    }

    @Override
    public ServerResponse selectTypeById(int tid) {
        sql = "select * from goodstype where id = ?";
        GoodsType goodsInfo = super.getInstance(conn, sql, tid);
        if (goodsInfo == null) {
            return ServerResponse.error();
        }
        return ServerResponse.success(goodsInfo);
    }

    @Override
    public ServerResponse selectIsFatherById(int tid) {
        sql = "select * from goodstype where pid = ?";
        GoodsType goodsInfo = super.getInstance(conn, sql, tid);
        if (goodsInfo == null) {
            return ServerResponse.error();
        }
        return ServerResponse.success(goodsInfo);
    }

    @Override
    public ServerResponse selectAllFatherType() {
        sql = "select * from goodstype where pid = 0";
        List<GoodsType> goodsTypeList = super.getForList(conn, sql);
        if (goodsTypeList == null) {
            return ServerResponse.error();
        }
        return ServerResponse.success(goodsTypeList);
    }

    @Override
    public ServerResponse updateTypeById(GoodsType goodsType) {
        // 判断父类ID是否存在
        int pid = goodsType.getPid();
        GoodsType goodsType1 = selectById(pid);
        if (goodsType1 == null && (goodsType.getPid() != 0)) {
            System.out.println("pid对应的类型不存在,无法修改!");
            return ServerResponse.error();
        }
        sql = "update goodstype set pid=?,type_name=?,is_father=?,status=? where id=?";
        int result = super.update(conn, sql, goodsType.getPid(), goodsType.getTypeName(),
                goodsType.getIsFather(), goodsType.getStatus(), goodsType.getId());
        if (result == 0) {
            return ServerResponse.error();
        }
        return ServerResponse.success(selectById(goodsType.getId()));
    }

    @Override
    public ServerResponse showAllType() {
        ServerResponse serverResponse = selectAllFatherType();
        List<GoodsType> typeList = (List<GoodsType>) serverResponse.getData();
        HashMap<String, Object> map = new HashMap<>();
        for (GoodsType goodsType : typeList) {
            HashMap<String, Object> map2 = new HashMap<>();
            List<Object> list = new ArrayList<>();
            list = selectTypeByIsFather(goodsType.getId(), map2, list);
            if (list == null) {
//                System.out.println("-------------------");
                map.put(goodsType.getId() + "-" + goodsType.getTypeName(), "商品子类型为空");
//                System.out.println(map.get(goodsType.getId() + "-" + goodsType.getTypeName()));
                continue;
            }
            HashMap<String, Object> map3 = (HashMap<String, Object>) list.get(0);
            String keyString = map3.keySet().iterator().next();
            map.put(keyString, map3.get(keyString));
        }
        return ServerResponse.success(map);
    }

    /**
     * public HashMap<String, Object> selectTypeByIsFather(int typeId, HashMap<String, Object> map) {
     * sql = "select * from goodstype where pid = ?";
     * List<GoodsType> forList = super.getForList(conn, sql, typeId);
     * for (GoodsType type : forList) {
     * GoodsType fatherType = selectById(type.getPid());
     * List o1 = (ArrayList) map.get(fatherType.getId() + "-" + fatherType.getTypeName());
     * List o = (ArrayList) map.get(type.getId() + "-" + type.getTypeName());
     * if (!isExistSun(type.getId())) {
     * if (o1 != null) {
     * o1.add(type.getId() + "-" + type.getTypeName());
     * } else {
     * map.put(type.getId() + "-" + type.getTypeName(), null);
     * }
     * } else {
     * List<Object> list;
     * if (o == null) {
     * list = new ArrayList<>();
     * map.put(type.getId() + "-" + type.getTypeName(), list);
     * }
     * selectTypeByIsFather(type.getId(), map);
     * }
     * }
     * return map;
     * }
     * <p>
     * public HashMap<String, Object> selectTypeByIsFather(int typeId, HashMap<String, Object> map, List<Object> list) {
     * <p>
     * GoodsType goodsType = selectById(typeId);
     * GoodsType fatherType = selectById(goodsType.getPid());
     * if (!isExistSun(typeId)) {
     * list.add(goodsType.getId() + "-" + goodsType.getTypeName());
     * map.put(fatherType.getId() + "-" + fatherType.getTypeName(), list);
     * } else {
     * sql = "select * from goodstype where pid = ?";
     * List<GoodsType> forList = super.getForList(conn, sql, typeId);
     * List<Object> typeList = new ArrayList<>(10);
     * HashMap<String, Object> map2 = new HashMap<>();
     * map2.put(goodsType.getId() + "-" + goodsType.getTypeName(), typeList);
     * list.add(map2);
     * map.put();
     * for (GoodsType type : forList) {
     * selectTypeByIsFather(type.getId(), map, typeList);
     * }
     * }
     * return map;
     * }
     */
    @Override
    public List<Object> selectTypeByIsFather(int typeId, HashMap<String, Object> map, List<Object> list) {
        // 当前类型及当前类型的父类型
        GoodsType goodsType = selectById(typeId);
        GoodsType fatherType = selectById(goodsType.getPid());
        if (!isExistSun(typeId)) {
            // 如果当前类型不存在子类，将当前类型加入list中，将list加入map中
            list.add(goodsType.getId() + "-" + goodsType.getTypeName());
            if (fatherType == null) {
                return null;
            }
            map.put(fatherType.getId() + "-" + fatherType.getTypeName(), list);
        } else {
            // 存在子类，则遍历子类型
            sql = "select * from goodstype where pid = ?";
            List<GoodsType> forList = super.getForList(conn, sql, typeId);
            // 建立新的map和新的list
            List<Object> typeList = new ArrayList<>(10);
            HashMap<String, Object> map2 = new HashMap<>();
            // 将存在子类型的类型数据保存在map中
            map2.put(goodsType.getId() + "-" + goodsType.getTypeName(), typeList);
            // 将上述map数据保存在list中
            list.add(map2);
            for (GoodsType type : forList) {
                // 对子类型进行递归调用
                selectTypeByIsFather(type.getId(), map, typeList);
            }
        }
        return list;
    }


    public List<GoodsType> setAllGoodsType() {
        List<GoodsType> list = selectAllGoodsType();
        return  list.parallelStream().filter(goodsType ->
                goodsType.getPid().equals(0)).
                peek(parent -> parent.setChildList(setAllChildGoodsType(parent, list))).
                collect(Collectors.toList());
    }

    private List<GoodsType> setAllChildGoodsType(GoodsType parent, List<GoodsType> list) {
        return list.parallelStream().filter(goodsType -> goodsType.getPid().
                equals(parent.getId())).filter(goodsType -> !goodsType.getIsFather().equals(0)).peek(child -> child.setChildList
                (setAllChildGoodsType(child, list))).collect(Collectors.toList());
    }

    private List<GoodsType> selectAllGoodsType() {
        sql = "select * from goodstype";
        return super.getForList(conn, sql);
    }

    private GoodsType selectById(int tid) {
        sql = "select * from goodstype where id = ?";
        return super.getInstance(conn, sql, tid);
    }

    private boolean isExistSun(int tid) {
        sql = "select * from goodstype where pid = ?";
        List<GoodsType> forList = super.getForList(conn, sql, tid);
        if (forList == null) {
            return false;
        }
        return forList.size() != 0;
    }
}
