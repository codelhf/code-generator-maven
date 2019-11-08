package com.example.generator.demo.dao;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.example.generator.demo.entity.Prize;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

/**
 * @Title: PrizeMapper
 * @Description: Prize持久层
 * @Company: example
 * @Author: liuhf
 * @CreateTime: 2019-11-09 01:41:38
 */
@Mapper
public interface PrizeMapper extends BaseMapper<Prize> {

}
