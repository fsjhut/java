package com.javasm.jdbc.dao.impl;

import com.javasm.jdbc.bean.Role;
import com.javasm.jdbc.dao.RoleDao;
import com.javasm.jdbc.util.DBHelper;

import java.sql.*;

/**
 * @author: DELL
 * @className: RoleDaoImpl
 * @description:
 * @date: 2021/4/19 15:44
 * @version:0.1
 * @since: 1.8
 */
public class RoleDaoImpl implements RoleDao {

    private Connection conn;
    private PreparedStatement ps;
    private String sql;
    private ResultSet rs;

    @Override
    public int addRole(Role role) throws Exception {
        conn = DBHelper.getMysqlConn();
        sql = "insert into tb_role (rolename,remark) values (?,?)";
        long result = 0;
        ps = conn.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);// 底层自动执行select last_insert_id()
        ps.setString(1, role.getRoleName());
        ps.setString(2, role.getRemark());

        ps.executeUpdate();
        //执行完sql之后  将id返回
        rs = ps.getGeneratedKeys();
        if (rs.next()) {
            result = rs.getLong(1);
        }

        return (int) result;
    }

    @Override
    public int addRolePer(String[] pidArray, int rid) throws Exception {
        conn = DBHelper.getMysqlConn();
        StringBuilder builder = new StringBuilder("insert into tb_role_per (roleid, pid) values ");
        for (String pid : pidArray) {
            builder.append("(");
            builder.append(rid);
            builder.append(",");
            builder.append(pid);
            builder.append(")");
            builder.append(",");
        }
        builder.deleteCharAt(builder.lastIndexOf(","));
        System.out.println(builder);

        ps = conn.prepareStatement(builder.toString());
        return ps.executeUpdate();
    }
}
