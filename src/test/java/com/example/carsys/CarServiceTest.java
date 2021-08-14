package com.example.carsys;

import com.example.CarSysApplication;
import com.example.bean.Car;
import com.example.service.CarService;
import org.junit.FixMethodOrder;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.MethodSorters;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.ArrayList;
import java.util.List;
import static org.junit.Assert.*;

@RunWith(SpringRunner.class)
@SpringBootTest(classes = CarSysApplication.class)
@FixMethodOrder(MethodSorters.NAME_ASCENDING)
public class CarServiceTest {
    @Autowired
    CarService service;

    @Test
    public void aInsert() {
        int size1 = service.query(null, null).size();
        Car car1 = new Car(9, "雪佛兰", "SUV", 120005.0f);
        Car car2 = new Car(10, "奥迪", "轿车", 500000.0f);
        List<Car> cars = new ArrayList<Car>();
        cars.add(car1);
        cars.add(car2);
        service.insert(cars);
        int size2 = service.query(null, null).size();
        assertEquals(size1 + 2, size2);
    }


    @Test
    public void bDelete() {
        int size1 = service.query(null, null).size();
        //删除宝马，SUV,价格低于500000
        String[] queryFields = new String[]{"brand", "type", "price"};
        Object[] queryValues = new Object[]{"宝马", "SUV", 500000.0f};
        service.delete(queryFields, queryValues);
        int size2 = service.query(null, null).size();
        assertEquals(size1 -1, size2);
    }

    @Test
    public void cUpdate() {
        // 更新SUV 的价格
        String[] upFields = new String[]{"price"};
        Object[] upValues = new Object[]{600000.0f};
        String[] queryFields = new String[]{"type"};
        Object[] queryValues = new Object[]{"SUV"};
        service.update(upFields, upValues, queryFields, queryValues);
    }

    @Test
    public void dQuery() {
        //查询所有
        List<Car> cars = service.query(null, null);
        System.out.println("查询所有: 数量"+cars.size());
        for(Car car:cars){
            System.out.println(car.toString());
        }
        //查询所有奔驰车
        String[] queryFields = new String[]{"brand"};
        Object[] queryValues = new Object[]{"奔驰"};
        cars = service.query(queryFields, queryValues);
        System.out.println("查询所有奔驰: 数量"+cars.size());
        for(Car car:cars){
            System.out.println(car.toString());
        }
        //查询奔驰，SUV
        queryFields = new String[]{"brand", "type"};
        queryValues = new Object[]{"奔驰", "SUV"};
        cars = service.query(queryFields, queryValues);
        System.out.println("查询奔驰SUV: 数量"+cars.size());
        for(Car car:cars){
            System.out.println(car.toString());
        }
        //查询轿车，价格小于200000.0
        queryFields = new String[]{"type", "price"};
        queryValues = new Object[]{"轿车", 200000.0f};
        cars = service.query(queryFields, queryValues);
        System.out.println("查询查询轿车，价格小于200000.0: 数量"+cars.size());
        for(Car car:cars){
            System.out.println(car.toString());
        }
    }
}