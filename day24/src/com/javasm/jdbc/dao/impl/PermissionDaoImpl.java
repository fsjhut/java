package com.javasm.jdbc.dao.impl;

import com.javasm.jdbc.bean.Permission;
import com.javasm.jdbc.dao.PermissionDao;
import com.javasm.jdbc.util.DBHelper;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

/**
 * @author: DELL
 * @className: PermissionDaoImpl
 * @description:
 * @date: 2021/4/19 15:48
 * @version:0.1
 * @since: 1.8
 */
public class PermissionDaoImpl implements PermissionDao {
    private Connection conn;
    private PreparedStatement ps;
    private String sql;
    private ResultSet rs;

    @Override
    public List<Permission> selectAllPermission() {
        conn = DBHelper.getMysqlConn();
        sql = "select * from tb_permission";

        List<Permission> list = new ArrayList<>(10);
        try {
            ps = conn.prepareStatement(sql);
            rs = ps.executeQuery();
            while (rs.next()) {
                list.add(new Permission(rs));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            DBHelper.closeResources(conn, ps, rs);
        }
        return list;
    }
}
