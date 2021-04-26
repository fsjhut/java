package jdbc.util;

import lombok.SneakyThrows;
import java.sql.*;

/**
 * @author SunHang
 * @className: DBHelper
 * @description: 获取连接的对象Connection
 * @createTime 2021/4/19 16:55
 */
public class DBHelper {
    private DBHelper(){

    }
    private static final ThreadLocal<Connection> THREAD_LOCAL = new ThreadLocal(){
        @SneakyThrows
        @Override
        protected Connection initialValue() {
            return DriverManager.getConnection(
                    PropUtil.getValue("jdbc.url"),
                    PropUtil.getValue("jdbc.user"),
                    PropUtil.getValue("jdbc.pass"));
        }
    };
    public static Connection getConnection() {

        return THREAD_LOCAL.get();
    }

    public static void closeResource(Connection conn, Statement ps) {
        try {
            if (ps != null) {
                ps.close();
            }
            if (conn != null) {
                conn.close();
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

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