package jdbc.dao.impl;

import jdbc.bean.Permission;

import java.util.List;

/**
 * @className: PermissionDao
 * @description:   
 * @author SunHang
 * @createTime 2021/4/19 16:51
 */
public interface PermissionDao {
    List<Permission> selectAllPermission();
}
