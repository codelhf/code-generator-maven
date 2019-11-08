package com.example.generator.demo.dao;

import com.example.generator.demo.entity.People;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

/**
 * @Title: PeopleMapper
 * @Description: People持久层
 * @Company: example
 * @Author: liuhf
 * @CreateTime: 2019-11-09 01:45:36
 */
@Mapper
public interface PeopleMapper  {

    People selectByPrimaryKey(int id);
    
    int deleteByPrimaryKey(int id);

    int insert(People record);

    int insertSelective(People record);

    int updateByPrimaryKeySelective(People record);

    int updateByPrimaryKey(People record);

    int deleteBatchIds(@Param("idList") List<String> idList);
    
    List<People> selectPage(People example);
}
