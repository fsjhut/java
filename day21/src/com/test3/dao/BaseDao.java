package com.test3.dao;

import java.util.List;

/**
 * @author SunHang
 * @className: BaseDao
 * @description:
 * @createTime 2021/4/13 20:41
 */
public interface BaseDao<T> {
    void add(T t);
    void delete(Integer id);
    void update(T t);
    T selectOne(int id);
    List<T> selectAll();
}
