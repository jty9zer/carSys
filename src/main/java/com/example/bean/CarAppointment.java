package com.example.bean;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;;

public class CarAppointment {
    Integer appointmentId;
    String name, phone, number;
    Boolean vip;
    Date datetime;

    public CarAppointment(Integer appointmentId, String name, String phone, String number, Boolean vip, String datetimeStr) {
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        Date datetime = null;
        try{
            datetime=simpleDateFormat.parse(datetimeStr);
        }catch (ParseException e){
            e.printStackTrace();
        }
        this.appointmentId = appointmentId;
        this.name = name;
        this.phone = phone;
        this.number = number;
        this.vip = vip;
        this.datetime = datetime;
    }

    public CarAppointment() {
    }

    public Integer getAppointmentId() {
        return appointmentId;
    }

    public void setAppointmentId(Integer appointmentId) {
        this.appointmentId = appointmentId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getNumber() {
        return number;
    }

    public void setNumber(String number) {
        this.number = number;
    }

    public Boolean getVip() {
        return vip;
    }

    public void setVip(Boolean vip) {
        this.vip = vip;
    }

    public Date getDatetime() {
        return datetime;
    }

    public void setDatetime(Date datetime) {
        this.datetime = datetime;
    }

    @Override
    public String toString() {
        return "CarAppointment{" +
                "appointmentId=" + appointmentId +
                ", name='" + name + '\'' +
                ", phone='" + phone + '\'' +
                ", number='" + number + '\'' +
                ", vip=" + vip +
                ", datetime=" + datetime +
                '}';
    }
}
