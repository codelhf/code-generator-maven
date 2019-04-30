package com.example.generator.demo.service.impl;

import com.alibaba.fastjson.JSON;
import com.example.generator.demo.common.ResponseCode;
import com.example.generator.demo.common.ServerResponse;
import com.example.generator.demo.dao.ResultMapper;
import com.example.generator.demo.dto.ResultDTO;
import com.example.generator.demo.po.Result;
import com.example.generator.demo.service.IResultService;
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
 * @CreateTime: 2019-04-30 16:56:49
 */
@Service
public class ResultServiceImpl implements IResultService {

    @Autowired
    private ResultMapper resultMapper;

    /**
     * @Title: list
     * @Description: 查询Result列表
     * @Company: example
     * @Author: liuhf
     * @CreateTime: 2019-04-30 16:56:49
     * 
     * @param pageNum
     * @param pageSize
     * @param params
     * @return ServerResponse<PageInfo>
     */
    public ServerResponse<PageInfo> list(Integer pageNum, Integer pageSize, Map<String, String> params) {
        if (pageNum != null && pageSize != null) {
            PageHelper.startPage(pageNum, pageSize);
        }
        Result result = null;
        if (CollectionUtils.isNotEmpty(params.values())) {
            result = JSON.parseObject(JSON.toJSONString(params), Result.class);
        }
        List<Result> resultList = resultMapper.selectPageList(result);
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
     * @CreateTime: 2019-04-30 16:56:49
     * 
     * @param id
     * @return ServerResponse<ResultDTO>
     */
    public ServerResponse<ResultDTO> select(String id) {
        if (StringUtils.isBlank(id)) {
            return ServerResponse.createByErrorMessage("id不能为空");
        }
        Result result = resultMapper.selectByPrimaryKey(id);
        if (result == null) {
            return ServerResponse.createByErrorMessage("Result不存在");
        }
        ResultDTO resultDTO = new ResultDTO();
        BeanUtils.copyProperties(result, resultDTO);
        return ServerResponse.createBySuccess(resultDTO);
    }


}
