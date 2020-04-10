package com.example.generator.demo.service.impl;

import com.alibaba.fastjson.JSON;
import com.example.generator.demo.common.ResponseCode;
import com.example.generator.demo.common.ServerResponse;
import com.example.generator.demo.dao.AdminMapper;
import com.example.generator.demo.dto.AdminDTO;
import com.example.generator.demo.entity.Admin;
import com.example.generator.demo.service.AdminService;
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
* @Title: AdminServiceImpl
* @Description: Admin业务层
* @Company: example
* @Author: liuhf
* @CreateTime: 2020/04/11 00:24:48
*/
@Service
public class AdminServiceImpl implements AdminService {

    @Autowired
    private AdminMapper adminMapper;

    /**
     * @Title: list
     * @Description: 查询Admin列表
     * @Company: example
     * @Author: liuhf
     * @CreateTime: 2020/04/11 00:24:48
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
        Admin admin = null;
        if (CollectionUtils.isNotEmpty(params.values())) {
            admin = JSON.parseObject(JSON.toJSONString(params), Admin.class);
        }
        List<Admin> adminList = adminMapper.selectPage(admin);
        List<AdminDTO> adminDTOList = new ArrayList<>();
        BeanUtils.copyProperties(adminList, adminDTOList, List.class);
        PageInfo pageInfo = new PageInfo<>(adminList);
        pageInfo.setList(adminDTOList);
        return ServerResponse.createBySuccess(pageInfo);
    }
    /**
     * @Title: select
     * @Description: 查询Admin对象
     * @Company: example
     * @Author: liuhf
     * @CreateTime: 2020/04/11 00:24:48
     *
     * @param id
     * @return ServerResponse<AdminDTO>
     */
    @Override
    public ServerResponse<AdminDTO> select(Integer id) {
        if (StringUtils.isBlank(String.valueOf(id))) {
            return ServerResponse.createByErrorMessage("id不能为空");
        }
        Admin admin = adminMapper.selectByPrimaryKey(id);
        if (admin == null) {
            return ServerResponse.createByErrorMessage("Admin不存在");
        }
        AdminDTO adminDTO = new AdminDTO();
        BeanUtils.copyProperties(admin, adminDTO);
        return ServerResponse.createBySuccess(adminDTO);
    }
    /**
     * @Title: insert
     * @Description: 保存Admin对象
     * @Company: example
     * @Author: liuhf
     * @CreateTime: 2020/04/11 00:24:48
     *
     * @param adminDTO
     * @return ServerResponse<String>
     */
    @Override
    public ServerResponse<String> insert(AdminDTO adminDTO) {
        Admin admin = new Admin();
        BeanUtils.copyProperties(adminDTO, admin);
        int rowCount = adminMapper.insertSelective(admin);
        if (rowCount == 0) {
            return ServerResponse.createByErrorMessage("新增Admin失败");
        }
        return ServerResponse.createBySuccessMessage("新增Admin成功");
    }
    /**
     * @Title: update
     * @Description: 更新Admin对象
     * @Company: example
     * @Author: liuhf
     * @CreateTime: 2020/04/11 00:24:48
     *
     * @param id
     * @param adminDTO
     * @return ServerResponse<String>
     */
    @Override
    public ServerResponse<String> update(Integer id, AdminDTO adminDTO) {
        if (StringUtils.isBlank(String.valueOf(id))) {
            return ServerResponse.createByErrorMessage("id不能为空");
        }
        adminDTO.setId(id);
        Admin admin = new Admin();
        BeanUtils.copyProperties(adminDTO, admin);
        int rowCount = adminMapper.updateByPrimaryKeySelective(admin);
        if (rowCount == 0) {
            return ServerResponse.createByErrorMessage("更新Admin失败");
        }
        return ServerResponse.createBySuccessMessage("更新Admin成功");
    }
    /**
     * @Title: delete
     * @Description: 批量删除Admin对象
     * @Company: example
     * @Author: liuhf
     * @CreateTime: 2020/04/11 00:24:48
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
        int rowCount = adminMapper.deleteBatchIds(idList);
        if (rowCount == 0 || rowCount < idList.size()) {
            return ServerResponse.createByErrorMessage("批量删除Admin失败");
        }
        return ServerResponse.createBySuccessMessage("批量删除Admin成功");
    }
}
