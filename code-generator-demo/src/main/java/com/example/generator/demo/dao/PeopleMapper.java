package com.example.generator.demo.dao;

import com.example.generator.demo.po.People;
//import org.apache.ibatis.annotations.Mapper;

import java.util.List;

/**
 * @Title: PeopleMapper
 * @Description: People实体类
 * @Company: example
 * @Author: liuhf
 * @CreateTime: 2019-04-11 16:10:39
 */
//@Mapper
public interface PeopleMapper  {

    List<People> selectAllListByKeyword();

    List<People> selectPageListByKeyword();

    People selectByPrimaryKey(int id);

    int deleteByPrimaryKey(int id);

    int insert(People record);

    int insertSelective(People record);

    int updateByPrimaryKeySelective(People record);

    int updateByPrimaryKey(People record);
}