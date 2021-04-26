package com.javasm.jdbc.service.impl;

import com.javasm.jdbc.bean.Role;
import com.javasm.jdbc.common.ServerResponse;
import com.javasm.jdbc.dao.RoleDao;
import com.javasm.jdbc.dao.impl.RoleDaoImpl;
import com.javasm.jdbc.service.RoleService;

/**
 * @author: DELL
 * @className: RoleServiceImpl
 * @description:
 * @date: 2021/4/19 16:23
 * @version:0.1
 * @since: 1.8
 */
public class RoleServiceImpl implements RoleService {
    @Override
    public ServerResponse addRole(Role role, String[] pids) {

        RoleDao roleDao = new RoleDaoImpl();
        try {
            int rid = roleDao.addRole(role);
            roleDao.addRolePer(pids, rid);
            return ServerResponse.success();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return ServerResponse.error();
    }
}