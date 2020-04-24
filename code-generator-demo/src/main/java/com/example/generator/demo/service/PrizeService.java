package com.example.generator.demo.service;

import com.example.generator.demo.common.ServerResponse;
import com.example.generator.demo.dto.PrizeDTO;
import com.github.pagehelper.PageInfo;

import java.util.Map;

/**
 * @Title: PrizeService
 * @Description: Prize接口层
 * @Company: example
 * @Author: liuhf
 * @CreateTime: 2020/04/24 22:31:44
 */
public interface PrizeService {

	/**
	 * @Title: list
	 * @Description: 查询Prize列表
	 * @Company: example
	 * @Author: liuhf
	 * @CreateTime: 2020/04/24 22:31:44
	 *
	 * @param pageNum
	 * @param pageSize
	 * @param params
	 * @return ServerResponse<PageInfo>
	 */
	ServerResponse<PageInfo> list(Integer pageNum, Integer pageSize, Map<String, String> params);

    /**
     * @Title: select
     * @Description: 查询Prize对象
     * @Company: example
     * @Author: liuhf
     * @CreateTime: 2020/04/24 22:31:44
     *
     * @param id
     * @return ServerResponse<PrizeDTO>
     */
	ServerResponse<PrizeDTO> select(Integer id);

    /**
     * @Title: insert
     * @Description: 保存Prize对象
     * @Company: example
     * @Author: liuhf
     * @CreateTime: 2020/04/24 22:31:44
     *
     * @param prizeDTO
     * @return ServerResponse<String>
     */
	ServerResponse<String> insert(PrizeDTO prizeDTO);

    /**
     * @Title: update
     * @Description: 更新Prize对象
     * @Company: example
     * @Author: liuhf
     * @CreateTime: 2020/04/24 22:31:44
     *
     * @param id
     * @param prizeDTO
     * @return ServerResponse<String>
     */
	ServerResponse<String> update(Integer id, PrizeDTO prizeDTO);

    /**
     * @Title: delete
     * @Description: 批量删除Prize对象
     * @Company: example
     * @Author: liuhf
     * @CreateTime: 2020/04/24 22:31:44
     *
     * @param ids
     * @return ServerResponse<String>
     */
	ServerResponse<String> delete(String ids);
}
