package com.example.generator.demo.service;

import com.example.generator.demo.common.ServerResponse;
import com.example.generator.demo.dto.AdminDTO;
import com.github.pagehelper.PageInfo;

import java.util.Map;

/**
 * @Title: AdminService
 * @Description: Admin接口层
 * @Company: example
 * @Author: liuhf
 * @CreateTime: 2020/04/10 23:39:06
 */
public interface AdminService {

	/**
	 * @Title: list
	 * @Description: 查询Admin列表
	 * @Company: example
	 * @Author: liuhf
	 * @CreateTime: 2020/04/10 23:39:06
	 *
	 * @param pageNum
	 * @param pageSize
	 * @param params
	 * @return ServerResponse<PageInfo>
	 */
	ServerResponse<PageInfo> list(Integer pageNum, Integer pageSize, Map<String, String> params);

    /**
     * @Title: select
     * @Description: 查询Admin对象
     * @Company: example
     * @Author: liuhf
     * @CreateTime: 2020/04/10 23:39:06
     *
     * @param id
     * @return ServerResponse<AdminDTO>
     */
	ServerResponse<AdminDTO> select(Integer id);

    /**
     * @Title: insert
     * @Description: 保存Admin对象
     * @Company: example
     * @Author: liuhf
     * @CreateTime: 2020/04/10 23:39:06
     *
     * @param adminDTO
     * @return ServerResponse<String>
     */
	ServerResponse<String> insert(AdminDTO adminDTO);

    /**
     * @Title: update
     * @Description: 更新Admin对象
     * @Company: example
     * @Author: liuhf
     * @CreateTime: 2020/04/10 23:39:06
     *
     * @param id
     * @param adminDTO
     * @return ServerResponse<String>
     */
	ServerResponse<String> update(Integer id, AdminDTO adminDTO);

    /**
     * @Title: delete
     * @Description: 批量删除Admin对象
     * @Company: example
     * @Author: liuhf
     * @CreateTime: 2020/04/10 23:39:06
     *
     * @param ids
     * @return ServerResponse<String>
     */
	ServerResponse<String> delete(String ids);
}
