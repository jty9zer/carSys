package com.example.dao.third;

import com.example.bean.CarAppointment;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * 车辆维修预约 数据层
 *
 * @Author Zhang Chenyang
 * @Date 2022/01/04 20:43
 * @Version 1.0
 */
@Mapper
public interface ICarAppointmentDao {
    /**
     * 插入
     *
     * @param carAppointments
     */
    void insert(List<CarAppointment> carAppointments);

    /**
     * 删除
     *
     * @param carAppointment 参数为null时，删除所有，谨慎使用；
     *                       参数不为null 按照car对象中不为null的字段 进行查询，删除满足条件的，若有多个字段不问null时，多个字段之间使用and
     */
    void delete(@Param("cp") CarAppointment carAppointment);

    /**
     * 更新
     *
     * @param query 参数为null，更新所有数据库中所有记录
     *              参数不为null 按照对象中不为null的字段 进行查询，更新满足条件的，若有多个字段不问null时，多个字段之间使用and
     * @param up
     */
    void update(@Param("q") CarAppointment query, @Param("u") CarAppointment up);

    /**
     * 查询
     *
     * @param carAppointment 参数为null时，查询所有的内容
     *                       参数不为null时，按照对象中不为null的字段 进行查询，若有多个字段不问null时，多个字段之间使用and
     * @return
     */
    List<CarAppointment> query(@Param("cp") CarAppointment carAppointment);
}
