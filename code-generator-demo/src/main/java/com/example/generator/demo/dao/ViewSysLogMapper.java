package com.example.generator.demo.dao;

import com.example.generator.demo.entity.ViewSysLog;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

/**
 * @Title: ViewSysLogMapper
 * @Description: ViewSysLog持久层
 * @Company: example
 * @Author: liuhf
 * @CreateTime: 2020/06/24 18:29:01
 */
@Mapper
public interface ViewSysLogMapper  {

    ViewSysLog selectByPrimaryKey(String id);
    
    List<ViewSysLog> selectPage(ViewSysLog example);
}
