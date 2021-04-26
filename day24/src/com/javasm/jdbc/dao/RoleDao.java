package com.javasm.jdbc.dao;

import com.javasm.jdbc.bean.Role;

/**
 * @author: DELL
 * @className: RoleDao
 * @description:
 * @date: 2021/4/19 15:43
 * @version:0.1
 * @since: 1.8
 */
public interface RoleDao {

    /**
     * 新增角色
     *
     * @param role
     * @return 获得自增id
     */
    int addRole(Role role) throws Exception;

    /**
     * @param pidArray 一个角色关联的多个权限的id
     * @return
     */
    int addRolePer(String[] pidArray, int rid) throws Exception;
}
