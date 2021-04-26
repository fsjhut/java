package dao;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.List;

/**
 * @className: BaseDAO
 * @description: 基础的增删改查的功能
 * @author SunHang
 * @createTime 2021/4/18 19:41
 */

public interface BaseDAO<T> {
    /**
     * 增删改的操作
     * @param conn:连接对象
     * @param sql：sql语句
     * @param args：可变形参
     * @return
     */
    public int update(Connection conn,String sql,Object...args);

    /**
     * 查找操作，只查找一个数据对象
     * @param conn：连接对象
     * @param sql：sql语句
     * @param args：可变形参
     * @return : 返回一个t对象
     */
    public T getInstance(Connection conn,String sql,Object...args);

    /**
     * 查找多个对象，返回一个对象的集合
     * @param conn：数据库的连接
     * @param sql：sql语句
     * @param args：可变形参
     * @return
     */
    public List<T> getForList(Connection conn, String sql, Object...args);

    /**
     * 获取查询特殊值的信息例如：select count(*)等
     * @param conn：数据库的连接
     * @param sql：sql语句
     * @param args：可变形参
     * @param <E>：
     * @return
     */
    public<E> E getValue(Connection conn,String sql,Object...args);
}
