package jdbc.dao.impl;

import jdbc.bean.Role;

/**
 * @className: RoleDao
 * @description:   
 * @author SunHang
 * @createTime 2021/4/19 16:51
 */
public interface RoleDao {
    int addRole(Role role) throws Exception;
    int addRolePer(String[] pidArray,int rid) throws Exception;
}
