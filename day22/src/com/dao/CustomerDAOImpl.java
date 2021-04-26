package com.dao;

import com.bean.Customer;

import java.sql.Connection;
import java.util.List;

/**
 * @className: CustomerDAOImpl
 * @description:   
 * @author SunHang
 * @createTime 2021/4/18 13:47
 */
public class CustomerDAOImpl extends BaseDAO implements CustomerDao {
    @Override
    public void insert(Connection conn, Customer cust) {
        String sql = "insert into customers(name,email,birth)values(?,?,?)";
        update(conn,sql,cust.getName(),cust.getEmail(),cust.getBirth());
    }

    @Override
    public void deleteById(Connection conn, int id) {

    }

    @Override
    public void update(Connection conn, Customer cust) {

    }

    @Override
    public void getCustomerById(Connection conn, int id) {

    }

    @Override
    public List<Customer> getAll(Connection conn) {
        return null;
    }

    @Override
    public Long getCount(Connection conn) {
        return null;
    }
}
