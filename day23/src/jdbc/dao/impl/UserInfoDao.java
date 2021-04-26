package jdbc.dao.impl;
/**
 * @className: UserInfoDao
 * @description:   
 * @author SunHang
 * @createTime 2021/4/19 16:51
 */

import jdbc.bean.UserInfo;

import java.util.List;
import java.util.Map;

/**
 * 用户数据库的增删改查
 */
public interface UserInfoDao {
    int addUserInfo(UserInfo userInfo) throws Exception;
    int deleteUserInfo(int id);
    int deleteUserByIn(int[] ids);
    int updateUserInfoById(UserInfo userInfo);
    UserInfo selectUserById(int id);
    List<UserInfo> selectParamsUser(Object...args);
    int selectCount();
    List<UserInfo> selectAllUserInfo();
    List<UserInfo> selectUserInfoByPage(int page);
    List<UserInfo> selectByParams(Map<String,Object> params);
    List<Map<String,Object>> selectUserIfoAddress(int uid);

}
