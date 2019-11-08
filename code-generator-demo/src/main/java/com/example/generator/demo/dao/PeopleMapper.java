package com.example.generator.demo.dao;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.example.generator.demo.entity.People;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

/**
 * @Title: PeopleMapper
 * @Description: People持久层
 * @Company: example
 * @Author: liuhf
 * @CreateTime: 2019-11-09 01:41:38
 */
@Mapper
public interface PeopleMapper extends BaseMapper<People> {

}
