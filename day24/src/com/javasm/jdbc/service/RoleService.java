package com.javasm.jdbc.service;

import com.javasm.jdbc.bean.Role;
import com.javasm.jdbc.common.ServerResponse;

/**
 * @author: DELL
 * @className: RoleService
 * @description:
 * @date: 2021/4/19 16:23
 * @version:0.1
 * @since: 1.8
 */
public interface RoleService {

    ServerResponse addRole(Role role,String[] pids);
}
