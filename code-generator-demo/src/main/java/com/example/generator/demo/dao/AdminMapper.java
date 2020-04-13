package com.example.generator.demo.dao;

import com.example.generator.demo.entity.Admin;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

/**
 * @Title: AdminMapper
 * @Description: Admin持久层
 * @Company: example
 * @Author: liuhf
 * @CreateTime: 2020/04/13 15:24:00
 */
@Mapper
public interface AdminMapper  {

    Admin selectByPrimaryKey(Integer id);
    
    int deleteByPrimaryKey(Integer id);

    int insert(Admin record);

    int insertSelective(Admin record);

    int updateByPrimaryKeySelective(Admin record);

    int updateByPrimaryKey(Admin record);

    int deleteBatchIds(@Param("idList") List<String> idList);
    
    List<Admin> selectPage(Admin example);
}
