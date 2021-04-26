package com.javasm.jdbc.dao;

import com.javasm.jdbc.bean.Permission;

import java.util.List;

/**
 * @author: DELL
 * @className: PermissonDao
 * @description:
 * @date: 2021/4/19 15:47
 * @version:0.1
 * @since: 1.8
 */
public interface PermissionDao {

    List<Permission> selectAllPermission();
}
