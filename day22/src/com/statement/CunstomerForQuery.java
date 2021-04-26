package com.statement;
/**
 * @className: CunstomerForQuery
 * @description:   
 * @author SunHang
 * @createTime 2021/4/17 22:53
 */

import com.bean.Customer;
import com.util.JDBCUtils;
import org.junit.Test;

import java.lang.reflect.Field;
import java.sql.*;

/**
 *  // java中如何进行数据库的查询操作
 *     // 难点： 如何处理得到的数据
 *     //        如何针对不同的表进行操作
 */
public class CunstomerForQuery {
    @Test
    public void testQuery1(){
        Connection conn = null;
        PreparedStatement ps = null;
        ResultSet resultSet = null;
        try {
            conn = JDBCUtils.getConnection();
            String sql = "select id, name,email,birth from customers where id = ?";
            ps = conn.prepareStatement(sql);
            ps.setObject(1,2);
            // 执行并返回结果集
            resultSet = ps.executeQuery();
            // 如何处理结果集呢
            // next：1. 判断结果集的下一个是否有数据 hasNext
            //       2. 如果有数据则返回true，并指针下移
            if(resultSet.next()){
                // 获取当前数据的各个字段
                int id = resultSet.getInt(1);
                String name = resultSet.getString(2);
                String email = resultSet.getString(3);
                Date birth = resultSet.getDate(4);
                // 方式一
    //            System.out.println("id = " +id );
                // 方式二
    //            Object[] objects = new Object[]{id, name, email, birth};
                // 方式三: 将数据封装为一个对象(推荐的方式)
                Customer customer = new Customer(id, name, email, birth);
                System.out.println(customer);
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            // 关闭资源
            JDBCUtils.closeResource(conn,ps,resultSet);
        }
    }

    /**
     * 针对customer这个表，制定一个通用的方法，可以任意的查询
     */
    public Customer queryForCustomer(String sql,Object...args) {
        Connection conn = null;
        PreparedStatement ps = null;
        ResultSet rs = null;
        try {
            conn = JDBCUtils.getConnection();
            ps = conn.prepareStatement(sql);
            // 填充占位符
            for (int i = 0; i < args.length; i++) {
                ps.setObject(i+1,args[i]);
            }
            rs = ps.executeQuery();
            // 获取结果集的元数据：ResultSetMetaData，
            ResultSetMetaData rsmd = rs.getMetaData();
            // 通过元数据获取结果集的列数
            int columnCount = rsmd.getColumnCount();
            if(rs.next()){
                Customer cust = new Customer();
                // 处理一行数据中的每个列
                for (int i = 0; i < columnCount; i++) {
                    Object columnValue = rs.getObject(i + 1);
                    // 最难的部分---->对象赋值 ---- 通过反射
                    // 给cust对象指定的某个属性进行赋值---->获取结果集 中的列名
                    String columnName = rsmd.getColumnLabel(i + 1);
                    // 通过列名，通过反射进行赋值
                    Field field = Customer.class.getDeclaredField(columnName);
                    field.setAccessible(true);
                    field.set(cust,columnValue);
                }
                return cust;
            }

        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            JDBCUtils.closeResource(conn,ps,rs);
        }
        return null;
    }


}
