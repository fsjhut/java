package jdbc.service.impl;

import jdbc.bean.UserInfo;
import jdbc.common.ServerResponse;
import jdbc.dao.impl.UserInfoDao;
import jdbc.dao.impl.impl.UserInfoDaoImpl;
import jdbc.service.UserInfoService;
import jdbc.util.FileUtil;
import jdbc.util.MD5Util;

import java.util.Objects;

/**
 * @className: UserInfoServiceImpl
 * @description:   
 * @author SunHang
 * @createTime 2021/4/19 16:55
 */
public class UserInfoServiceImpl implements UserInfoService {
    @Override
    public ServerResponse addUser(UserInfo userInfo) {
        Objects.requireNonNull(userInfo);
        String serverFilePath = FileUtil.fileUpload(userInfo.getImage());
        userInfo.setImage(serverFilePath);
        String encodeStr = MD5Util.encodeStr(userInfo.getPass());
        userInfo.setPass(encodeStr);
        UserInfoDaoImpl userInfoDao = new UserInfoDaoImpl();
        int result = userInfoDao.addUserInfo(userInfo);
        if(result==0){
            return ServerResponse.error();
        }
        return ServerResponse.success();
    }

    @Override
    public ServerResponse<UserInfo> selectOneUser(int uid) {
        UserInfoDaoImpl userInfoDao = new UserInfoDaoImpl();
        UserInfo userInfo = userInfoDao.selectUserById(uid);
        if(userInfo == null){
            return ServerResponse.error();
        }
        return ServerResponse.success();
    }
}
