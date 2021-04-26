package com.javasm.jdbc.service;

import com.javasm.jdbc.bean.UserInfo;
import com.javasm.jdbc.common.ServerResponse;

import java.util.Map;

/**
 * @author: DELL
 * @className: UserService
 * @description:
 * @date: 2021/4/19 14:43
 * @version:0.1
 * @since: 1.8
 */
public interface UserInfoService {


    //新增用户业务逻辑
//    Map<String, Object> addUser(UserInfo userInfo);
//
//    //查询单个用户业务功能
//    Map<String, Object> selectOneUser(int uid);


    //新增用户业务逻辑
    ServerResponse addUser(UserInfo userInfo);

    //查询单个用户业务功能
    ServerResponse<UserInfo> selectOneUser(int uid);

}
