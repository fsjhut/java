package jdbc.dao.impl.impl;

import jdbc.bean.Permission;
import jdbc.dao.impl.PermissionDao;
import jdbc.util.DBHelper;
import sun.security.pkcs11.Secmod;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

/**
 * @className: PermissionDaoImpl
 * @description:   
 * @author SunHang
 * @createTime 2021/4/19 16:52
 */
public class PermissionDaoImpl implements PermissionDao {
    private Connection conn;
    private PreparedStatement ps;
    private String sql;
    private ResultSet rs;
    @Override
    public List<Permission> selectAllPermission() {
        conn = DBHelper.getConnection();
        sql = "select * from permission";
        ArrayList<Permission> list = new ArrayList<>();

        try {
            ps = conn.prepareStatement(sql);
            rs = ps.executeQuery();
            while (rs.next()){
                list.add(new Permission(rs));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            DBHelper.closeResource(conn,ps,rs);
        }
        return list;
    }
}
