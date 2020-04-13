package com.example.generator.demo.dao;

import com.example.generator.demo.entity.Prize;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

/**
 * @Title: PrizeMapper
 * @Description: Prize持久层
 * @Company: example
 * @Author: liuhf
 * @CreateTime: 2020/04/13 16:00:44
 */
@Mapper
public interface PrizeMapper  {

    Prize selectByPrimaryKey(Integer id);
    
    int deleteByPrimaryKey(Integer id);

    int insert(Prize record);

    int insertSelective(Prize record);

    int updateByPrimaryKeySelective(Prize record);

    int updateByPrimaryKey(Prize record);

    int deleteBatchIds(@Param("idList") List<String> idList);
    
    List<Prize> selectPage(Prize example);
}
