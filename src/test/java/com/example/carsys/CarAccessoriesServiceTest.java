package com.example.carsys;

import com.example.CarSysApplication;
import com.example.bean.CarAccessories;
import com.example.service.CarAccessoriesService;
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
public class CarAccessoriesServiceTest {
    @Autowired
    CarAccessoriesService service;

    @Test
    public void aInsert() {
        int size1 = service.query(null, null).size();
        CarAccessories ca1 = new CarAccessories(21, "雪佛兰", "发动机", true, 5000.0f);
        CarAccessories ca2 = new CarAccessories(22, "雪佛兰", "轮胎", true, 2000.0f);
        List<CarAccessories> carAccessoriesList = new ArrayList<CarAccessories>();
        carAccessoriesList.add(ca1);
        carAccessoriesList.add(ca2);
        service.insert(carAccessoriesList);
        int size2 = service.query(null, null).size();
        assertEquals(size1 + 2, size2);
    }

    @Test
    public void bDelete() {
        //删除 轮胎无货
        int size1 = service.query(null, null).size();
        String[] queryFields = new String[]{"accessoriesName", "availability"};
        Object[] queryValues = new Object[]{"轮胎", false};
        int count = service.query(queryFields, queryValues).size();
        service.delete(queryFields, queryValues);
        int size2 = service.query(null, null).size();
        assertEquals(size1 - count, size2);
    }

    @Test
    public void cUpdate() {
        // 将价格小于等于1000的轮胎修改为无货
        String[] queryFields = new String[]{"accessoriesName", "price"};
        Object[] queryValues = new Object[]{"轮胎", 1000.0f};
        String[] upFields = new String[]{"availability"};
        Object[] upValues = new Object[]{false};
        service.update(upFields, upValues, queryFields, queryValues);
    }

    @Test
    public void dQuery() {
        //查询所有
        List<CarAccessories> cas = service.query(null, null);
        System.out.println("查询所有: 数量" + cas.size());
        for (CarAccessories carAccessories : cas) {
            System.out.println(carAccessories.toString());
        }
        //查询轮胎，有货，价格低于5000
        String[] queryFields = new String[]{"accessoriesName", "availability", "price"};
        Object[] queryValues = new Object[]{"轮胎", true, 10000.0f};
        cas = service.query(queryFields, queryValues);
        System.out.println("查询轮胎，有货，价格低于5000: 数量" + cas.size());
        for (CarAccessories carAccessories : cas) {
            System.out.println(carAccessories.toString());
        }
    }

    @Test
    public void dQueryBySingletonModel() {
        //查询所有
        List<CarAccessories> cas = service.queryAllInSingletonMode();
        System.out.println("（单例模式）查询所有: 数量" + cas.size());
        for (CarAccessories carAccessories : cas) {
            System.out.println(carAccessories.toString());
        }
        //查询所有雪佛兰品牌
        String[] queryValues = new String[]{"雪佛兰"};
        cas = service.queryByBrandInSingletonMode(queryValues);
        System.out.println("（单例模式）查询所有雪佛兰: 数量" + cas.size());
        for (CarAccessories carAccessories : cas) {
            System.out.println(carAccessories.toString());
        }
    }
}