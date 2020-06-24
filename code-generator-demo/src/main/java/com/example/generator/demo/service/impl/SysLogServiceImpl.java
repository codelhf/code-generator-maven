package com.example.generator.demo.service.impl;

import com.alibaba.fastjson.JSON;
import com.example.generator.demo.common.ResponseCode;
import com.example.generator.demo.common.ServerResponse;
import com.example.generator.demo.dao.SysLogMapper;
import com.example.generator.demo.dto.SysLogDTO;
import com.example.generator.demo.entity.SysLog;
import com.example.generator.demo.service.SysLogService;
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
* @Title: SysLogServiceImpl
* @Description: SysLog业务层
* @Company: example
* @Author: liuhf
* @CreateTime: 2020/06/24 18:29:01
*/
@Service
public class SysLogServiceImpl implements SysLogService {

    @Autowired
    private SysLogMapper sysLogMapper;

    /**
     * @Title: list
     * @Description: 查询SysLog列表
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
        SysLog sysLog = null;
        if (CollectionUtils.isNotEmpty(params.values())) {
            sysLog = JSON.parseObject(JSON.toJSONString(params), SysLog.class);
        }
        List<SysLog> sysLogList = sysLogMapper.selectPage(sysLog);
        List<SysLogDTO> sysLogDTOList = new ArrayList<>();
        BeanUtils.copyProperties(sysLogList, sysLogDTOList, List.class);
        PageInfo pageInfo = new PageInfo<>(sysLogList);
        pageInfo.setList(sysLogDTOList);
        return ServerResponse.createBySuccess(pageInfo);
    }
    /**
     * @Title: select
     * @Description: 查询SysLog对象
     * @Company: example
     * @Author: liuhf
     * @CreateTime: 2020/06/24 18:29:01
     *
     * @param id
     * @return ServerResponse<SysLogDTO>
     */
    @Override
    public ServerResponse<SysLogDTO> select(Integer id) {
        if (StringUtils.isBlank(String.valueOf(id))) {
            return ServerResponse.createByErrorMessage("id不能为空");
        }
        SysLog sysLog = sysLogMapper.selectByPrimaryKey(id);
        if (sysLog == null) {
            return ServerResponse.createByErrorMessage("SysLog不存在");
        }
        SysLogDTO sysLogDTO = new SysLogDTO();
        BeanUtils.copyProperties(sysLog, sysLogDTO);
        return ServerResponse.createBySuccess(sysLogDTO);
    }
    /**
     * @Title: insert
     * @Description: 保存SysLog对象
     * @Company: example
     * @Author: liuhf
     * @CreateTime: 2020/06/24 18:29:01
     *
     * @param sysLogDTO
     * @return ServerResponse<String>
     */
    @Override
    public ServerResponse<String> insert(SysLogDTO sysLogDTO) {
        SysLog sysLog = new SysLog();
        BeanUtils.copyProperties(sysLogDTO, sysLog);
        int rowCount = sysLogMapper.insertSelective(sysLog);
        if (rowCount == 0) {
            return ServerResponse.createByErrorMessage("新增SysLog失败");
        }
        return ServerResponse.createBySuccessMessage("新增SysLog成功");
    }
    /**
     * @Title: update
     * @Description: 更新SysLog对象
     * @Company: example
     * @Author: liuhf
     * @CreateTime: 2020/06/24 18:29:01
     *
     * @param id
     * @param sysLogDTO
     * @return ServerResponse<String>
     */
    @Override
    public ServerResponse<String> update(Integer id, SysLogDTO sysLogDTO) {
        if (StringUtils.isBlank(String.valueOf(id))) {
            return ServerResponse.createByErrorMessage("id不能为空");
        }
        sysLogDTO.setId(id);
        SysLog sysLog = new SysLog();
        BeanUtils.copyProperties(sysLogDTO, sysLog);
        int rowCount = sysLogMapper.updateByPrimaryKeySelective(sysLog);
        if (rowCount == 0) {
            return ServerResponse.createByErrorMessage("更新SysLog失败");
        }
        return ServerResponse.createBySuccessMessage("更新SysLog成功");
    }
    /**
     * @Title: delete
     * @Description: 批量删除SysLog对象
     * @Company: example
     * @Author: liuhf
     * @CreateTime: 2020/06/24 18:29:01
     *
     * @param ids
     * @return ServerResponse<String>
     */
    @Override
    public ServerResponse<String> delete(String ids) {
        List<String> idList = Splitter.on(",").splitToList(ids);
        if (CollectionUtils.isEmpty(idList)) {
            return ServerResponse.createByErrorCodeMessage(ResponseCode.ILLEGAL_ARGUMENT.getCode(), "id不能为空");
        }
        int rowCount = sysLogMapper.deleteBatchIds(idList);
        if (rowCount == 0 || rowCount < idList.size()) {
            return ServerResponse.createByErrorMessage("批量删除SysLog失败");
        }
        return ServerResponse.createBySuccessMessage("批量删除SysLog成功");
    }
}
