package com.example.dao.second;

import com.example.bean.CarAccessories;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * 车辆配件 数据层
 *
 * @Author Zhang Chenyang
 * @Date 2022/01/04 20:43
 * @Version 1.0
 */
@Mapper
public interface ICarAccessoriesDao {
    /**
     * 插入
     *
     * @param cas
     */
    void insert(List<CarAccessories> cas);

    /**
     * 删除
     *
     * @param carAccessories 参数为null时，删除所有，谨慎使用；
     *                       参数不为null 按照carAccessories对象中不为null的字段 进行查询，删除满足条件的，若有多个字段不问null时，多个字段之间使用and
     */
    void delete(@Param("ca") CarAccessories carAccessories);

    /**
     * 更新
     *
     * @param query 参数为null，更新所有数据库中所有记录
     *              参数不为null 按照对象中不为null的字段 进行查询，更新满足条件的，若有多个字段不问null时，多个字段之间使用and
     * @param up    参数为null时，不进行更新操作，直接返回
     *              参数不为null时，按照对象中不为null的字段，更新其值
     */
    void update(@Param("q") CarAccessories query, @Param("u") CarAccessories up);

    /**
     * 查询
     *
     * @param carAccessories 参数为null时，查询所有的内容
     *                       参数不为null时，按照对象中不为null的字段 进行查询，若有多个字段不问null时，多个字段之间使用and
     * @return
     */
    List<CarAccessories> query(@Param("ca") CarAccessories carAccessories);
}
