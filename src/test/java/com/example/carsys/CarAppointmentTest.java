package com.example.carsys;

import com.example.CarSysApplication;
import com.example.bean.CarAppointment;
import com.example.service.CarAppointmentService;
import org.junit.FixMethodOrder;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.MethodSorters;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;

import static org.junit.Assert.assertEquals;

@RunWith(SpringRunner.class)
@SpringBootTest(classes = CarSysApplication.class)
@FixMethodOrder(MethodSorters.NAME_ASCENDING)
public class CarAppointmentTest {
    @Autowired
    CarAppointmentService appointmentService;

    @Test
    public void aInsert() {
        // 新增测试一和测试二的预约
        int size1 = appointmentService.query(null, null).size();
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        CarAppointment carAppointment1 = new CarAppointment(9, "测试一", "11111111111","测A88888",true, "2021-08-15 00:00:00");
        CarAppointment carAppointment2 = new CarAppointment(10, "测试二", "22222222222", "测B88888",true,"2021-08-16 00:00:00");
        List<CarAppointment> carAppointments = new ArrayList<CarAppointment>();
        carAppointments.add(carAppointment1);
        carAppointments.add(carAppointment2);
        appointmentService.insert(carAppointments);
        int size2 = appointmentService.query(null, null).size();
        assertEquals(size1 + 2, size2);
    }


    @Test
    public void bDelete() {
        int size1 = appointmentService.query(null, null).size();
        //删除测试一的预约
        String[] queryFields = new String[]{"name"};
        Object[] queryValues = new Object[]{"测试一"};
        appointmentService.delete(queryFields, queryValues);
        //删除测试二的预约
        queryFields = new String[]{"name"};
        queryValues = new Object[]{"测试二"};
        appointmentService.delete(queryFields, queryValues);
        int size2 = appointmentService.query(null, null).size();
        assertEquals(size1 - 2, size2);

    }

    @Test
    public void cUpdate() {
        int size1 = appointmentService.query(null, null).size();
        // 更新李四的预约的时间
        String[] upFields = new String[]{"datetime"};
        Object[] upValues = new Object[]{"2021-08-15 00:00:00"};
        String[] queryFields = new String[]{"name"};
        Object[] queryValues = new Object[]{"李四"};
        appointmentService.update(upFields, upValues, queryFields, queryValues);
        int size2 = appointmentService.query(null, null).size();
        assertEquals(size1, size2);
    }

    @Test
    public void dQuery() {
        //查询所有
        List<CarAppointment> carAppointments = appointmentService.query(null, null);
        System.out.println("查询所有: 数量"+carAppointments.size());
        for(CarAppointment carAppointment:carAppointments){
            System.out.println(carAppointment.toString());
        }
        //查询所有VIP预约
        String[] queryFields = new String[]{"vip"};
        Object[] queryValues = new Object[]{"true"};
        carAppointments = appointmentService.query(queryFields, queryValues);
        System.out.println("查询所有VIP预约: 数量"+carAppointments.size());
        for(CarAppointment carAppointment:carAppointments){
            System.out.println(carAppointment.toString());
        }
        //查询所有日期早于2021-08-13的预约
        queryFields = new String[]{"datetime"};
        queryValues = new Object[]{"2021-08-13 00:00:00"};
        carAppointments = appointmentService.query(queryFields, queryValues);
        System.out.println("查询所有VIP预约早于2021-08-13 00:00:00 : 数量"+carAppointments.size());
        for(CarAppointment carAppointment:carAppointments){
            System.out.println(carAppointment.toString());
        }
    }
}
