package com.test1;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/**
 * @className: UserInfoDanImpl
 * @description:   
 * @author SunHang
 * @createTime 2021/4/13 19:59
 */
public class UserInfoDanImpl {
    public UserInfo selectOneUser(){
        return new UserInfo(1,"jim");
    }

    public List<UserInfo> selectAllUser(){
        ArrayList<UserInfo> userInfos = new ArrayList<>();
        Collections.addAll(userInfos,
                new UserInfo(1,"jim"),
                new UserInfo(2,"tom"),
                new UserInfo(3,"lily"));
        return userInfos;
    }
}
