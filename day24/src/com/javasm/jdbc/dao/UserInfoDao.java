package com.javasm.jdbc.dao;

import com.javasm.jdbc.bean.UserInfo;

import java.util.List;
import java.util.Map;

/**
 * @author: DELL
 * @className: UserInfoDao
 * @description:
 * @date: 2021/4/17 16:19
 * @version:0.1
 * @since: 1.8
 */
public interface UserInfoDao {


    /**
     * 定义操作用户逻辑方法
     *
     * @param userInfo
     * @return
     */
    int addUserInfo(UserInfo userInfo);


    int deleteUserInfoById(int id);

    /**
     * 批量删除用户
     *
     * @param ids 用户id
     * @return 受影响的记录数
     */
    int deleteUserByIn(int[] ids);


    /**
     * 查询单个用户信息
     *
     * @param id 用户id
     * @return 查询到的用户信息
     */
    UserInfo selectUserById(int id);


    /**
     * 修改用户信息
     *
     * @param userInfo 用户对象
     * @return 受影响的记录数
     */
    int updateUserInfoById(UserInfo userInfo);

    /**
     * 查询所有用户信息
     */
    List<UserInfo> selectAllUserInfo();

    /**
     * 分页查询
     *
     * @param page 第几页
     * @return
     */
    List<UserInfo> selectUserInfoByPage(int page);

    int selectCount();

    List<UserInfo> selectByParams(Map<String, Object> params);

    List<Map<String, Object>> selectUserInfoAddress(int uid);
}
