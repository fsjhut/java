package com.test1;

/**
 * @className: UserModule
 * @description:   
 * @author SunHang
 * @createTime 2021/4/13 20:03
 */
public class UserModule {
    public ResponseResult<UserInfo> selectOne(){
        UserInfoDanImpl userInfoDan = new UserInfoDanImpl();
        UserInfo userInfo = userInfoDan.selectOneUser();
        if(userInfo==null){
            return ResponseResult.error("查询单个用户信息失败",400);
        }
        return ResponseResult.success("查询用户信息成功",200,userInfo);
    }
}
