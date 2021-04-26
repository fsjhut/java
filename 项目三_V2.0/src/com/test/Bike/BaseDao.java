package com.test.Bike;

/**
 * @author SunHang
 * @className: BaseDao
 * @description:
 * @createTime 2021/3/25 21:24
 */
public interface BaseDao {
    Object[] add(Object[] objArr,Object obj);
    Object[] delete(Object[] objArr,Object obj);
    void update(Object[] objArr,Object obj);
    int select(Object[] objArr,int index);
    int[] rankList(int[] arrays);
}
