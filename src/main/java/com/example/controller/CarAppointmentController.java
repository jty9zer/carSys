package com.example.controller;

import com.example.bean.CarAppointment;
import com.example.service.CarAppointmentService;
import com.example.utils.JSONResult;
import org.apache.ibatis.annotations.Param;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("appointment")
public class CarAppointmentController {
    @Autowired
    CarAppointmentService carAppointmentService;
    /**
     *
     * 人工维修预约
     * @param appointmentId
     * @param name
     * @param phone
     * @param number
     * @param vip
     * @param datetime
     * @return
     */
    @PostMapping("insert")
    public JSONResult insertAppointment(@Param("appointmentId") Integer appointmentId, @Param("name") String name, @Param("phone") String phone,
                                        @Param("number") String number, @Param("vip") boolean vip, @Param("datetime") String datetime) {
        List<CarAppointment> carAppointments = new ArrayList<>();
        CarAppointment carAppointment = new CarAppointment(appointmentId, name,phone,number,vip,datetime);
        carAppointments.add(carAppointment);
        carAppointmentService.insert(carAppointments);
        return JSONResult.ok();
    }
    /**
     * 查询所有预约
     * @return
     */
    @GetMapping("findAll")
    public JSONResult findAll() {
        List<CarAppointment> carAppointments = carAppointmentService.query(null, null);
        return JSONResult.ok(carAppointments);
    }


}
