package com.example.generator.demo.dao;

import com.example.generator.demo.po.Result;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

/**
 * @Title: ResultMapper
 * @Description: Result实体类
 * @Company: example
 * @Author: liuhf
 * @CreateTime: 2019-04-30 16:56:49
 */
@Mapper
public interface ResultMapper  {




    Result selectByPrimaryKey(String id);



    List<Result> selectPageList(Result example);

}