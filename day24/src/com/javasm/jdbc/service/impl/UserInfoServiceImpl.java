package com.javasm.jdbc.service.impl;

import com.javasm.jdbc.bean.UserInfo;
import com.javasm.jdbc.common.ServerResponse;
import com.javasm.jdbc.dao.UserInfoDao;
import com.javasm.jdbc.dao.impl.UserInfoDaoImpl;
import com.javasm.jdbc.service.UserInfoService;
import com.javasm.jdbc.util.FileUtil;
import com.javasm.jdbc.util.MD5Util;

import java.util.HashMap;
import java.util.Map;
import java.util.Objects;

/**
 * @author: DELL
 * @className: UserInfoServiceImpl  调用dao功能
 * @description:
 * @date: 2021/4/19 14:44
 * @version:0.1
 * @since: 1.8
 */
public class UserInfoServiceImpl implements UserInfoService {
    @Override
    public ServerResponse addUser(UserInfo userInfo) {
        Objects.requireNonNull(userInfo);

        //用户头像上传
        //1.1 IO 实现文件上传
        //1.2 Socket编写文件服务器

        String serverFilePath = FileUtil.fileUpload(userInfo.getImage());
        userInfo.setImage(serverFilePath);

        //用户密码加密
        String encodePass = MD5Util.encodeStr(userInfo.getPass());
        userInfo.setPass(encodePass);

        UserInfoDao userInfoDao = new UserInfoDaoImpl();

        int result = userInfoDao.addUserInfo(userInfo);
        if (result == 0) {
            return ServerResponse.error();
        }
        return ServerResponse.success();
    }

    @Override
    public ServerResponse<UserInfo> selectOneUser(int uid) {
        UserInfoDao userInfoDao = new UserInfoDaoImpl();
        UserInfo userInfo = userInfoDao.selectUserById(uid);
        if (userInfo == null) {
            return ServerResponse.error();
        }
        return ServerResponse.success(userInfo);
    }
}
