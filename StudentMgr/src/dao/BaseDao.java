package dao;

import java.lang.reflect.InvocationTargetException;
import java.util.ArrayList;
import java.util.List;

/**
 * @author SunHang
 * @className: BaseDao
 * @description:
 * @createTime 2021/4/27 22:30
 */
public interface BaseDao<T> {

    boolean add(T t, List<T> ts);


    T[] findAll(List<T> ts);


    void deleteById(String delId, List<T> ts) throws Exception;


    ArrayList<Integer> getIndex(String id, List<T> ts, String filedName) throws Exception;


    void update(String updateId, T t, List<T> ts) throws Exception;
}
