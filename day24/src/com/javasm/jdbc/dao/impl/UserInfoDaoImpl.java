package com.javasm.jdbc.dao.impl;

import com.javasm.jdbc.bean.UserInfo;
import com.javasm.jdbc.constants.CommonConstant;
import com.javasm.jdbc.dao.UserInfoDao;
import com.javasm.jdbc.util.DBHelper;

import java.io.File;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @author: DELL
 * @className: UserInfoDaoImpl
 * @description:
 * @date: 2021/4/17 16:20
 * @version:0.1
 * @since: 1.8
 */
public class UserInfoDaoImpl implements UserInfoDao {

    private Connection conn;
    private PreparedStatement ps;
    private String sql;
    private ResultSet rs;

    @Override
    public int addUserInfo(UserInfo userInfo) {
        //1.获得数据库连接
        conn = DBHelper.getMysqlConn();
        //2.准备sql语句 使用占位符? 进行占位 代表需要传递一个数据
        sql = "insert  into tb_userinfo (username,image,age, birthday, balance, rid,pass) values (?,?,?,?,?,?,?)";
        //3. 执行sql---> 所有的sql都是DBMS的服务软件里面执行  java程序没有能力执行

        int result = 0;
        try {
            //3.1 将sql发送到mysql的服务中
            ps = conn.prepareStatement(sql);//sql语句在ps对象
            //查看sql语句有没有占位符 1
            ps.setString(1, userInfo.getUsername());
            ps.setString(2, userInfo.getImage());
            ps.setInt(3, userInfo.getAge());
            ps.setObject(4, userInfo.getBirthday());
            ps.setBigDecimal(5, userInfo.getBalance());
            ps.setInt(6, userInfo.getRid());
            ps.setString(7,userInfo.getPass());
            //3.2 在mysql服务  执行sql语句  DDL  DML(insert update delete) DQL(select)
            result = ps.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
            //删除文件
            new File(userInfo.getImage()).delete();
        } finally {
            DBHelper.closeResources(conn, ps, null);
        }
        return result;
    }

    @Override
    public int deleteUserInfoById(int id) {

        conn = DBHelper.getMysqlConn();
        sql = "delete from tb_userinfo where id=?";

        int result = 0;
        try {
            ps = conn.prepareStatement(sql);
            ps.setInt(1, id);

            result = ps.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            DBHelper.closeResources(conn, ps, rs);
        }
        return result;
    }

