package com.example.service;

import com.example.bean.Car;
import com.example.dao.first.ICarDao;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class CarService {
    @Autowired
    private ICarDao iCarDao;

    public void insert(List<Car> cars) {
        iCarDao.insert(cars);
    }

    public void delete(String[] queryFields, Object[] queryValues) {
        Car car = carUtil(queryFields, queryValues);
        iCarDao.delete(car);
    }

    public void update(String[] upFields, Object[] upValues, String[] queryFields, Object[] queryValues) {
        Car queryCar = carUtil(queryFields, queryFields);
        Car upCar = carUtil(upFields, upValues);
        iCarDao.update(queryCar, upCar);
    }

    public List<Car> query(String[] queryFields, Object[] queryValues) {
        Car car = carUtil(queryFields, queryValues);
        return iCarDao.query(car);
    }

    public Car carUtil(String[] queryFields, Object[] queryValues) {
        Car car = new Car();
        if(queryFields != null) {
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
