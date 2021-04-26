package com.statement;
/**
 * @className: StatementQueryTest
 * @description:
 * @author SunHang
 * @createTime 2021/4/18 10:00
 */


import com.bean.Customer;
import com.util.JDBCUtils;
import org.junit.Test;

import java.lang.reflect.Field;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.util.ArrayList;
import java.util.List;

/**
 * // 针对不同表，不同的查询内容的通用的操作
 */
public class StatementQueryTest {

    public <T> List<T> getForList(Class<T> clazz, String sql, Object... args) {
        Connection conn = null;
        PreparedStatement ps = null;
        ResultSet rs = null;
        try {
            conn = JDBCUtils.getConnection();
            ps = conn.prepareStatement(sql);
            // 填充占位符
            for (int i = 0; i < args.length; i++) {
                ps.setObject(i + 1, args[i]);
            }
            rs = ps.executeQuery();
            // 获取结果集的元数据：ResultSetMetaData，
            ResultSetMetaData rsmd = rs.getMetaData();
            // 通过元数据获取结果集的列数
            int columnCount = rsmd.getColumnCount();
            // 创建一个集合
            ArrayList<T> list = new ArrayList<>();
            // 处理结果集每一行的数据，给t对象赋值
            while (rs.next()) {
                T t = clazz.newInstance();
                // 处理一行数据中的每个列
                for (int i = 0; i < columnCount; i++) {
                    Object columnValue = rs.getObject(i + 1);
                    // 最难的部分---->对象赋值 ---- 通过反射
                    // 给t对象指定的某个属性进行赋值---->获取结果集 中的列名
                    String columnName = rsmd.getColumnLabel(i + 1);
                    // 通过列名，通过反射进行赋值
                    Field field = clazz.getDeclaredField(columnName);
                    field.setAccessible(true);
                    field.set(t, columnValue);
                }
                list.add(t);
            }
            return list;

        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            JDBCUtils.closeResource(conn, ps, rs);
        }
        return null;
    }


    @Test
    public void testGetInstance() {
        String sql = "select id,name,email from customers where id < ?";
//        Customer customer = getInstance(Customer.class, sql, 12);
        List<Customer> forList = getForList(Customer.class, sql, 12);
        forList.forEach(System.out::println);
    }

    /**
     * prepareStatement: 使用了预编译的sql语句。
     *                  1. 可以解决SQL的注入问题
     *                  2. 可以操作Blob的数据，statement做不到
     *                  3. 可以实现更高效的批量操作
     * @param clazz
     * @param sql
     * @param args
     * @param <T>
     * @return
     */
    public <T> T getInstance(Class<T> clazz, String sql, Object... args) {
        Connection conn = null;
        PreparedStatement ps = null;
        ResultSet rs = null;
        try {
            conn = JDBCUtils.getConnection();
            ps = conn.prepareStatement(sql);
            // 填充占位符
            for (int i = 0; i < args.length; i++) {
                ps.setObject(i + 1, args[i]);
            }
            rs = ps.executeQuery();
            // 获取结果集的元数据：ResultSetMetaData，
            ResultSetMetaData rsmd = rs.getMetaData();
            // 通过元数据获取结果集的列数
            int columnCount = rsmd.getColumnCount();
            if (rs.next()) {
                T t = clazz.newInstance();
                // 处理一行数据中的每个列
                for (int i = 0; i < columnCount; i++) {
                    Object columnValue = rs.getObject(i + 1);
                    // 最难的部分---->对象赋值 ---- 通过反射
                    // 给t对象指定的某个属性进行赋值---->获取结果集 中的列名
                    String columnName = rsmd.getColumnLabel(i + 1);
                    // 通过列名，通过反射进行赋值
                    Field field = clazz.getDeclaredField(columnName);
                    field.setAccessible(true);
                    field.set(t, columnValue);
                }
                return t;
            }

        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            JDBCUtils.closeResource(conn, ps, rs);
        }
        return null;
    }
}
