package jdbc.dao.impl.impl;

import jdbc.bean.Role;
import jdbc.dao.impl.RoleDao;
import jdbc.util.DBHelper;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;

/**
 * @className: RoleDaoImpl
 * @description:   
 * @author SunHang
 * @createTime 2021/4/19 16:52
 */
public class RoleDaoImpl implements RoleDao {
    private Connection conn;
    private PreparedStatement ps;
    private String sql;
    private ResultSet rs;
    @Override
    public int addRole(Role role) throws Exception {
        conn = DBHelper.getConnection();
        sql = "insert into role (rolename,remar) values (?,?)";
        long result = 0;
        ps = conn.prepareStatement(sql,Statement.RETURN_GENERATED_KEYS);
        ps.setString(1,role.getRoleName());
        ps.setString(2,role.getRemark());
        ps.executeUpdate();
        // 执行完毕后，将id返回
        rs = ps.getGeneratedKeys();
        if(rs.next()){
            result = rs.getLong(1);
        }
        return (int)result;
    }

    @Override
    public int addRolePer(String[] pidArray, int rid) throws Exception {
        conn = DBHelper.getConnection();
        StringBuilder builder = new StringBuilder("insert into role_per (roleid,pid) values");
        for (String pid : pidArray) {
            builder.append("(");
            builder.append(rid);
            builder.append(",");
            builder.append(pid);
            builder.append(")");
            builder.append(",");
        }
        builder.deleteCharAt(builder.lastIndexOf(","));
        ps = conn.prepareStatement(builder.toString());
        return ps.executeUpdate();
    }
}
