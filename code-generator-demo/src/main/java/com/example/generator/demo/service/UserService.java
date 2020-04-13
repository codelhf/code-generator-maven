package com.example.generator.demo.service;

import com.example.generator.demo.common.ServerResponse;
import com.example.generator.demo.dto.UserDTO;
import com.github.pagehelper.PageInfo;

import java.util.Map;

/**
 * @Title: UserService
 * @Description: User接口层
 * @Company: example
 * @Author: liuhf
 * @CreateTime: 2020/04/13 16:00:44
 */
public interface UserService {

	/**
	 * @Title: list
	 * @Description: 查询User列表
	 * @Company: example
	 * @Author: liuhf
	 * @CreateTime: 2020/04/13 16:00:44
	 *
	 * @param pageNum
	 * @param pageSize
	 * @param params
	 * @return ServerResponse<PageInfo>
	 */
	ServerResponse<PageInfo> list(Integer pageNum, Integer pageSize, Map<String, String> params);

    /**
     * @Title: select
     * @Description: 查询User对象
     * @Company: example
     * @Author: liuhf
     * @CreateTime: 2020/04/13 16:00:44
     *
     * @param id
     * @return ServerResponse<UserDTO>
     */
	ServerResponse<UserDTO> select(Integer id);

    /**
     * @Title: insert
     * @Description: 保存User对象
     * @Company: example
     * @Author: liuhf
     * @CreateTime: 2020/04/13 16:00:44
     *
     * @param userDTO
     * @return ServerResponse<String>
     */
	ServerResponse<String> insert(UserDTO userDTO);

    /**
     * @Title: update
     * @Description: 更新User对象
     * @Company: example
     * @Author: liuhf
     * @CreateTime: 2020/04/13 16:00:44
     *
     * @param id
     * @param userDTO
     * @return ServerResponse<String>
     */
	ServerResponse<String> update(Integer id, UserDTO userDTO);

    /**
     * @Title: delete
     * @Description: 批量删除User对象
     * @Company: example
     * @Author: liuhf
     * @CreateTime: 2020/04/13 16:00:44
     *
     * @param ids
     * @return ServerResponse<String>
     */
	ServerResponse<String> delete(String ids);
}
