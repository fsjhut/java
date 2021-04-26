import jdbc.bean.Permission;
import jdbc.bean.Role;
import jdbc.dao.impl.impl.PermissionDaoImpl;
import jdbc.service.impl.RoleServiceImpl;

import java.util.Arrays;
import java.util.List;
import java.util.Scanner;

/**
 * @className: UserInfoTest
 * @description:   
 * @author SunHang
 * @createTime 2021/4/19 22:29
 */
public class UserInfoTest {
    public static void main(String[] args) {
        //新增角色信息  并关联角色的权限功能
        //1.查看所有的权限数据的前提下  分配
        Scanner input = new Scanner(System.in);
        System.out.println("录入新的角色名称:");
        String newRoleName = input.next();
        System.out.println("录入新的角色描述:");
        String str = input.next();
        System.out.println("目前所有的权限功能如下，请给" + newRoleName + "分配权限:");
        PermissionDaoImpl permissionDao = new PermissionDaoImpl();
        List<Permission> list = permissionDao.selectAllPermission();
        list.forEach(System.out::println);
        String[] choiceArray = input.next().split(",");
        System.out.println(Arrays.toString(choiceArray));

        RoleServiceImpl roleService = new RoleServiceImpl();
        System.out.println(roleService.addRole(new Role(newRoleName, str), choiceArray));

    }
}
