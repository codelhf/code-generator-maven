package com.example.generator.demo.service;

import com.example.generator.demo.common.ServerResponse;
import com.example.generator.demo.dto.PeopleDTO;
import com.github.pagehelper.PageInfo;

import java.util.Map;

/**
 * @Title: PeopleService
 * @Description: People接口层
 * @Company: example
 * @Author: liuhf
 * @CreateTime: 2019-05-09 13:31:39
 */
public interface PeopleService {

	/**
	 * @Title: list
	 * @Description: 查询People列表
	 * @Company: example
	 * @Author: liuhf
	 * @CreateTime: 2019-05-09 13:31:39
	 *
	 * @param pageNum
	 * @param pageSize
	 * @param params
	 * @return ServerResponse<PageInfo>
	 */
	ServerResponse<PageInfo> list(Integer pageNum, Integer pageSize, Map<String, String> params);

    /**
     * @Title: select
     * @Description: 查询People对象
     * @Company: example
     * @Author: liuhf
     * @CreateTime: 2019-05-09 13:31:39
     *
     * @param id
     * @return ServerResponse<PeopleDTO>
     */
	ServerResponse<PeopleDTO> select(int id);

    /**
     * @Title: insert
     * @Description: 保存People对象
     * @Company: example
     * @Author: liuhf
     * @CreateTime: 2019-05-09 13:31:39
     *
     * @param peopleDTO
     * @return ServerResponse<String>
     */
	ServerResponse<String> insert(PeopleDTO peopleDTO);

    /**
     * @Title: update
     * @Description: 更新People对象
     * @Company: example
     * @Author: liuhf
     * @CreateTime: 2019-05-09 13:31:39
     *
     * @param id
     * @param peopleDTO
     * @return ServerResponse<String>
     */
	ServerResponse<String> update(int id, PeopleDTO peopleDTO);

    /**
     * @Title: delete
     * @Description: 批量删除People对象
     * @Company: example
     * @Author: liuhf
     * @CreateTime: 2019-05-09 13:31:39
     *
     * @param ids
     * @return ServerResponse<String>
     */
	ServerResponse<String> delete(String ids);
}
