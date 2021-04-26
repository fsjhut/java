package com.mysql;

import com.mysql.jdbc.Driver;
import org.junit.Test;
import sun.security.util.Password;

import java.io.InputStream;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.Properties;

/**
 * @author SunHang
 * @className: MysqlTest
 * @description:
 * @createTime 2021/4/17 20:25
 */
public class MysqlTest {

    @Test
    public void testConnection() throws SQLException {
//        String driver2 = "com.mysql.jdbc.Driver";
        String username = "root";
        String password = "root";
        Driver driver = new com.mysql.jdbc.Driver();
        String url = "jdbc:mysql://localhost:3306/test";
        Properties info = new Properties();
        // 将用户名和密码封装在properties中
        info.setProperty("user", username);
        info.setProperty("password", password);
        Connection conn = driver.connect(url, info);
        System.out.println(conn);
    }

    // 方式二： 对方式一的迭代,通过反射获取driver对象
    // 好处：在程序中不出现第三方的API，使程序具有良好的可移植性
    @Test
    public void testConnection2() throws Exception {
        String driver2 = "com.mysql.jdbc.Driver";
        String username = "root";
        String password = "root";
        Class<?> clazz = Class.forName(driver2);
        Driver driver = (Driver) clazz.newInstance();
        String url = "jdbc:mysql://localhost:3306/test";
        Properties info = new Properties();
        // 将用户名和密码封装在properties中
        info.setProperty("user", username);
        info.setProperty("password", password);
        Connection conn = driver.connect(url, info);
        System.out.println(conn);
    }

    // 方式三：使用DriverManager替换Driver
    @Test
    public void testConnection3() throws Exception {
        String driver2 = "com.mysql.jdbc.Driver";
        String user = "root";
        String password = "root";
        String url = "jdbc:mysql://localhost:3306/test";
        // 获取driver的实现类对象
        Class<?> clazz = Class.forName(driver2);
        Driver driver = (Driver) clazz.newInstance();
        DriverManager.registerDriver(driver);
        Connection conn = DriverManager.getConnection(url, user, password);
        System.out.println(conn);
    }

    // 方式四：可以省略  Driver driver = (Driver) clazz.newInstance();
    //                  DriverManager.registerDriver(driver);
    // 可省略的原因：Driver类中提供了静态代码块：DriverManager.registerDriver(new Driver());
    @Test
    public void testConnection4() throws Exception {
        // 1. 提供三个连接的基本信息
        String user = "root";
        String password = "root";
        String url = "jdbc:mysql://localhost:3306/test";
        // 2. 获取driver实现类的对象
        String driver2 = "com.mysql.jdbc.Driver";
        // 将driver的类加入到JVM中，加载驱动 注：mysql可以省略下面的步骤，其它不可以
        Class.forName(driver2);
//        Driver driver = (Driver) clazz.newInstance();
        // 3. 注册驱动 本步骤可有可无
//        DriverManager.registerDriver(driver);
        // 4. 获取连接
        Connection conn = DriverManager.getConnection(url, user, password);
        System.out.println(conn);
    }
    // 方式五：将第三方信息放在配置文件中，通过加载配置文件的方式获取连接
    // 配置文件
    // 好处：1. 实现了数据与代码的分离，实现了解耦，提高了可移植性
    //       2. 如果需要修改配置文件，可以避免程序的重新打包

    @Test
    public void testConnection5() throws Exception {
        // 1. 读取配置文件中的信息
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
        Connection conn = DriverManager.getConnection(url, user, password);
        System.out.println(conn);
    }
}


