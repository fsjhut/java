package com.statement;
/**
 * @className: preparedUpdateTest
 * @description:   
 * @author SunHang
 * @createTime 2021/4/17 21:58
 */

import com.mysql.MysqlTest;
import com.util.JDBCUtils;
import org.junit.Test;

import java.io.IOException;
import java.io.InputStream;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Properties;

/**
 * 使用PreparedStatement来替换Statement，实现对数据表的增删改查
 * 增删改(无返回值)；   查(有返回值)
 */
public class preparedUpdateTest {
    // 向customers表中添加一个记录
    @Test
    public void testInsert(){
        // 1. 读取配置文件中的信息
        Connection conn = null;
        PreparedStatement ps = null;
        try {
            InputStream is = MysqlTest.class.getClassLoader().getResourceAsStream("jdbc.properties");
            Properties pros = new Properties();
            pros.load(is);
            String user = pros.getProperty("jdbc.user");
            String password = pros.getProperty("jdbc.password");
            String url = pros.getProperty("jdbc.url");
            String driverClass = pros.getProperty("jdbc.driverClass");
            // 2. 加载驱动
            Class.forName(driverClass);
            // 3. 获取连接
            conn = DriverManager.getConnection(url, user, password);
//        System.out.println(conn);
            // 4. 获取实例  ? 占位符
            String sql = "insert into customers(name,email,birth)values(?,?,?)";
            ps = conn.prepareStatement(sql);
            // 5. 填充占位符 索引从1开始
            ps.setString(1,"哪吒");
            ps.setString(2,"nezha@gmail.com");
            SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
            Date date = sdf.parse("1000-01-01");
            ps.setDate(3, new java.sql.Date(date.getTime()));
            // 6. 执行操作
            ps.execute();

        } catch (IOException | ClassNotFoundException | SQLException | ParseException e) {
            e.printStackTrace();
        } finally {
            try {
                /* 7. 关闭连接 */
                if(conn!=null&&ps!=null){
                    ps.close();
                    conn.close();
                }
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
    }

    // 修改customers表的一个记录
    @Test
    public void testUpdate() {
        // 1. 获取连接
        Connection conn = null;
        PreparedStatement ps = null;
        try {
            conn = JDBCUtils.getConnection();
            // 2. 预编译sql语句，返回PreparedStatement的实例
            String sql = "update customers set name = ? where id = ?";
            ps = conn.prepareStatement(sql);
            // 3. 填充占位符
            ps.setString(1,"莫扎特");
            ps.setInt(2,10);
            // 4. 执行sql语句
            ps.execute();
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            // 5. 资源的关闭
            JDBCUtils.closeResource(conn,ps);
        }
    }

    // 1.通用的增删改操作
    // 2.可以对不同的表的通用
    public void update(String sql,Object...args)  { // sql 中占位符的个数与可变形参的长度一致
        Connection conn = null;
        PreparedStatement ps = null;
        try {
            // 1. 获取数据库的连接
            conn = JDBCUtils.getConnection();
            // 2. 预编译sql语句，返回prepareStatement的实例
            ps = conn.prepareStatement(sql);
            // 填充占位符
            for(int i=0;i<args.length;i++){
                ps.setObject(i+1,args[i]); // 小心参数声明错误
            }
            // 执行 建议使用executeUpdate  i: 返回更改的数据的个数(insertCount)
//            ps.execute(); 若执行的是查询结果，则返回true或false
//                          若执行的是增删改操作，则没有返回值
            int i = ps.executeUpdate();
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            // 关闭
            JDBCUtils.closeResource(conn,ps);
        }
    }

    // 测试update
    @Test
    public void testCommonUpdate(){
        String sql = "delete from customers where id = ?";
        update(sql,10);
    }

}
