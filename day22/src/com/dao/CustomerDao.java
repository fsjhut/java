package com.dao;
/**
 * @className: CustomerDao
 * @description:   
 * @author SunHang
 * @createTime 2021/4/18 13:40
 */

import com.bean.Customer;
import sun.net.ConnectionResetException;

import java.sql.Connection;
import java.util.List;

/**
 * 此接口，用于规范针对customers表的常用操作
 */
public interface CustomerDao {
    /**
     * @Title: insert
     * @Description: 将cust对象添加到数据库中
     * @param: [conn, cust]
     * @return: void
     * @date: 2021/4/18 13:41
     * @version: 1.0
     */
    void insert(Connection conn, Customer cust);

    /**
     * 根据指定id删除表中的一个记录
     * @param conn
     * @param id
     */
    void deleteById(Connection conn,int id);

    /**
     * 针对cust对象，去修改数据库中的指定的记录
     * @param conn
     * @param cust
     */
    void update(Connection conn, Customer cust);

    /**
     * 针对id查询到对应的Customer对象
     * @param conn
     * @param id
     */
    void getCustomerById(Connection conn,int id);

    /**
     * 查询表中的所有的数据的集合
     * @param conn
     * @return
     */
    List<Customer> getAll(Connection conn);

    /**
     * 返回数据表中的数据的条目数
     * @param conn
     * @return
     */
    Long getCount(Connection conn);
}
