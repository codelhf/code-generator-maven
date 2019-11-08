package com.example.generator.demo.dao;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.example.generator.demo.entity.Result;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

/**
 * @Title: ResultMapper
 * @Description: Result持久层
 * @Company: example
 * @Author: liuhf
 * @CreateTime: 2019-11-09 01:41:38
 */
@Mapper
public interface ResultMapper extends BaseMapper<Result> {

}
