package com.example.generator.demo.dao;

import com.example.generator.demo.po.Prize;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

/**
 * @Title: PrizeMapper
 * @Description: Prize实体类
 * @Company: example
 * @Author: liuhf
 * @CreateTime: 2019-04-30 23:33:29
 */
@Mapper
public interface PrizeMapper  {

    Prize selectByPrimaryKey(int id);
    
    int deleteByPrimaryKey(int id);

    int insert(Prize record);

    int insertSelective(Prize record);

    int updateByPrimaryKeySelective(Prize record);

    int updateByPrimaryKey(Prize record);

    int deleteByIdList(@Param("idList") List<String> idList);
    
    List<Prize> selectPageList(Prize example);
}