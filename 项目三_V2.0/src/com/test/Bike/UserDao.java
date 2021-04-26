package com.test.Bike;

/**
 * @author SunHang
 * @className: UserDao
 * @description:
 * @createTime 2021/3/25 22:11
 */
public interface UserDao extends BaseDao {
    void showUser();
    void login(User[] userArr);
    User[] register(User[] userArr, int index, int cls);
}
