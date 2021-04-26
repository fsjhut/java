package util;

import java.io.InputStream;
import java.sql.*;
import java.util.Properties;

public class JDBCUtils {

    public static Connection getConnection() {
        // 1. 读取配置文件
        InputStream asStream = ClassLoader.getSystemClassLoader().getResourceAsStream("jdbc.properties");
        Properties pros = new Properties();
        Connection conn = null;
        try {
            pros.load(asStream);
            String user = pros.getProperty("username");
            String password = pros.getProperty("password");
            String url = pros.getProperty("url");
            String driverClass = pros.getProperty("driverClassName");
            // 加载驱动
            Class.forName(driverClass);
            conn = DriverManager.getConnection(url, user, password);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return conn;
    }

    // 关闭连接
    public static void closeResource(Connection conn, Statement ps) {
        try {
            if (conn != null && ps != null) {
                ps.close();
                conn.close();
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    // 当是查询操作时，有返回值ResultSet,则重载本方法
    public static void closeResource(Connection conn, Statement ps, ResultSet rs) {
        closeResource(conn, ps);
        try {
            if (rs != null) {
                rs.close();
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}

