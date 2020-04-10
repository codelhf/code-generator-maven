package com.example.generator.demo.dao;

import com.example.generator.demo.entity.User;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

/**
 * @Title: UserMapper
 * @Description: User持久层
 * @Company: example
 * @Author: liuhf
 * @CreateTime: 2020/04/11 00:24:48
 */
@Mapper
public interface UserMapper  {

    User selectByPrimaryKey(Integer id);
    
    int deleteByPrimaryKey(Integer id);

    int insert(User record);

    int insertSelective(User record);

    int updateByPrimaryKeySelective(User record);

    int updateByPrimaryKey(User record);

    int deleteBatchIds(@Param("idList") List<String> idList);
    
    List<User> selectPage(User example);
}
