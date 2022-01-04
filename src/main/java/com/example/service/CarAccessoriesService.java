package com.example.service;

import com.example.bean.CarAccessories;
import com.example.bean.CarAppointment;
import com.example.config.DBManager;
import com.example.dao.second.ICarAccessoriesDao;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * 车辆配件 业务层
 *
 * @Author Zhang Chenyang
 * @Date 2022/01/04 20:43
 * @Version 1.0
 */
@Service
public class CarAccessoriesService {
    @Autowired
    ICarAccessoriesDao iCarAccessoriesDao;

    /**
     * 新增配件
     *
     * @param carAccessories
     */
    public void insert(List<CarAccessories> carAccessories) {
        iCarAccessoriesDao.insert(carAccessories);
    }

    /**
     * 删除配件
     *
     * @param queryFields
     * @param queryValues
     */
    public void delete(String[] queryFields, Object[] queryValues) {
        CarAccessories carAccessories = carAccessoriesUtil(queryFields, queryValues);
        iCarAccessoriesDao.delete(carAccessories);
    }

    /**
     * 更新配件
     *
     * @param upFields
     * @param upValues
     * @param queryFields
     * @param queryValues
     */
    public void update(String[] upFields, Object[] upValues, String[] queryFields, Object[] queryValues) {
        CarAccessories query = carAccessoriesUtil(queryFields, queryValues);
        CarAccessories up = carAccessoriesUtil(upFields, upValues);
        iCarAccessoriesDao.update(query, up);
    }

    /**
     * 查询配件
     *
     * @param queryFields
     * @param queryValues
     * @return
     */
    public List<CarAccessories> query(String[] queryFields, Object[] queryValues) {
        CarAccessories carAccessories = carAccessoriesUtil(queryFields, queryValues);
        return iCarAccessoriesDao.query(carAccessories);

    }

    /**
     * 查询所有配件（单例模式）
     *
     * @return
     */
    public List<CarAccessories> queryAllInSingletonMode() {
        DBManager dbm = DBManager.getInstance();
        dbm.conn_init("postgresql", "jdbc:postgresql://localhost:5432/carsys", "postgres", "19961203");
        List<CarAccessories> queryResult = dbm.executeQuery(CarAccessories.class, "select * from car_accessories", null);
        dbm.close();
        return queryResult;
    }

    /**
     * 通过品牌查询所有配件（单例模式）
     *
     * @param queryValues
     * @return
     */
    public List<CarAccessories> queryByBrandInSingletonMode(String[] queryValues) {
        DBManager dbm = DBManager.getInstance();
        dbm.conn_init("postgresql", "jdbc:postgresql://localhost:5432/carsys", "postgres", "19961203");
        List<CarAccessories> queryResult = dbm.executeQuery(CarAccessories.class, "select * from car_accessories where brand=?", queryValues);
        dbm.close();
        return queryResult;
    }

    /**
     * 参数设置工具
     *
     * @param queryFields
     * @param queryValues
     * @return
     */
    public CarAccessories carAccessoriesUtil(String[] queryFields, Object[] queryValues) {
        CarAccessories carAccessories = new CarAccessories();
        if (queryFields != null) {
            for (int i = 0; i < queryFields.length; i = i + 1) {
                if (queryFields[i].equals("brand")) {
                    carAccessories.setBrand(queryValues[i].toString());
                }

                if (queryFields[i].equals("accessoriesName")) {
                    carAccessories.setAccessoriesName(queryValues[i].toString());
                }

                if (queryFields[i].equals("availability")) {
                    carAccessories.setAvailability(Boolean.valueOf(queryValues[i].toString()));
                }

                if (queryFields[i].equals("price")) {
                    carAccessories.setPrice(Float.parseFloat(queryValues[i].toString()));
                }
            }
        }
        return carAccessories;
    }
}
