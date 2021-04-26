package com.javasm.test;

import com.javasm.jdbc.bean.Permission;
import com.javasm.jdbc.bean.Role;
import com.javasm.jdbc.dao.PermissionDao;
import com.javasm.jdbc.dao.RoleDao;
import com.javasm.jdbc.dao.impl.PermissionDaoImpl;
import com.javasm.jdbc.dao.impl.RoleDaoImpl;
import com.javasm.jdbc.service.RoleService;
import com.javasm.jdbc.service.impl.RoleServiceImpl;

import java.util.Arrays;
import java.util.List;
import java.util.Scanner;

/**
 * @author: DELL
 * @className: RoleTest
 * @description:
 * @date: 2021/4/19 15:53
 * @version:0.1
 * @since: 1.8
 */
public class RoleTest {


    public static void main(String[] args) {

        //新增角色信息  并关联角色的权限功能
        //1.查看所有的权限数据的前提下  分配
        Scanner input = new Scanner(System.in);
        System.out.println("录入新的角色名称:");
        String newRoleName = input.next();
        System.out.println("录入新的角色描述:");
        String str = input.next();
        
        System.out.println("目前所有的权限功能如下，请给" + newRoleName + "分配权限:");
        PermissionDao permissionDao = new PermissionDaoImpl();
        List<Permission> list = permissionDao.selectAllPermission();
        list.forEach(System.out::println);
        String[] choiceArray = input.next().split(",");
        System.out.println(Arrays.toString(choiceArray));


        RoleService roleService = new RoleServiceImpl();
        System.out.println(roleService.addRole(new Role(newRoleName, str), choiceArray));
    }
}