    @Override
    public int deleteUserByIn(int[] ids) {
        conn = DBHelper.getMysqlConn();
        StringBuilder builder = new StringBuilder("delete from tb_userinfo where id in ( ");

        for (int id : ids) {
            builder.append(id);
            builder.append(",");
        }
        builder.deleteCharAt(builder.lastIndexOf(","));
        builder.append(")");
        int result = 0;
        try {
            ps = conn.prepareStatement(builder.toString());
            result = ps.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return result;
    }

    @Override
    public UserInfo selectUserById(int id) {
        conn = DBHelper.getMysqlConn();
        sql = "select * from tb_userinfo where id=?";// DQL
        UserInfo userInfo = null;
        try {
            //1.将sql发送到mysql
            ps = conn.prepareStatement(sql);
            //2. 是否有占位符
            ps.setInt(1, id);
            //3. 执行sql  类似Iterator
            rs = ps.executeQuery();//select查询的结果(临时表的数据) 都在rs对象中
            //判断光标之后是否有更多记录需要遍历
            if (rs.next()) {
                //获得记录  get
                userInfo = new UserInfo(rs);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            DBHelper.closeResources(conn, ps, rs);
        }
        return userInfo;
    }

    @Override
    public int updateUserInfoById(UserInfo userInfo) {
        conn = DBHelper.getMysqlConn();
        sql = "update tb_userinfo set username=?,image=?,age=?, birthday=?, balance=?, rid=?,pass=? where id=?";
        int result = 0;
        try {
            ps = conn.prepareStatement(sql);//sql语句在ps对象
            ps.setString(1, userInfo.getUsername());
            ps.setString(2, userInfo.getImage());
            ps.setInt(3, userInfo.getAge());
            ps.setObject(4, userInfo.getBirthday());
            ps.setBigDecimal(5, userInfo.getBalance());
            ps.setInt(6, userInfo.getRid());
            ps.setString(7,userInfo.getPass());
            ps.setInt(8, userInfo.getId());
            result = ps.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            DBHelper.closeResources(conn, ps, null);
        }
        return result;
    }

    @Override
    public List<UserInfo> selectAllUserInfo() {
        conn = DBHelper.getMysqlConn();
        sql = "select * from tb_userinfo";// DQL
        UserInfo userInfo = null;
        List<UserInfo> userInfoList = new ArrayList<>(10);
        try {
            ps = conn.prepareStatement(sql);
            rs = ps.executeQuery();
            while (rs.next()) {
                userInfoList.add(new UserInfo(rs));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            DBHelper.closeResources(conn, ps, rs);
        }
        return userInfoList;
    }

    @Override
    public List<UserInfo> selectUserInfoByPage(int page) {
        conn = DBHelper.getMysqlConn();
        sql = "select * from tb_userinfo order by id DESC limit ?,?";
        UserInfo userInfo = null;
        List<UserInfo> userInfoList = new ArrayList<>(10);
        try {
            ps = conn.prepareStatement(sql);
            ps.setInt(1, (page - 1) * CommonConstant.SIZE);
            ps.setInt(2, CommonConstant.SIZE);
            rs = ps.executeQuery();
            while (rs.next()) {
                userInfoList.add(new UserInfo(rs));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            DBHelper.closeResources(conn, ps, rs);
        }
        return userInfoList;
    }

    @Override
    public int selectCount() {
        conn = DBHelper.getMysqlConn();
        sql = "select count(*) from tb_userinfo";//Long

        long cou = 0L;
        try {
            ps = conn.prepareStatement(sql);
            rs = ps.executeQuery();

            if (rs.next()) {
                cou = (Long) rs.getObject(1);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return (int) cou;
    }

    @Override
    public List<UserInfo> selectByParams(Map<String, Object> params) {

        conn = DBHelper.getMysqlConn();
        StringBuilder builder = new StringBuilder("select * from tb_userinfo where ");
        params.forEach((key, value) -> {
            builder.append(key);
            builder.append("='");
            builder.append(value);
            builder.append("'");
            builder.append(" and ");
        });
        builder.delete(builder.lastIndexOf("and"), builder.length());

        List<UserInfo> userInfoList = new ArrayList<>(10);
        try {
            ps = conn.prepareStatement(builder.toString());

            rs = ps.executeQuery();
            while (rs.next()) {
                userInfoList.add(new UserInfo(rs));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            DBHelper.closeResources(conn, ps, rs);
        }
        return userInfoList;
    }

    @Override
    public List<Map<String, Object>> selectUserInfoAddress(int uid) {

        conn = DBHelper.getMysqlConn();
        sql = "select\n" +
                "  u.id,u.username,u.age,sa.province,sa.city,sa.area,sa.id AS sid\n" +
                "from\n" +
                "  tb_ship_address sa, tb_userinfo u\n" +
                "where u.id = ? and sa.uid=u.id";
        List<Map<String, Object>> list = new ArrayList<>(10);
        try {
            ps = conn.prepareStatement(sql);

            ps.setInt(1, uid);
            rs = ps.executeQuery();

            while (rs.next()) {
                Map<String, Object> map = new HashMap<>(16);
                map.put("id", rs.getObject("id"));
                map.put("username", rs.getObject("username"));
                map.put("province", rs.getObject("province"));
                map.put("city", rs.getObject("city"));
                map.put("area", rs.getObject("area"));
                map.put("sid", rs.getObject("sid"));
                list.add(map);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            DBHelper.closeResources(conn, ps, rs);
        }
        return list;
    }
}
