package com.example.service;

import com.example.bean.CarAccessories;
import com.example.dao.second.ICarAccessoriesDao;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CarAccessoriesService {
    @Autowired
    ICarAccessoriesDao iCarAccessoriesDao;

    public void insert(List<CarAccessories> carAccessories){
        iCarAccessoriesDao.insert(carAccessories);
    }

    public void delete(String[] queryFields, Object[] queryValues){
        CarAccessories carAccessories = carAccessoriesUtil(queryFields, queryValues);
        iCarAccessoriesDao.delete(carAccessories);
    }


    public void update(String[] upFields, Object[] upValues, String[] queryFields, Object[] queryValues){
        CarAccessories query = carAccessoriesUtil(queryFields, queryValues);
        CarAccessories up = carAccessoriesUtil(upFields, upValues);
        iCarAccessoriesDao.update(query, up);
    }

    public List<CarAccessories> query(String[] queryFields, Object[] queryValues) {
        CarAccessories carAccessories = carAccessoriesUtil(queryFields, queryValues);
        return iCarAccessoriesDao.query(carAccessories);

    }

    public CarAccessories carAccessoriesUtil(String[] queryFields, Object[] queryValues){
        CarAccessories carAccessories = new CarAccessories();
        if(queryFields!= null) {
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
