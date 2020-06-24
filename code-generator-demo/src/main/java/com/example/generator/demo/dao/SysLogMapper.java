package com.example.generator.demo.dao;

import com.example.generator.demo.entity.SysLog;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

/**
 * @Title: SysLogMapper
 * @Description: SysLog持久层
 * @Company: example
 * @Author: liuhf
 * @CreateTime: 2020/06/24 18:29:01
 */
@Mapper
public interface SysLogMapper  {

    SysLog selectByPrimaryKey(Integer id);
    
    int deleteByPrimaryKey(Integer id);

    int insert(SysLog record);

    int insertSelective(SysLog record);

    int updateByPrimaryKeySelective(SysLog record);

    int updateByPrimaryKey(SysLog record);

    int deleteBatchIds(@Param("idList") List<String> idList);
    
    List<SysLog> selectPage(SysLog example);
}
