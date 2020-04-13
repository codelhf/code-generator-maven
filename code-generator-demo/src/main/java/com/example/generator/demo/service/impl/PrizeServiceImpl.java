package com.example.generator.demo.service.impl;

import com.alibaba.fastjson.JSON;
import com.example.generator.demo.common.ResponseCode;
import com.example.generator.demo.common.ServerResponse;
import com.example.generator.demo.dao.PrizeMapper;
import com.example.generator.demo.dto.PrizeDTO;
import com.example.generator.demo.entity.Prize;
import com.example.generator.demo.service.PrizeService;
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
* @Title: PrizeServiceImpl
* @Description: Prize业务层
* @Company: example
* @Author: liuhf
* @CreateTime: 2020/04/13 16:00:44
*/
@Service
public class PrizeServiceImpl implements PrizeService {

    @Autowired
    private PrizeMapper prizeMapper;

    /**
     * @Title: list
     * @Description: 查询Prize列表
     * @Company: example
     * @Author: liuhf
     * @CreateTime: 2020/04/13 16:00:44
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
        Prize prize = null;
        if (CollectionUtils.isNotEmpty(params.values())) {
            prize = JSON.parseObject(JSON.toJSONString(params), Prize.class);
        }
        List<Prize> prizeList = prizeMapper.selectPage(prize);
        List<PrizeDTO> prizeDTOList = new ArrayList<>();
        BeanUtils.copyProperties(prizeList, prizeDTOList, List.class);
        PageInfo pageInfo = new PageInfo<>(prizeList);
        pageInfo.setList(prizeDTOList);
        return ServerResponse.createBySuccess(pageInfo);
    }
    /**
     * @Title: select
     * @Description: 查询Prize对象
     * @Company: example
     * @Author: liuhf
     * @CreateTime: 2020/04/13 16:00:44
     *
     * @param id
     * @return ServerResponse<PrizeDTO>
     */
    @Override
    public ServerResponse<PrizeDTO> select(Integer id) {
        if (StringUtils.isBlank(String.valueOf(id))) {
            return ServerResponse.createByErrorMessage("id不能为空");
        }
        Prize prize = prizeMapper.selectByPrimaryKey(id);
        if (prize == null) {
            return ServerResponse.createByErrorMessage("Prize不存在");
        }
        PrizeDTO prizeDTO = new PrizeDTO();
        BeanUtils.copyProperties(prize, prizeDTO);
        return ServerResponse.createBySuccess(prizeDTO);
    }
    /**
     * @Title: insert
     * @Description: 保存Prize对象
     * @Company: example
     * @Author: liuhf
     * @CreateTime: 2020/04/13 16:00:44
     *
     * @param prizeDTO
     * @return ServerResponse<String>
     */
    @Override
    public ServerResponse<String> insert(PrizeDTO prizeDTO) {
        Prize prize = new Prize();
        BeanUtils.copyProperties(prizeDTO, prize);
        int rowCount = prizeMapper.insertSelective(prize);
        if (rowCount == 0) {
            return ServerResponse.createByErrorMessage("新增Prize失败");
        }
        return ServerResponse.createBySuccessMessage("新增Prize成功");
    }
    /**
     * @Title: update
     * @Description: 更新Prize对象
     * @Company: example
     * @Author: liuhf
     * @CreateTime: 2020/04/13 16:00:44
     *
     * @param id
     * @param prizeDTO
     * @return ServerResponse<String>
     */
    @Override
    public ServerResponse<String> update(Integer id, PrizeDTO prizeDTO) {
        if (StringUtils.isBlank(String.valueOf(id))) {
            return ServerResponse.createByErrorMessage("id不能为空");
        }
        prizeDTO.setId(id);
        Prize prize = new Prize();
        BeanUtils.copyProperties(prizeDTO, prize);
        int rowCount = prizeMapper.updateByPrimaryKeySelective(prize);
        if (rowCount == 0) {
            return ServerResponse.createByErrorMessage("更新Prize失败");
        }
        return ServerResponse.createBySuccessMessage("更新Prize成功");
    }
    /**
     * @Title: delete
     * @Description: 批量删除Prize对象
     * @Company: example
     * @Author: liuhf
     * @CreateTime: 2020/04/13 16:00:44
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
        int rowCount = prizeMapper.deleteBatchIds(idList);
        if (rowCount == 0 || rowCount < idList.size()) {
            return ServerResponse.createByErrorMessage("批量删除Prize失败");
        }
        return ServerResponse.createBySuccessMessage("批量删除Prize成功");
    }
}
