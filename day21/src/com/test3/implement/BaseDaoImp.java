package com.test3.implement;

import com.test3.dao.BaseDao;

import java.util.List;

/**
 * @className: BaseDaoImp
 * @description:   
 * @author SunHang
 * @createTime 2021/4/13 20:42
 */
public abstract class BaseDaoImp<T> implements BaseDao<T> {


    @Override
    public void add(T t) {

    }

    @Override
    public void delete(Integer id) {

    }

    @Override
    public void update(T t) {

    }

    @Override
    public T selectOne(int id) {
        return null;
    }

    @Override
    public List<T> selectAll() {
        return null;
    }
}
