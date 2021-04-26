package jdbc.service.impl;

import jdbc.bean.Role;
import jdbc.common.ServerResponse;
import jdbc.dao.impl.impl.RoleDaoImpl;
import jdbc.service.RoleService;

/**
 * @className: RoleServiceImpl
 * @description:   
 * @author SunHang
 * @createTime 2021/4/19 16:54
 */
public class RoleServiceImpl implements RoleService {
    @Override
    public ServerResponse addRole(Role role, String[] pids) {
        RoleDaoImpl roleDao = new RoleDaoImpl();

        try {
            int rid = roleDao.addRole(role);
            roleDao.addRolePer(pids,rid);
            return ServerResponse.success();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return ServerResponse.error();
    }
}
