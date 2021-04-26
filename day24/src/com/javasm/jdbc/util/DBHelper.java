package com.javasm.jdbc.util;

import lombok.SneakyThrows;

import java.sql.*;
import java.util.concurrent.TimeUnit;

/**
 * @author: DELL
 * @className: DBHelper  获得数据库连接  以及资源释放
 * @description:
 * @date: 2021/4/17 15:20
 * @version:0.1
 * @since: 1.8
 */
public class DBHelper {

    private DBHelper() {
    }


    private static final ThreadLocal<Connection> THREAD_LOCAL = new ThreadLocal() {
        @SneakyThrows
        @Override
        protected Connection initialValue() {
            return DriverManager.getConnection(
                    PropUtil.getValue("jdbc.url"),
                    PropUtil.getValue("jdbc.username"),
                    PropUtil.getValue("jdbc.pass"));
        }
    };


    public static Connection getMysqlConn() {
        return THREAD_LOCAL.get();
    }


    public static void closeResources(Connection connection, PreparedStatement ps, ResultSet rs) {
        try {

            if (rs != null) rs.close();
            if (ps != null) ps.close();

            if (connection != null) {
                connection.close();
                THREAD_LOCAL.remove();
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public static void main(String[] args) {

        //一个进程里面 调用getMysqlConn  151
        //在一个进程里面  不管调用多少次  都是同一个连接的对象?
        //1.解决方式: 存储到静态代码块

        new Thread(DBHelper::a).start();
        new Thread(DBHelper::a).start();


        new Thread(() -> {
            for (int i = 1; i <= 10; i++) {

                try {
                    Connection conn1 = getMysqlConn();
                    System.out.println(Thread.currentThread().getName() + ":" + conn1.isClosed());

                    if (i == 5) {
                        //关闭了连接
                        conn1.close();
                    }
                    TimeUnit.MILLISECONDS.sleep(300);
                } catch (InterruptedException | SQLException e) {
                    e.printStackTrace();
                }
            }

        }).start();

        new Thread(() -> {
            for (int i = 1; i <= 10; i++) {
                Connection conn1 = getMysqlConn();
                try {
                    System.out.println(Thread.currentThread().getName() + ":" + conn1.isClosed());
                    TimeUnit.SECONDS.sleep(1);
                } catch (InterruptedException | SQLException e) {
                    e.printStackTrace();
                }
            }

        }).start();

        //前提: 一个进程里面有一个连接对象  多个线程是相互独立的
        //一个线程关闭 其它线程就不能使用的
        //保证每个线程都有一个连接对象就可以了。 ThreadLocal--->以空间换取时间


    }

    private static void a() {
        for (int i = 1; i <= 10; i++) {
            Connection conn1 = getMysqlConn();
            System.out.println(Thread.currentThread().getName() + ":" + conn1);
            try {
                TimeUnit.MILLISECONDS.sleep(300);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }
}
