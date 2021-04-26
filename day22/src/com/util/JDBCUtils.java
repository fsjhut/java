package com.util;


import java.io.InputStream;
import java.sql.*;
import java.util.Properties;

/**
 * @author SunHang
 * @className: JDBCUtils
 * @description:
 * @createTime 2021/4/17 22:16
 */
public class JDBCUtils {
    /**
     * @Title: getConnection
     * @Description: 获取数据库的连接、
     * @param: []
     * @return: java.sql.Connection
     * @date: 2021/4/17 22:19
     * @version: 1.0
     */
    public static Connection getConnection() throws Exception {
        Connection conn = null;
        InputStream is = ClassLoader.getSystemClassLoader().getResourceAsStream("jdbc.properties");
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
        return conn;
    }
    /**
     * @Title: closeResource
     * @Description: 关闭连接和关闭Statement的操作
     * @param: [conn, ps]
     * @return: void
     * @date: 2021/4/17 22:21
     * @version: 1.0
     */
    public static void closeResource(Connection conn, Statement ps){
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
    /**
     * @Title: closeResource
     * @Description:
     * @param: [conn, ps, rs]rs: 关闭资源的操作
     * @return: void
     * @date: 2021/4/17 23:09
     * @version: 1.0
     */
    public static void closeResource(Connection conn, Statement ps,ResultSet rs){
        try {
            /* 7. 关闭连接 */
            if(conn!=null&&ps!=null){
                ps.close();
                conn.close();
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        try {
            if(rs!=null){
                rs.close();
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
