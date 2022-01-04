package com.example.service;

import com.example.bean.Car;
import com.example.config.DBManager;
import com.example.dao.first.ICarDao;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;

/**
 * 车辆数据 业务层
 *
 * @Author Zhang Chenyang
 * @Date 2022/01/04 20:43
 * @Version 1.0
 */
@Component
public class CarService {
    @Autowired
    private ICarDao iCarDao;

    /**
     * 新增车辆
     *
     * @param cars
     */
    public void insert(List<Car> cars) {
        iCarDao.insert(cars);
    }

    /**
     * 删除车辆
     *
     * @param queryFields
     * @param queryValues
     */
    public void delete(String[] queryFields, Object[] queryValues) {
        Car car = carUtil(queryFields, queryValues);
        iCarDao.delete(car);
    }

    /**
     * 更新车辆
     *
     * @param upFields
     * @param upValues
     * @param queryFields
     * @param queryValues
     */
    public void update(String[] upFields, Object[] upValues, String[] queryFields, Object[] queryValues) {
        Car queryCar = carUtil(queryFields, queryFields);
        Car upCar = carUtil(upFields, upValues);
        iCarDao.update(queryCar, upCar);
    }

    /**
     * 查询车辆
     *
     * @param queryFields
     * @param queryValues
     * @return
     */
    public List<Car> query(String[] queryFields, Object[] queryValues) {
        Car car = carUtil(queryFields, queryValues);
        return iCarDao.query(car);
    }

    /**
     * 查询所有车辆（单例模式）
     *
     * @return
     */
    public List<Car> queryAllInSingletonMode() {
        DBManager dbm = DBManager.getInstance();
        dbm.conn_init("mysql", "jdbc:mysql://localhost:3306/carSys?useSSL=false&characterEncoding=utf8&serverTimezone=GMT%2B8", "root", "19961203");
        List<Car> queryResult = dbm.executeQuery(Car.class, "select * from car", null);
        dbm.close();
        return queryResult;
    }

    /**
     * 通过类型查询所有车辆（单例模式）
     *
     * @param queryValues
     * @return
     */
    public List<Car> queryByTypeInSingletonMode(String[] queryValues) {
        DBManager dbm = DBManager.getInstance();
        dbm.conn_init("mysql", "jdbc:mysql://localhost:3306/carSys?useSSL=false&characterEncoding=utf8&serverTimezone=GMT%2B8", "root", "19961203");
        List<Car> queryResult = dbm.executeQuery(Car.class, "select * from car where type=?", queryValues);
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
    public Car carUtil(String[] queryFields, Object[] queryValues) {
        Car car = new Car();
        if (queryFields != null) {
            for (int i = 0; i < queryFields.length; i = i + 1) {
                if (queryFields[i].equals("brand")) {
                    car.setBrand(queryValues[i].toString());
                }

                if (queryFields[i].equals("type")) {
                    car.setType(queryValues[i].toString());
                }

                if (queryFields[i].equals("price")) {
                    car.setPrice(Float.parseFloat(queryValues[i].toString()));
                }
            }
        }
        return car;
    }

}
