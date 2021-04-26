package com.javasm.jdbc;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

/**
 * @author: DELL
 * @className: MysqlConnTest
 * @description:
 * @date: 2021/4/17 14:51
 * @version:0.1
 * @since: 1.8
 */
public class MysqlConnTest {


    public static void main(String[] args) {

        //获得mysql数据库的连接---> 对象
        //1. 获得 mysql数据库的连接对象

        String username = "root";
        String password = "root";
//        String url = "jdbc:mysql://ip:端口号/数据库名称?参数名=值&";
        String url = "jdbc:mysql://192.168.14.113:3306/aa?useSSL=false&characterEncoding=utf-8";
        //mysql服务的版本: 5+
        String driver = "com.mysql.jdbc.driver";//第三方的jar


        //2. 利用JDBC获得对象  DriverManager
        //JDBC4 ---> 服务发现机制  META-INF/service ---> 省略注册驱动
        //java项目可以这么用  web项目--->运行服务器-->jar/war META-INF没有service

        try {
            //2.1 注册驱动  java.sql.*
            // DriverManager.registerDriver(new Driver());
            Class.forName(driver);// jvm 自动创建Class对象  加载static的代码块(注册驱动)

            //2.2 DriverManager
            Connection connection = DriverManager.getConnection(url, username, password);
            System.out.println("mysql...连接:" + connection);
            //com.mysql.jdbc.JDBC4Connection@5b37e0d2

            //
            connection.close();
        } catch (ClassNotFoundException | SQLException e) {
            e.printStackTrace();
        }
    }
}
