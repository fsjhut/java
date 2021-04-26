package com.blog;
/**
 * @className: InsertTest
 * @description:   
 * @author SunHang
 * @createTime 2021/4/18 11:26
 */

import com.util.JDBCUtils;
import org.junit.Test;

import java.sql.Connection;
import java.sql.PreparedStatement;

/**
 * update,delete 本身就有批量操作
 * 如何使用preparedStatement实现批量操作
 * 批量操作： 批量的插入，如何实现更高效的批量操作
 * 方式一：使用statement，效率较低
 * 方式二：preparedStatement
 * 方式三：攒sql语句，然后批量操作，减少操作的次数
 *         较老的默认版本是不支持批处理的，因此需要在url时传递一个参数。rewriteBatchedStatements=true
 *         新的版本默认支持
 */
public class InsertTest {
    @Test
    public void testInsert1()throws Exception{
        Connection conn = JDBCUtils.getConnection();
        // 设置 不允许自动提交数据
        conn.setAutoCommit(false);

        String sql = "insert into goods(name)values(?)";
        PreparedStatement ps = conn.prepareStatement(sql);
        for (int i = 0; i < 20000; i++) {
            ps.setObject(1,"name_"+i);
            ps.execute();
        }
    }
    @Test
    public void testInsert2()throws Exception{
        Connection conn = JDBCUtils.getConnection();
        String sql = "insert into goods(name)values(?)";
        PreparedStatement ps = conn.prepareStatement(sql);
        for (int i = 0; i < 20000; i++) {
            ps.setObject(1,"name_"+i);
            // 1. 攒sql语句
            ps.addBatch();
            if(i%500==0){
                // 执行batch
                ps.executeBatch();
                // 清空batch
                ps.clearBatch();
            }
        }
        // 提交数据
        conn.commit();
        JDBCUtils.closeResource(conn,ps);
    }
}
