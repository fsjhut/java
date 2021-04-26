package com.test.Bike;



/**
 * @author SunHang
 * @className: BikeDao
 * @description:
 * @createTime 2021/3/25 22:11
 */
public interface BikeDao extends BaseDao {
    void borrowBike(Company company);
    void returnBike(Company company);
    void showBike(Company bikeCompany);
    void rankList(Company bikeCompany);
}
