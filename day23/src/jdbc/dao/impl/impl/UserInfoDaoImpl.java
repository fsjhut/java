package jdbc.dao.impl.impl;

import jdbc.bean.UserInfo;
import jdbc.constants.CommonConstant;
import jdbc.dao.impl.UserInfoDao;
import jdbc.util.DBHelper;

import java.lang.reflect.Field;
import java.sql.*;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @className: UserInfoDaoImpl
 * @description:   
 * @author SunHang
 * @createTime 2021/4/19 16:52
 */
public class UserInfoDaoImpl implements UserInfoDao {
    private Connection conn;
    private PreparedStatement ps;
    private String sql;
    private ResultSet rs;
    private int result=0;
    @Override
    public int addUserInfo(UserInfo userInfo) {
        // 1. 获取数据库的连接
        conn = DBHelper.getConnection();
        // 2. 数据库的增加语句
        sql = "insert into userinfo(username,image,age, birthday, balance, rid,pass) valuse" +
                "(?,?,?,?,?,?,?)";
        try {
            ps = conn.prepareStatement(sql);
            ps.setObject(1,userInfo.getName());
            ps.setObject(2,userInfo.getImage());
            ps.setObject(3,userInfo.getAge());
            ps.setObject(4,userInfo.getBirthday());
            ps.setObject(5,userInfo.getBalance());
            ps.setObject(6,userInfo.getRid());
            ps.setObject(7,userInfo.getPass());
            result = ps.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            DBHelper.closeResource(conn,ps);
        }
        return result;
    }

    @Override
    public int deleteUserInfo(int id) {
        conn = DBHelper.getConnection();
        sql = "delete from userinfo where id = ?";
        try {
            ps = conn.prepareStatement(sql);
            ps.setObject(1,id);
            result = ps.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            DBHelper.closeResource(conn,ps);
        }
        return result;
    }

    @Override
    public int deleteUserByIn(int[] ids) {
        conn = DBHelper.getConnection();
        StringBuilder stringBuilder = new StringBuilder("delete from userinfo where id in (");
        for (int id : ids) {
            stringBuilder.append(id);
            stringBuilder.append(",");
        }
        stringBuilder.deleteCharAt(stringBuilder.lastIndexOf(","));
        stringBuilder.append(")");
        try {
            ps = conn.prepareStatement(stringBuilder.toString());
            result = ps.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            DBHelper.closeResource(conn,ps);
        }
        return result;
    }

    @Override
    public int updateUserInfoById(UserInfo userInfo) {
        conn = DBHelper.getConnection();
        sql = "update tb_userinfo set username=?,image=?,age=?, birthday=?, balance=?, rid=?,pass=? where id=?";
        int result = 0;
        try {
            ps = conn.prepareStatement(sql);//sql语句在ps对象
            ps.setString(1, userInfo.getName());
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
            DBHelper.closeResource(conn, ps);
        }
        return result;
    }

    @Override
    public UserInfo selectUserById(int id) {
        conn = DBHelper.getConnection();
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
            DBHelper.closeResource(conn, ps, rs);
        }
        return userInfo;
    }

    @Override
    public List<UserInfo> selectParamsUser(Object... args) {
        conn = DBHelper.getConnection();
        StringBuilder builder = new StringBuilder("select (");
        for (Object arg : args) {
            builder.append(arg);
            builder.append(",");
        }
        builder.deleteCharAt(builder.lastIndexOf(","));
        builder.append(")");
        builder.append(" from userinfo");
        ArrayList<UserInfo> userInfos = null;
        try {
            ps = conn.prepareStatement(builder.toString());
            rs = ps.executeQuery();
            userInfos = new ArrayList<>();
            ResultSetMetaData metaData = rs.getMetaData();
            int columnCount = metaData.getColumnCount();
            while (rs.next()){
                UserInfo userInfo = new UserInfo();
                for (int i = 0; i < columnCount; i++) {
                    String columnLabel = metaData.getColumnLabel(i + 1);
                    Object columnValue = rs.getObject(i + 1);
                    Field field = UserInfo.class.getDeclaredField(columnLabel);
                    field.setAccessible(true);
                    field.set(userInfo,columnValue);
                }
                userInfos.add(userInfo);
            }
            return userInfos;
        } catch (Exception e) {
            e.printStackTrace();
        }finally {
            DBHelper.closeResource(conn,ps,rs);
        }
        return null;
    }

    @Override
    public int selectCount() {
        conn = DBHelper.getConnection();
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
    public List<UserInfo> selectAllUserInfo() {
        conn = DBHelper.getConnection();
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
            DBHelper.closeResource(conn, ps, rs);
        }
        return userInfoList;
    }

    @Override
    public List<UserInfo> selectUserInfoByPage(int page) {
        conn = DBHelper.getConnection();
        sql = "select * from userinfo order by id desc limit ?,?";
        ArrayList<UserInfo> userInfos = new ArrayList<>();
        try {
            ps = conn.prepareStatement(sql);
            ps.setInt(1,(page-1)*CommonConstant.SIZE);
            ps.setInt(2,CommonConstant.SIZE);
            rs = ps.executeQuery();
            while (rs.next()){
                userInfos.add(new UserInfo(rs));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            DBHelper.closeResource(conn,ps,rs);
        }
        return userInfos;
    }

    @Override
    public List<UserInfo> selectByParams(Map<String, Object> params) {
        conn = DBHelper.getConnection();
        StringBuilder builder = new StringBuilder("select * from userinfo where ");
        params.forEach((key,value)->{
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
            while (rs.next()){
                userInfoList.add(new UserInfo(rs));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            DBHelper.closeResource(conn,ps,rs);
        }
        return userInfoList;
    }

    @Override
    public List<Map<String, Object>> selectUserIfoAddress(int uid) {
        conn = DBHelper.getConnection();
        sql = "select\n" +
                "  u.id,u.username,u.age,sa.province,sa.city,sa.area,sa.id AS sid\n" +
                "from\n" +
                "  tb_ship_address sa, tb_userinfo u\n" +
                "where u.id = ? and sa.uid=u.id";
        List<Map<String, Object>> list = new ArrayList<>(10);
        try {
            ps = conn.prepareStatement(sql);
            ps.setInt(1,uid);
            rs = ps.executeQuery();
            while (rs.next()){
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
            DBHelper.closeResource(conn,ps,rs);
        }
        return list;
    }
}
