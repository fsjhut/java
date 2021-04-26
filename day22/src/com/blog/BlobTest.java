package com.blog;

import com.bean.Customer;
import com.util.JDBCUtils;
import org.junit.Test;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.InputStream;
import java.sql.*;

/**
 * @className: BlobTest
 * @description: 测试场操作Blob类型的数据
 * @author SunHang
 * @createTime 2021/4/18 10:59
 */
public class BlobTest {
    // 向数据表中插入blob数据类型
    @Test
    public void testInsert() throws Exception {
        Connection conn = JDBCUtils.getConnection();
        String sql = "insert into customers(name,email,birth,photo)values(?,?,?,?)";
        PreparedStatement ps = conn.prepareStatement(sql);
        ps.setObject(1, "孙航");
        ps.setObject(2,"sunhang@164.com");
        ps.setObject(3,"1993-09-09");
        FileInputStream is = new FileInputStream(new File("C:\\Users\\JAVASM\\Pictures\\小鹤双拼壁纸.jpg"));
        ps.setBlob(4,is);
        ps.execute();
        JDBCUtils.closeResource(conn,ps);
    }
    // 查询数据表中的Blog类型的数据
    @Test
    public void testQuery()throws Exception{
        Connection conn = JDBCUtils.getConnection();
        String sql = "select id,name,email,birth,photo from customers where id = ?";
        PreparedStatement ps = conn.prepareStatement(sql);
        ps.setInt(1,21);
        ResultSet rs = ps.executeQuery();
        if(rs.next()){
//            int id = rs.getInt(1);
//            String name = rs.getString(2);
//            String email = rs.getString(3);
//            Date birth = rs.getDate(4);
            // 方式二
            int id = rs.getInt("id");
            String name = rs.getString("name");
            String email = rs.getString("email");
            Date birth = rs.getDate("birth");
            Customer cust = new Customer(id, name, email, birth);
            System.out.println(cust);
            // 将blob类型的字段，以文件的方式下载下来，保存在本地
            Blob photo = rs.getBlob("photo");
            InputStream is = photo.getBinaryStream();
            FileOutputStream fos = new FileOutputStream("sunhang.jpg");
            byte[] buffer = new byte[1024];
            int len;
            while ((len =(is.read(buffer)))!=-1){
                fos.write(buffer,0,len);
            }
            JDBCUtils.closeResource(conn,ps,rs);
            is.close();
            fos.close();
        }
    }
}
