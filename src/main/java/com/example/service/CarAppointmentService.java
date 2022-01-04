package com.example.service;

import com.example.bean.Car;
import com.example.bean.CarAppointment;
import com.example.config.DBManager;
import com.example.dao.third.ICarAppointmentDao;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

/**
 * 车辆维修预约 业务层
 *
 * @Author Zhang Chenyang
 * @Date 2022/01/04 20:43
 * @Version 1.0
 */
@Service
public class CarAppointmentService {
    @Autowired
    private ICarAppointmentDao iCarAppointmentDao;

    /**
     * 新增预约
     *
     * @param carAppointments
     */
    public void insert(List<CarAppointment> carAppointments) {
        iCarAppointmentDao.insert(carAppointments);
    }

    /**
     * 删除预约
     *
     * @param queryFields
     * @param queryValues
     */
    public void delete(String[] queryFields, Object[] queryValues) {
        CarAppointment carAppointment = appointmentUtil(queryFields, queryValues);
        iCarAppointmentDao.delete(carAppointment);
    }

    /**
     * 更新预约
     *
     * @param upFields
     * @param upValues
     * @param queryFields
     * @param queryValues
     */
    public void update(String[] upFields, Object[] upValues, String[] queryFields, Object[] queryValues) {
        CarAppointment query = appointmentUtil(queryFields, queryValues);
        CarAppointment up = appointmentUtil(upFields, upValues);
        iCarAppointmentDao.update(query, up);
    }

    /**
     * 查询预约
     *
     * @param queryFields
     * @param queryValues
     * @return
     */
    public List<CarAppointment> query(String[] queryFields, Object[] queryValues) {
        CarAppointment carAppointment = appointmentUtil(queryFields, queryValues);
        return iCarAppointmentDao.query(carAppointment);
    }

    /**
     * 参数设置工具
     *
     * @param queryFields
     * @param queryValues
     * @return
     */
    public CarAppointment appointmentUtil(String[] queryFields, Object[] queryValues) {
        CarAppointment carAppointment = new CarAppointment();
        if (queryFields != null) {
            for (int i = 0; i < queryFields.length; i = i + 1) {
                if (queryFields[i].equals("name")) {
                    carAppointment.setName(queryValues[i].toString());
                }
                if (queryFields[i].equals("phone")) {
                    carAppointment.setPhone(queryValues[i].toString());
                }
                if (queryFields[i].equals("number")) {
                    carAppointment.setNumber(queryValues[i].toString());
                }
                if (queryFields[i].equals("vip")) {
                    carAppointment.setVip(Boolean.valueOf(queryValues[i].toString()));
                }
                if (queryFields[i].equals("datetime")) {
                    SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
                    Date datetime = null;
                    try {
                        datetime = simpleDateFormat.parse(queryValues[i].toString());
                    } catch (ParseException e) {
                        e.printStackTrace();
                    }
                    carAppointment.setDatetime(datetime);
                }
            }
        }
        return carAppointment;
    }
}
