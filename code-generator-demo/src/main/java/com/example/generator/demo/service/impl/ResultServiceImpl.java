package com.example.generator.demo.service.impl;

import com.alibaba.fastjson.JSON;
import com.example.generator.demo.common.ResponseCode;
import com.example.generator.demo.common.ServerResponse;
import com.example.generator.demo.dao.ResultMapper;
import com.example.generator.demo.dto.ResultDTO;
import com.example.generator.demo.entity.Result;
import com.example.generator.demo.service.ResultService;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.google.common.base.Splitter;
import org.apache.commons.collections.CollectionUtils;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

/**
* @Title: ResultServiceImpl
* @Description: Result业务层
* @Company: example
* @Author: liuhf
* @CreateTime: 2019-11-09 01:41:38
*/
@Service
public class ResultServiceImpl implements ResultService {

    @Autowired
    private ResultMapper resultMapper;

    /**
     * @Title: list
     * @Description: 查询Result列表
     * @Company: example
     * @Author: liuhf
     * @CreateTime: 2019-11-09 01:41:38
     *
     * @param pageNum
     * @param pageSize
     * @param params
     * @return ServerResponse<PageInfo>
     */
    @Override
    public ServerResponse<PageInfo> list(Integer pageNum, Integer pageSize, Map<String, String> params) {
        if (pageNum != null && pageSize != null) {
            PageHelper.startPage(pageNum, pageSize);
        }
        QueryWrapper<Result> wrapper = new QueryWrapper<>();

        List<Result> resultList = resultMapper.selectList(wrapper);
        List<ResultDTO> resultDTOList = new ArrayList<>();
        BeanUtils.copyProperties(resultList, resultDTOList, List.class);
        PageInfo pageInfo = new PageInfo(resultList);
        pageInfo.setList(resultDTOList);
        return ServerResponse.createBySuccess(pageInfo);
    }
    /**
     * @Title: select
     * @Description: 查询Result对象
     * @Company: example
     * @Author: liuhf
     * @CreateTime: 2019-11-09 01:41:38
     *
     * @param id
     * @return ServerResponse<ResultDTO>
     */
    @Override
    public ServerResponse<ResultDTO> select(String id) {
        if (StringUtils.isBlank(String.valueOf(id))) {
            return ServerResponse.createByErrorMessage("id不能为空");
        }
        Result result = resultMapper.selectById(id);
        if (result == null) {
            return ServerResponse.createByErrorMessage("Result不存在");
        }
        ResultDTO resultDTO = new ResultDTO();
        BeanUtils.copyProperties(result, resultDTO);
        return ServerResponse.createBySuccess(resultDTO);
    }
}
