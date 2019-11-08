package com.example.generator.demo.service.impl;

import com.alibaba.fastjson.JSON;
import com.example.generator.demo.common.ResponseCode;
import com.example.generator.demo.common.ServerResponse;
import com.example.generator.demo.dao.PeopleMapper;
import com.example.generator.demo.dto.PeopleDTO;
import com.example.generator.demo.entity.People;
import com.example.generator.demo.service.PeopleService;
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
* @Title: PeopleServiceImpl
* @Description: People业务层
* @Company: example
* @Author: liuhf
* @CreateTime: 2019-11-09 01:41:38
*/
@Service
public class PeopleServiceImpl implements PeopleService {

    @Autowired
    private PeopleMapper peopleMapper;

    /**
     * @Title: list
     * @Description: 查询People列表
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
        QueryWrapper<People> wrapper = new QueryWrapper<>();

        List<People> peopleList = peopleMapper.selectList(wrapper);
        List<PeopleDTO> peopleDTOList = new ArrayList<>();
        BeanUtils.copyProperties(peopleList, peopleDTOList, List.class);
        PageInfo pageInfo = new PageInfo(peopleList);
        pageInfo.setList(peopleDTOList);
        return ServerResponse.createBySuccess(pageInfo);
    }
    /**
     * @Title: select
     * @Description: 查询People对象
     * @Company: example
     * @Author: liuhf
     * @CreateTime: 2019-11-09 01:41:38
     *
     * @param id
     * @return ServerResponse<PeopleDTO>
     */
    @Override
    public ServerResponse<PeopleDTO> select(int id) {
        if (StringUtils.isBlank(String.valueOf(id))) {
            return ServerResponse.createByErrorMessage("id不能为空");
        }
        People people = peopleMapper.selectById(id);
        if (people == null) {
            return ServerResponse.createByErrorMessage("People不存在");
        }
        PeopleDTO peopleDTO = new PeopleDTO();
        BeanUtils.copyProperties(people, peopleDTO);
        return ServerResponse.createBySuccess(peopleDTO);
    }
    /**
     * @Title: insert
     * @Description: 保存People对象
     * @Company: example
     * @Author: liuhf
     * @CreateTime: 2019-11-09 01:41:38
     *
     * @param peopleDTO
     * @return ServerResponse<String>
     */
    @Override
    public ServerResponse<String> insert(PeopleDTO peopleDTO) {
        People people = new People();
        BeanUtils.copyProperties(peopleDTO, people);
        int rowCount = peopleMapper.insert(people);
        if (rowCount == 0) {
            return ServerResponse.createByErrorMessage("新增People失败");
        }
        return ServerResponse.createBySuccessMessage("新增People成功");
    }
    /**
     * @Title: update
     * @Description: 更新People对象
     * @Company: example
     * @Author: liuhf
     * @CreateTime: 2019-11-09 01:41:38
     *
     * @param id
     * @param peopleDTO
     * @return ServerResponse<String>
     */
    @Override
    public ServerResponse<String> update(int id, PeopleDTO peopleDTO) {
        if (StringUtils.isBlank(String.valueOf(id))) {
            return ServerResponse.createByErrorMessage("id不能为空");
        }
        peopleDTO.setId(id);
        People people = new People();
        BeanUtils.copyProperties(peopleDTO, people);
        int rowCount = peopleMapper.updateById(people);
        if (rowCount == 0) {
            return ServerResponse.createByErrorMessage("更新People失败");
        }
        return ServerResponse.createBySuccessMessage("更新People成功");
    }
    /**
     * @Title: delete
     * @Description: 批量删除People对象
     * @Company: example
     * @Author: liuhf
     * @CreateTime: 2019-11-09 01:41:38
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
        int rowCount = peopleMapper.deleteBatchIds(idList);
        if (rowCount == 0 || rowCount < idList.size()) {
            return ServerResponse.createByErrorMessage("批量删除People失败");
        }
        return ServerResponse.createBySuccessMessage("批量删除People成功");
    }
}
