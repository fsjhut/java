package jdbc.service;

import jdbc.bean.UserInfo;
import jdbc.common.ServerResponse;

/**
 * @className: UserInfoService
 * @description:   
 * @author SunHang
 * @createTime 2021/4/19 16:53
 */
public interface UserInfoService {
    ServerResponse addUser(UserInfo userInfo);
    ServerResponse<UserInfo> selectOneUser(int uid);
}
