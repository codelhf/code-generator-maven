package com.example.generator.demo.dao;

import com.example.generator.demo.po.Result;
//import org.apache.ibatis.annotations.Mapper;

import java.util.List;

/**
 * @Title: ResultMapper
 * @Description: Result实体类
 * @Company: example
 * @Author: liuhf
 * @CreateTime: 2019-04-11 16:10:39
 */
//@Mapper
public interface ResultMapper  {

    List<Result> selectAllListByKeyword();

    List<Result> selectPageListByKeyword();

    Result selectByPrimaryKey();





}