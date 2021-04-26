package com.test1;

/**
 * @className: UserTest
 * @description:   
 * @author SunHang
 * @createTime 2021/4/13 20:05
 */
public class UserTest {
    public static void main(String[] args) {
        UserModule userModule = new UserModule();
        ResponseResult<UserInfo> result = userModule.selectOne();
        if(result.getCode().equals(400)){
            System.out.println(result.getMessage());
            return;
        }
        UserInfo data = result.getData();
        System.out.println(data.getId());
    }
}
