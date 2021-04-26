package jdbc.service;

import jdbc.bean.Role;
import jdbc.common.ServerResponse;

/**
 * @className: RoleService
 * @description:   
 * @author SunHang
 * @createTime 2021/4/19 16:53
 */
public interface RoleService {
    ServerResponse addRole(Role role, String[] pids);
}
