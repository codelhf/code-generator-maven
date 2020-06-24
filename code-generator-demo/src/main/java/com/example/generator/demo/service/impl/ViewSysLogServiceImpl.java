package com.example.generator.demo.service.impl;

import com.alibaba.fastjson.JSON;
import com.example.generator.demo.common.ResponseCode;
import com.example.generator.demo.common.ServerResponse;
import com.example.generator.demo.dao.ViewSysLogMapper;
import com.example.generator.demo.dto.ViewSysLogDTO;
import com.example.generator.demo.entity.ViewSysLog;
import com.example.generator.demo.service.ViewSysLogService;
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
* @Title: ViewSysLogServiceImpl
* @Description: ViewSysLog业务层
* @Company: example
* @Author: liuhf
* @CreateTime: 2020/06/24 18:29:01
*/
@Service
public class ViewSysLogServiceImpl implements ViewSysLogService {

    @Autowired
    private ViewSysLogMapper viewSysLogMapper;

    /**
     * @Title: list
     * @Description: 查询ViewSysLog列表
     * @Company: example
     * @Author: liuhf
     * @CreateTime: 2020/06/24 18:29:01
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
        ViewSysLog viewSysLog = null;
        if (CollectionUtils.isNotEmpty(params.values())) {
            viewSysLog = JSON.parseObject(JSON.toJSONString(params), ViewSysLog.class);
        }
        List<ViewSysLog> viewSysLogList = viewSysLogMapper.selectPage(viewSysLog);
        List<ViewSysLogDTO> viewSysLogDTOList = new ArrayList<>();
        BeanUtils.copyProperties(viewSysLogList, viewSysLogDTOList, List.class);
        PageInfo pageInfo = new PageInfo<>(viewSysLogList);
        pageInfo.setList(viewSysLogDTOList);
        return ServerResponse.createBySuccess(pageInfo);
    }
    /**
     * @Title: select
     * @Description: 查询ViewSysLog对象
     * @Company: example
     * @Author: liuhf
     * @CreateTime: 2020/06/24 18:29:01
     *
     * @param id
     * @return ServerResponse<ViewSysLogDTO>
     */
    @Override
    public ServerResponse<ViewSysLogDTO> select(String id) {
        if (StringUtils.isBlank(String.valueOf(id))) {
            return ServerResponse.createByErrorMessage("id不能为空");
        }
        ViewSysLog viewSysLog = viewSysLogMapper.selectByPrimaryKey(id);
        if (viewSysLog == null) {
            return ServerResponse.createByErrorMessage("ViewSysLog不存在");
        }
        ViewSysLogDTO viewSysLogDTO = new ViewSysLogDTO();
        BeanUtils.copyProperties(viewSysLog, viewSysLogDTO);
        return ServerResponse.createBySuccess(viewSysLogDTO);
    }
}
